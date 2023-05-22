// 计算机科学中，堆是一种基于树的数据结构，通常用完全二叉树实现。堆的特性如下
// ·在大顶堆中，任意节点C与它的父节点P符合P.value≥C.value
// ·而小顶堆中，任意节点C与它的父节点P符合P.value≤C.value
// ·最顶层的节点（没有父亲）称之为root根节点
//从左到右填满 
// 如果从素引0开始存储节点数据
// 节点i的父节点为floor(2-1)/2),当i>0时
// 节点i的左子节点为2i+1，右子节点为2i+2，当然它们得<size

// 如果从索引1开始存储节点数据
// 节点i的父节点为flo0r(i/2),当i>1时
// 节点i的左子节点为2i,右子节点为2+1，同样得<size
class PriorityQueueHeap<E extends Priority> {
    Priority[] array;
    int size;

    public PriorityQueueHeap(int capacity) {
        // 设置数组长度
        array = new Priority[capacity];
    }

    // val 是一个数组,有自己的优先级
    // 再最后加入元素,索引设置为 chaid ,比较与父节点的优先级
    // 如果孩子优先级大于父节点,调换位置
    public boolean offer(E val) {
        if (isFull()) {
            return false;
        }
        int child = size++; // 孩子节点索引
        int parent = (child - 1) / 2;
        while (child > 0 && val.priority() > array[parent].priority()) {
            // 新加入节点的优先级父节点优先级比较
            array[child] = array[parent];// 父节点向下移动
            child = parent;// 新父节点向上转移
            parent = (child - 1) / 2;

        }
        array[child] = val;
        return true;

    }

    /*
     * 1,交换堆顶和尾部元素让尾部元素出队
     * 2.(下潜)
     * 从堆顶开始，将父元素与两个孩子较大孩子交换
     * 直到父元素大于两个孩子，或没有孩子为止
     */
    public E poll() {
        if (isEmpty()) {
            return null;

        }
        swap(0, size - 1);
        Priority a = array[size - 1];
        array[size - 1] = null;
        floft(0);
        return (E) a;

    }

    public void floft(int parent) {
        int min = parent; // 索引

        int left = (2 * parent) + 1;// 优先数
        int right = (2 * parent) + 2;
        if (array[left].priority() > array[min].priority()) {
            min = left;// 索引

        }
        if (left < size && array[right].priority() > array[min].priority()) {
            min = right;// 索引

        }
        if (min != parent) {

            swap(min, parent);// 交换
            floft(min);//

        }
    }

    public void swap(int a, int b) {
        Priority c = array[a];
        array[a] = array[b];
        array[b] = c;

    }

    // 返回优先数最高的数
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return (E) array[0];
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;

    }

}

// 小顶堆
class PriorityQueueSmallHeap {
    // 节点类
    class node {
        int val;// 存储值
        int Priority;// 优先数

    }

    node[] array;// 数组存储类型是node
    int size;

    public PriorityQueueSmallHeap(int capacity) {
        // 设置数组长度
        array = new node[capacity];
    }

    // 再最后加入元素,索引设置为 chaid ,比较与父节点的优先级
    // 如果孩子优先级小 于父节点,调换位置
    public boolean offer(node e) {
        if (isFull()) {
            return false;
        }
        int child = size++; // 孩子节点索引
        int parent = (child - 1) / 2;
        while (child > 0 && e.Priority < array[parent].Priority) {
            // 新加入节点的优先级父节点优先级比较
            // 父节点优先级大于需要加入的节点
            array[child] = array[parent];// 父节点向下移动
            child = parent;// 父节点向下转移
            parent = (child - 1) / 2;

        }
        array[child] = e;
        return true;

    }

    /*
     * 1,交换堆顶和尾部元素让尾部元素出队
     * 2.(上潜)
     * 从堆顶开始，将父元素与两个孩子较大孩子交换
     * 直到父元素小于两个孩子，或没有孩子为止
     */
    public node poll() {
        if (isEmpty()) {
            return null;

        }
        swap(0, size - 1);
        node a = array[size - 1];
        array[size - 1] = null;
        floft(0);
        return a;

    }

    public void floft(int parent) {
        //假设0索引处 优先数是最小的
        int min = parent; // 索引

        int left = (2 * parent) + 1;// 优先数
        int right = (2 * parent) + 2;
        if (array[left].Priority < array[min].Priority) {
            min = left;// 索引

        }
        if (left < size && array[right].Priority < array[min].Priority) {
            min = right;// 索引

        }
        if (min != parent) {

            swap(min, parent);// 交换
            floft(min);//

        }
    }

    public void swap(int a, int b) {
        node c = array[a];
        array[a] = array[b];
        array[b] = c;

    }

    // 返回优先数最小的数
    public node peek() {
        if (isEmpty()) {
            return null;
        }

        return array[0];
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;

    }

}