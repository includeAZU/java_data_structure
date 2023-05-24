//阻塞队列
//多线程 安全 问题 
//双锁问题 

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<E> {
    private E[] array;
    // 头指针 head 指向队列的第一个元素，尾指针 tail 指向队列最后一个元素的下一个位置（方便添加新元素）
    private int head;// 头指针
    private int tail;// 尾指针

    // size 赋值要经过 三 个过程 ,为了防止与其他线程交叉运行,
    // 使用原子变量AtomicInteger size
    private AtomicInteger size = new AtomicInteger();
    // 两个锁
    private ReentrantLock tailLock = new ReentrantLock(); // tailLock 与pop 使用

    private Condition tailWaits = tailLock.newCondition();// 等待 队列被加满时,

    private ReentrantLock headLock = new ReentrantLock();// headLock 与offer 搭配使用 ,添加元素时使用
    private Condition headWaits = headLock.newCondition();// 等待 队列被清空时,

    public BlockingQueue(int capacity) {
        // 定义数组的大小
        array = (E[]) new Object[capacity];

    }

    public boolean isEmpty() {
        // size 赋值要经过 三 个过程 ,为了防止与其他线程交叉运行,
        // 使用原子变量AtomicInteger size
        return size.get() == 0;

    }

    public boolean isFull() {

        return size.get() == array.length;
    }

    public String toString() {
        return Arrays.toString(array);

    }

    public void offer(E e) throws InterruptedException {
        // 这个方法用于添加元素到队列中。首先，它在 tailLock 的保护下获取了尾指针 tail，
        // 并判断队列是否已满。如果队列已满，则等待 tailWaits 中的信号（也就是等待有元素被取出队列，让出空间）
        // ，并在唤醒后重新检查队列是否已满。如果队列未满，则将元素添加至队尾并更新尾指针 tail，同时增加 size。
        // 最后，如果 size 值为 1（即队列之前为空），
        // 则发出 headWaits 中的信号（也就是通知正在等待队列非空的线程），以便其它线程可以从队列中取走元素。
        int c;//元素个数 
        tailLock.lockInterruptibly();// 进程上锁
        try {
            while (isFull()) {
                // 如果队列 满了 ,等待pop操作后释放空间然后唤醒
                tailWaits.await();// tailWaits 等待//阻塞

            }
            array[tail] = e;
            if (++tail == array.length) {
                // 如果发现是最后一个元素的下一个 ,队 满  ,tail 指向0
                tail = 0;

            }
            c = size.getAndIncrement();
            //c 是size 的值// size ++
            if (c + 1 < array.length) {
                // 如果 发现队列没有满,还可以继续添加 ,唤醒因为队满不可以添加而阻塞的任务
                // 减少重复上锁
                tailWaits.signal();
            }

        }

        finally {
            tailLock.unlock();
        }
        // 这段代码是在 offer() 方法中的，用于通知正在等待队列非空的线程。
        // 如果队列之前为空（即 size 等于 0），则发出 headWaits 中的信号，
        // 以便其它线程可以从队列中取走元素
        if (c == 0) {
            // 队空 

            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    // 这个方法用于从队列中获取一个元素。首先，它在 headLock 的保护下获取了头指针 head，
    // 并判断队列是否为空。如果队列为空，则等待 headWaits 中的信号（也就是等待队列非空），
    // 并在唤醒后重新检查队列是否为空。如果队列非空，则取出队列的第一个元素，
    // 更新头指针 head，并减小 size 值。最后，如果 size 在之前大于等于数组大小（即队列之前已满），
    // 则发出 tailWaits 中的信号（也就是通知正在等待队列未满的线程），以便其它线程可以添加元素至队列中。
    public E poll() throws InterruptedException {
        E e;
        int c; // 取走前的元素个数
        headLock.lockInterruptibly();
        try {
            // 1. 队列空则等待
            while (isEmpty()) {
                headWaits.await(); // poll_4
            }

            // 2. 非空则出队
            e = array[head];
            array[head] = null; // help GC
            if (++head == array.length) {
                head = 0;
            }

            // 3. 修改 size
            c = size.getAndDecrement();
            // 3->2 2->1 1->0
            // poll_1 poll_2 poll_3
            if (c > 1) {
                headWaits.signal();
            }
            /*
             * 1. 读取成员变量size的值 5
             * 2. 自减 4
             * 3. 结果写回成员变量size 4
             */
        } finally {
            headLock.unlock();
        }

        // 4. 队列从满->不满时 由poll唤醒等待不满的 offer 线程
        //原来是满的 ,现在不是满的 
        if (c == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal(); // ctrl+alt+t
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new BlockingQueue<>(3);
        queue.offer("元素1");
        queue.offer("元素2");

        new Thread(() -> {
            try {
                queue.offer("元素3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }
}
