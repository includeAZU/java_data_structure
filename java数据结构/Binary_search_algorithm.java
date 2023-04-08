
//二分查找法
// 需求：在**有序**数组 $A$ 内，查找值 $target$
// * 如果找到返回索引
// * 如果找不到返回 $-1$
//如果用8位表示无符号数,255就是1111 1111
//如果8位表示有符号,-1就是1111 1111,最高位是符号位
public class Binary_search_algorithm {
  public static int Binary_search(int[] a, int target) {
    int left = 0;
    int right = a.length - 1;

    while (left <= right) { // target是否在left , right中间
      int mid = (right + left) >>> 1;//(/2)
      //因为在Java中,会把最高位当成符号位,如果(right + left)/2超出了int 最大表示范围,就会变成负数(int无法表示),>>>运算不会产生这问题
      if (a[mid] < target) { // 目标在mid右边
        left = mid + 1;

      } else if (a[mid] > target) { // 目标在mid左边.right = mid + 1;
        right = mid - 1;
      } else {
        return mid;

      }
    }
    return -1;// 找不到
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6, 7, 9 };
    //System.out.println(Binary_search(a, 1));
    System.out.println((Integer.MAX_VALUE+Integer.MAX_VALUE+8)>>>1);
  }

}