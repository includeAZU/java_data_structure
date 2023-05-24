import java.time.Period;
import java.util.Arrays;

import javax.swing.text.AsyncBoxView.ChildLocator;

//堆排序 
// 算法描述
// 1.heapify建立大J顶堆
// 2.将堆顶与堆底交换（最大元素被交换到堆底），缩小并下潜调整堆
// 3.重复第二步直至堆里剩一个元素
class heapSort {
    public static void main(String[] args) {
        int a[] = { 33, 21, 7, 54, 5, 96, 3, 89, 1 };
        maxHeap test = new maxHeap(a);
        System.out.println(Arrays.toString(a));
        // 每一次就把最大的放后面
        while (test.size > 1) {
            test.swap(0, test.size - 1);
            test.size--;
            test.down(0);

        }
        System.out.println(Arrays.toString(a));
    }

}

// 给定整数数组nums
// 和整数k,请返回数组中第k个最大 的元素。
// 清注意，你需要找的是数组排序后的第k个最大的元素，而不是第k个不同的元素。
// 你必须设计并实现时间复杂度为0()的算法解决此问题。
// 解体思路
// 1.向小顶堆放入前k个元素
// 2.剩余元素
// 若<=堆顶元素，则略过
// 若>堆顶元素，则替换堆顶元素
// 这样小顶堆始终保留的是到目前为止，前k大的元素
// 循环结束，堆顶元素即为第K大元素
class findKMax {
    public static void main(String[] args) {
        int a[] = { 33, 21, 7, 54, 5, 96, 3, 89, 1 };
        // maxHeap test = new maxHeap(a);
        System.out.println(Arrays.toString(a));
        int c = findKthLargest(a, 3);
        System.out.println(c);

    }

    public static int findKthLargest(int[] numbers, int k) {
        minHeap heap = new minHeap(k);// 数组长度为k
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);// 1.向小顶堆放入前k个元素
        }
        for (int i = k; i < numbers.length; i++) {
            // 2.剩余元素
            if (numbers[i] > heap.peek()) {
                heap.replace(numbers[i]);
            }

        }
        return heap.peek();
    }
}

class findMid {
    public static void main(String[] args) {
        int a[] = { 33, 21, 7, 54, 5, 96, 3, 89, 1 };
        maxHeap test = new maxHeap(a);
        System.out.println(Arrays.toString(a));

    }

}

// 小顶堆
class minHeap {
    int[] array;
    int size;// 记录堆中元素个数

    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    minHeap(int capacity) {
        array = new int[capacity];
    }

    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 在堆尾添加元素,小的上去
    public void up(int offered) {
        int chlid = size;
        int parent = (chlid - 1) / 2;
        while (chlid > 0 && array[parent] > offered) {
            array[chlid] = array[parent];
            chlid = parent;
            parent = (chlid - 1) / 2;

        }
        array[chlid] = offered;

    }

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

// 大顶堆
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
