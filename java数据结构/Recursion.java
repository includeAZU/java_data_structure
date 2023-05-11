// 自己调用自己，如果说每个函数对应着一种解决方案，自己调用自己意味着解决方案是一样的（有规律的）
// 每次调用，函数处理的数据会较上次缩减（子集），而且最后会缩减至无需继续递归
// 内层函数调用（子集处理）完成，外层函数才能算调用完成
// void f(Nodenode =1){
//     printIn("before:"node.value)
//     void f(Nodenode 2){
//     println("before:"node.value)
//     void f(Node node =3){
//     println("before:"node.value)
//     void f(Node node null){
//     if(node =null){
//     return;
//     3子
//     println("after:"node.value)
//     3子
//     println("after:"node.value)
//     3
//     println("after:"node.value)
//     3
// 深入到最里层叫做递
// 最里层出来叫做归
// 在递的过程中，外层函数内的局部变量（以及方法参数）并未消失，归的
// 时候还可以用到

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class Recursion {
    // 递归求阶乘
    public int factorial(int data) {
        if (data == 1) {
            return 1;
        }
        return data * factorial(data - 1);
    }

    // 递归逆序打印字符串
    public void printf(int n, String a) {
        if (n == a.length()) {
            return;
        }
        printf(n + 1, a);
        System.out.print(a.charAt(n));// 按索引打印字符串内容

    }

    // 递归二分查找
    public int Binary_search(int data, int[] array, int low, int hight) {
        int mid = (low + hight) >> 1;
        if (low > hight) {
            return -1;
        }
        if (data > array[mid]) {
            return Binary_search(data, array, mid + 1, hight);

        }

        else if (data < array[mid]) {
            return Binary_search(data, array, low, mid - 1);

        } else {
            return mid;
        }

    }

    // 递归实现冒泡排序
    // 利用是否交换了元素进行判断,如果在交换中,一个元素都没有交换,就说明元素都是从小到大排序的
    public void bullle(int[] array) {
        boolean flag = false;

        for (int i = 0; i < array.length - 1; i++) {// 一次交换

            if (array[i] > array[i + 1]) {
                int t = array[i];
                array[i] = array[i + 1];
                array[i + 1] = t;
                flag = true;
            }

        }
        if (flag == false) {
            return;
        }
        bullle(array);

    }

    // 避免交换
    private static void bubble1(int[] a, int j) {
        if (j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[+1];
                a[i + 1] = t;
                x = i;
            }
        }
        bubble1(a, x);
    }

    // 递归实现插入排序
    // 实现2,冒泡排序,反过来
    public static void insert(int a[], int low) {// 要排序的数组,未排序数字的左边界
        if (low == a.length) {
            return;

        }
        int t = a[low];// 未排序的最左
        int i = low = -1;
        while (i >= 0 && a[i] > t) {
            a[i + i] = a[i];
            i--;
        }

        // 找到插入位置
        a[i + 1] = t;
        insert(a, low);

    }

    // 例3-斐波那契数列(多路递归)
    public static int E06Fibonacci(int n)

    {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;

        }
        int x = E06Fibonacci(n - 1);
        int y = E06Fibonacci(n - 2);
        return x + y;

    }

    // 优化例3-斐波那契数列(多路递归)
    public static int fibonacci(int n)

    {
        int[] cacha = new int[n + 1];
        Arrays.fill(cacha, -1);// 用-1填充
        cacha[0] = 0;
        cacha[1] = 1;
        return f(n, cacha);

    }

    public static int f(int n, int[] cacha) {
        if (cacha[n] != -1) {
            return cacha[n];// 选数

        }
        int x = f(n - 1, cacha);// 从数组中选数
        int y = f(n - 2, cacha);
        cacha[n] = x + y;// 刚好 就是数组下标
        return cacha[n];

    }

    // 递归求和n+n-1,…,+1//fn=f(n-1)+n
    // 爆栈问题
    // 解决方法,改为循环
    @tailrec // 检查是否是伪递归
    public static long sum(long n) {
        if (n == 1) {
            return 1;
        }
        return sum(n - 1) + n;

    }

    // 尾调用和尾递归
    // 如果函数的最后一步是调用一个函数
    // 那么称为尾调用
    // 归并排序
    // 这是一个归并排序的分割函数，用于将一个整数数组 A[] 分割成两半，并逐步递归至单个元素，再进行合并操作。

    // 具体来说，该函数接收三个参数，分别为：

    // B[]：表示用于存储归并结果的中间数组；
    // i 和 j：表示要分割的子数组 A[i...j] 的左右边界（其中，i 表示左边界，j 表示右边界）；
    // A[]：表示原始的要排序的数组。
    void split(int B[], int i, int j, int A[]) {
        if (j - i <= 1) // 归并排序
            return;
        int m = (i + j) / 2;
        // 递归
        split(A, i, m, B);
        split(A, m, j, B);
        // 合并
        // merge(B, i, m, j, A);
    }

}

