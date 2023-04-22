import java.net.InterfaceAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import javax.swing.text.html.parser.Element;

public class dynamic_array implements Iterable<Integer> {//
    // 使用迭代器进行遍历
    private int capacity = 10;

    public int[] array = new int[capacity];
    private int size = 0;

    public void add_last(int element) {
        array[size] = element;

        size++;

    }

    public void add(int index, int element) {
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);

        }
        array[index] = element;

        size++;
    }

    public int get(int index) {
        return array[index];

    }

    public void foreach(comsumer<Integer> comsumer) {
        for (int i = 0; i < array.length; i++) {

            comsumer.accept(array[i]);
        }

    }

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            int i = 0;

            public boolean hasNext() {// 有没有下个元素

                return i < size;

            }

            public Integer next() {
                // 返回下个元素
                return array[i++];
            }

        };
    }

    // 流方式遍历
    public IntStream stream() {

        return IntStream.of(Arrays.copyOfRange(array, 0, size));

    }

}

class test {
    public static void main(String[] args) {
        dynamic_array test = new dynamic_array();

        test.add_last(1);
        test.add_last(2);
        test.add_last(3);
        test.add_last(8);
        test.add(1, 33);
        test.add(3, 88);
        //System.out.println(Arrays.toString(test.array));

        // test .forEach((element)->{System.out.println(element);});
        // for (Integer element : dynamic_array) {
        // System.out.println(element);//有问题

        // }
        test .stream().forEach((element)->{System.out.println(element);});
    }
}
