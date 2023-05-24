// 1.找到最后一个非叶子 节点
// 2.从后向前，对每个节点执行下潜
//大顶堆 
// 计算机科学中，堆是一种基于树的数据结构，通常用完全二叉树实现。堆的特性如下
// ·在大顶堆中，任意节点C与它的父节点P符合P.value≥C.value
// ·而小顶堆中，任意节点C与它的父节点P符合P.value≤C.value
// ·最顶层的节点（没有父亲）称之为root根节点
//从左到右填满 
// 如果从素引0开始存储节点数据
// 节点i的父节点为floor(i-1)/2),当i>0时
// 节点i的左子节点为2i+1，右子节点为2i+2，当然它们得<size

// 如果从索引1开始存储节点数据
// 节点i的父节点为flo0r(i/2),当i>1时
// 节点i的左子节点为2i,右子节点为2+1，同样得<size

import java.time.Period;
import java.util.Arrays;

import javax.swing.text.AsyncBoxView.ChildLocator;

//大顶堆 
class maxHeap {
    int[] array;
    int size;// 记录堆中元素个数
    // 返回第一个元素,不删除

    public int peek() {
        if (isEmpty()) {
            // 如果堆 为空
            return -1;
        }
        return array[0];

    }

    // 返回第一个元素并删除
    public int poll() {
        if (isEmpty()) {
            // 如果堆 为空
            return -1;
        }
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;

    }

    // 根据位置删除元素
    public int poll(int site) {
        int delete = array[site];
        swap(site, size - 1);
        size--;
        down(site);
        return delete;

    }

    // 替换堆顶元素
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 在堆尾添加元素,大的上去
    public void up(int offered) {
        int chlid = size;
        int parent = (chlid - 1) / 2;
        while (chlid > 0 && array[parent] < offered) {
            array[chlid] = array[parent];
            chlid = parent;
            parent = (chlid - 1) / 2;

        }
        array[chlid] = offered;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public maxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        make_heap(array);// 创建大顶堆
    }

    // 从最后一个非叶子 节点开始 ,作为父节点 ,比较父节点与左右节点,和大的交换
    // 1.找到最后一个非叶子 节点
    // 2.从后向前，对每个节点执行下潜
    public void make_heap(int[] array) {
        // 最后一个非叶子节点索引
        for (int i = (size / 2) - 1; i >= 0; i--) {
            down(i);// 最后一个非叶子 节点

        }

    }

    // 下潜方法
    // 小的下去
    // 大顶堆
    public void down(int parent) {

        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max = parent;// 假设找到的就是最大的
        if (left < size && array[max] < array[left]) {
            max = left;
        }
        if (right < size && array[max] < array[right]) {
            max = right;
        }
        if (max != parent) {
            swap(parent, max);
            down(max);// 向下查找
        }

    }

    // 元素交换
    public void swap(int a, int b) {
        int c = array[a];
        array[a] = array[b];
        array[b] = c;

    }
}

// 小顶堆
class minHeap {
    int[] array;
    int size;// 记录堆中元素个数

    // 返回第一个元素,不删除
    public int peek() {
        if (isEmpty()) {
            // 如果堆 为空
            return -1;
        }
        return array[0];

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public minHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        make_heap(array);// 创建小顶堆
    }

    // 从最后一个非叶子 节点开始 ,作为父节点 ,比较父节点与左右节点,和小的交换
    // 1.找到最后一个非叶子 节点
    // 2.从后向前，对每个节点执行下潜
    public void make_heap(int[] array) {
        // 最后一个非叶子节点索引
        for (int i = (size / 2) - 1; i >= 0; i--) {
            down(i);// 最后一个非叶子 节点

        }

    }

    // 下潜方法
    // 大的下去
    // 小顶堆
    public void down(int parent) {

        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int min = parent;// 假设找到的就是最小的
        if (left < size && array[min] > array[left]) {
            min = left;
        }
        if (right < size && array[min] > array[right]) {
            min = right;
        }
        if (min != parent) {
            swap(parent, min);
            down(min);// 向下查找
        }

    }

    // 元素交换
    public void swap(int a, int b) {
        int c = array[a];
        array[a] = array[b];
        array[b] = c;

    }
}

class test12 {

    public static void main(String[] args) {
        int[] a = { 8, 9, 1, 3, 4, 6, 5, 7, };
        minHeap test = new minHeap(a);
        System.out.println(Arrays.toString(a));

    }
}