class DoublyLinkedListSentinel implements Iterable<Integer> {
    static class Node {
        Node prev; // 上一个节点指针
        int value; // 值
        Node next; // 下一个节点指针

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node head; // 头哨兵
    private Node tail; // 尾哨兵

    public DoublyLinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node last = tail.prev;
        Node added = new Node(last, value, tail);
        last.next = added;
        tail.prev = added;
    }

    public void removeLast() {
        Node removed = tail.prev;
        if (removed == head) {
            throw illegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node next = prev.next;
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;
    }

    public void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == tail) {
            throw illegalIndex(index);
        }
        Node next = removed.next;

        prev.next = next;
        next.prev = prev;
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index));
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

}

class hannoTower {
    // 汉诺塔
    // 假设每根柱子标号，b,c,每个圆盘用1,2,3.表示其大小，圆盘初始在a,要移动到的目标是c
    // 如果只有一个圆盘，此时是最小问题，可以直接求解
    // // o移动圆盘1a→C
    // ·如果有两个圆盘，那么
    // o圆盘1a→b
    // o圆盘2a→C
    // 0圆盘1b→C
    // ·如果有三个圆盘，那么
    // 0圆盘12a→b
    // o圆盘3a→C
    // 0圆盘12b→C

    static DoublyLinkedListSentinel a = new DoublyLinkedListSentinel();// a b c 三个柱子
    static DoublyLinkedListSentinel b = new DoublyLinkedListSentinel();
    static DoublyLinkedListSentinel c = new DoublyLinkedListSentinel();

    static void init(int n) {
        for (int i = n; i >= 1; i--) {

            a.addLast(n);
        }

    }

    static void move(int n, LinkList a, LinkList b, LinkList c)

    {
        if (n == 0) {

            return;

        }
        move(n - 1, a, c, b); // 把 n-1 个盘子由a,借c,移至b
        c.add_last((int) a.removeLast()); // 把最后的盘子由 a 移至 c
        // print();
        move(n - 1, b, a, c); // 把 n-1 个盘子由b,借a,移至c

    }
}

/**
 * 递归杨辉三角(Pascal三角)
 */
class E03PascalTriangle {

    /**
     * <h3>直接递归(未优化)</h3>
     *
     * @param i 行坐标
     * @param j 列坐标
     * @return 该坐标元素值
     */
    private static int element(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    private static void printSpace(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            // printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j));
            }
            System.out.println();
        }
    }
    /*
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     * 1 5 10 10 5 1
     */

    /**
     * <h3>优化1 - 使用二维数组记忆法</h3>
     *
     * @param triangle 二维数组
     * @param i        行坐标
     * @param j        列坐标
     * @return 该坐标元素值
     */
    private static int element1(int[][] triangle, int i, int j) {
        if (triangle[i][j] > 0) {
            return triangle[i][j];
        }

        if (j == 0 || i == j) {
            triangle[i][j] = 1;
            return 1;
        }
        triangle[i][j] = element1(triangle, i - 1, j - 1) + element1(triangle, i - 1, j);
        return triangle[i][j];
    }

    public static void print1(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) { // 行
            triangle[i] = new int[i + 1];
            // printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element1(triangle, i, j));
            }
            System.out.println();
        }
    }
}

class Test4 {
    public static void main(String[] args) {
        Recursion test = new Recursion();
        // System.out.println(test.factorial(5));
        // test.printf(0, "asd");
        int[] a = { 5, 4, 3, 2, 1 };
        test.bullle(a);

        System.out.println(Arrays.toString(a));
    }
