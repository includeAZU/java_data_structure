//使用链表的方式实现

import java.util.Iterator;

public class stack_link_list {
    node s = new node(-1, null);// 哨兵节点,添加元素时在头部添加
    int size = 0;
    int capacity = Integer.MAX_VALUE;

    stack_link_list(int capacity) {
        this.capacity = capacity;

    }

    private static class node {
        // 只有class node没有使用非本类属性,才可以使用 static
        int value;
        node next;// 指向下一节点

        public node(int value, node node) {
            this.value = value;
            this.next = node;

        }
    }

    // 向栈顶压入元素
    // Params:value-待压入值
    // Returns:压入成功返回true,否则返回false
    // s ->1 ->2
    public boolean push(int value) {
        if (size >= this.capacity) {
            return false;
        }
        s.next = new node(value, s.next);
        size++;
        return true;
    }

    // 从栈弼弹出元素
    // Returns:栈非空返回栈顶元素，栈为空返回nul
    public node pop() {
        if (s.next == null) {
            return null;
        }
        node first = s.next;
        s.next = first.next;
        size--;
        return first;

    }

    // 返回栈顶元素，不弹出
    // Returns:栈非空返回栈顶元素，栈为空返回null
    public node peek() {
        return s.next;

    }

    public boolean isEmpty() {
        return size == 0 || s.next == null;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void print() {
        node p = s.next;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }

    }

}

class test8 {

    public static void main(String[] args) {
        stack_link_list test = new stack_link_list(8);
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        test.print();
    }

}