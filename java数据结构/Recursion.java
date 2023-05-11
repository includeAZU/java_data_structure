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
}