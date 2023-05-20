
//用数组的方法实现栈

import java.rmi.server.ObjID;
import java.util.Iterator;

import javax.swing.text.DefaultEditorKit.BeepAction;

public class stack_array<E> implements Iterable<E> {
    int top;
    E[] array;
    int size = 0;
    int capacity;

    // 0 1 2 3 4 //5 capacity
    // 2 3 4 5 6 top
    // top 位置在 最后一个元素之后的位置
    stack_array(int capacity) {
        array = (E[]) new Object[capacity];// 需要强制类型转换
        this.capacity = capacity;
    }

    // t添加元素
    public boolean push(E val) {
        if (size == capacity) {
            return false;
        }
        array[top] = val;
        top++;
        return true;

    }

    // 弹出元素
    public E pop() {
        if (top == 0) {
            return null;
        }
        E e = array[--top];
        array[top] = null; // help GC
        return e;

    }

    // 弹出元素但不删除
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;

    }

    public boolean isFull() {
        return top == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top;

            public boolean hasNext() {
                return top == 0;
            }

            public E next() {
                return array[--p];

            }
        };

    }

}

class test9 {
    // 给定一个只包括’（[，’），’”，’}’，’]，’]’的字符串s,判断字符串是否有
    // 效。
    // 有效字符串需满足：
    // 1.左括号必须用相同类型的右括号闭合。
    // 2.左括号必须以正确的顺序闭合。
    // 3.每个右括号都有一个对应的相同类型的左括号。
    public static boolean isValid(String s) {// static
        stack_array<Character> stack = new stack_array<>(s.length());// 指定类型//包装类
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                stack.push('}');

            } else if (c == '[') {
                stack.push(']');

            } else if (c == '(') {
                stack.push(')');

            } else {
                if (!stack.isEmpty() && c == stack.peek())

                {
                    stack.pop();

                } else {
                    return false;
                }

            }

        }
        return stack.isEmpty();
    }

    // 逆波兰表达式也称为后缀表达式，即运算符写在后面
    // ·从左向右进行计算
    // ·不必考虑运算符优先级，即不用包含括号
    public static int Reverse_Polish_notation(String[] arr)// 用字符数组,需要转成int
    {
        stack_array<Integer> stack = new stack_array<>(arr.length);// 看 stack操作什么类型的数字
        for (String c : arr) {
            switch (c) {
                case "+": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "/": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                case "-": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "*": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);
                    break;
                }

                default: {
                    stack.push(Integer.parseUnsignedInt(c));// String 类型转换为int 类型
                    break;
                }

            }
        }
        return stack.pop();

    }

    public static void main(String[] args) {
        // boolean a = isValid("[}");
        // System.out.println(a);
        String[] a = { "1", "2", "+", "3", "*" };
        int res = Reverse_Polish_notation(a);
        System.out.println(res);

    }
}
