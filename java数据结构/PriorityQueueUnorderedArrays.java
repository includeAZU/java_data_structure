//无序数组实现
//每存储有两个元素 ,一个是元素的值,一个是元素的优先级
//需要实现 Priority 这个接口,返回优先级
//需要 Priority.java 这个文件 

public class PriorityQueueUnorderedArrays<E extends Priority> {
    Priority[] array;
    int size;

    public PriorityQueueUnorderedArrays(int capacity) {
        // 设置数组长度
        array = new Priority[capacity];

    }

    public boolean offer(E val) {
        if (isFull()) {
            return false;
        }
        array[size++] = val;
        return true;
    }

    // 找到优先级最大的元素 索引
    // array[i].Priority() 获取 优先数
    public int selectMax() {
        int Max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[Max].priority()) {
                Max = i;

            }
        }
        return Max;

    }

    // 删除 并返回
    public E poll() {
        if (isEmpty()) {
            return null;

        }
        int Max = selectMax();
        E e = (E) array[Max];
        remove(Max);
        return e;
    }

    public void remove(int index) {
        // 不是最后一个元素
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);

        }
        array[--size] = null;// 如果是最后一个,删除后置空

    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        return (E) array[max];
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;

    }
}
