// 环形数组实现
// 好处
// 1.对比普通数组，起点和终点更为自由，不用考虑数据移动
// 2.“环“意味着不会存在【越界】问题
// 3.数组性能更佳
// 4.环形数组比较适合实现有界队列、RingBuffer等

// (cur +step)%length
// 。cur当前指针位置
// ·step前进步数
// ·length数组长度
//队列为空 头指针==尾指针
//队列为满,(tail +1)%总容量==head
/*关于对capacity 的优化,我们需要把capacity 的值转化为 2^ n 形式,优化程序运行速度
第一种方法,假如是30,需要转化为32,可以 2^(int )log2(30-1)+1,本身是2^n 也可以转化
怎么计算tail , tail =tail &(array.lenght-1),提高计算速度
第二种 二叉数遍历
  c--;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c++;
        //666 
*/

import java.net.CacheRequest;
import java.security.DrbgParameters.Capability;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SizeAction;

public class myqueue_array<E> implements Queue<E>, Iterable<E> {

    E[] array;

    int head = 1;// 头指针
    int tail = 1;// 尾指针
    int size;// size的最大值可以使是数组的最大长度
    // 不需要专门留一个空位置了
//求模运算：
// -如果除数是2的n次方
// -那么被除数的后n位即为余数（模）
// -求被除数的后n亚法：与2n-1按位与
    myqueue_array(int capacity) {
        // 有参构造
        // 有一个指向最后一个元素,但是不赋值
        // 有效利用就是 capacity
        // 其中，Object 是 Java 中的顶级父类，可以表示任何对象类型，所以我们可以把它作为数组类型。
        // 在对 array 数组使用时，需要进行强制类型转换为 E 类型，避免出现编译器错误。
        array = (E[]) new Object[capacity + 1];
        size = capacity;

    }

    // 添加元素
    public boolean offer(E e) {
        if ((tail + 1) % array.length == head)// 已经满了
        {
            return false;
        }
        array[tail] = e;

        tail = (tail + 1) % array.length;
        size++;
        return true;
    }

    public boolean offer1(E e) {
        //感觉差不多

        if ((tail + 1) % array.length == head)// 已经满了
        {
            return false;
        }
        array[tail % array.length] = e;
        tail++;
        return true;

    }

    // 移除元素

    public E poll() {
        if (tail == head) {
            return null;
        }
        E first = array[head];

        head = (head + 1) % array.length;
        size--;
        return first;

    }

    public boolean isFull() {

        return (tail + 1) % array.length == head || size == array.length;// array.length如果在边缘
    }

    public boolean isEmpty() {
        {
            return head == tail || size == 0;
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;// 添加一个指针

            public boolean hasNext() {
                return p != tail;// 查找条件
            }

            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public boolean add(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public E remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public E element() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'element'");
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

}