import java.util.Arrays;

public class myarray {
   // 将数组内两个区间内的有序元素合并
   // 递归实现
   public void merge(int[] a1, int i, int iend, int j, int jend, int[] a2, int k) {
      if (i > iend) {
         System.arraycopy(a1, j, a2, k, jend - j + 1);
         return;
      }
      if (j > jend) {
         System.arraycopy(a1, i, a2, k, iend - i + 1);
         return;
      }
      if (a1[i] < a1[j]) {
         a2[k] = a1[i];
         merge(a1, i + 1, iend, j, jend, a2, k + 1);

      } else {
         a2[k] = a1[j];
         merge(a1, i, iend, j + 1, jend, a2, k + 1);
      }
   }
}

class test6 {
   public static void main(String[] args) {
      myarray test = new myarray();

      int[] a1 = { 1, 5, 6, 2, 4, 10, 11 };
      int[] a2 = new int[a1.length];
      test.merge(a1, 0, 2, 3, 6, a2, 0);
      System.out.println(Arrays.toString(a2));
   }

}