//有序数组实现

class PriorityQueueOrderedArrays<E extends Priority> {
    Priority[] array;
    int size;

    public PriorityQueueOrderedArrays(int capacity) {
        // 设置数组长度
        array = new Priority[capacity];

    }

    // val 是一个数组,有自己的优先级
    public boolean offer(E val) {
        if (isFull()) {
            return false;
        }

        insert(val);
        size++;
        return true;
    }

    public void insert(E e) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i + 1] = array[i];
            i--;

        }
        array[i + 1] = e;

    }

    // 删除 并返回
    public E poll() {
        if (isEmpty()) {
            return null;

        }
        E a = (E) array[size - 1];
        array[--size] = null;
        return a;

    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return (E) array[size - 1];
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;

    }

}