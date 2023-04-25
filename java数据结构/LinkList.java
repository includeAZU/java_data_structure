import java.util.Iterator;

import javax.management.ValueExp;

public class LinkList implements Iterable<Integer> {

    private node head = null;// 头节点

    //
    // 节点
    private static class node {
        // 只有class node没有使用非本类属性,才可以使用 static
        int value;
        node next;// 指向下一节点

        public node(int value, node node) {
            this.value = value;
            this.next = node;

        }
    }

    // 头插法
    public void addFrist(int value) {
        // 链表为空
        if (head == null) {
            node head = new node(value, null);
        }
        // 链表不为空
        node add_Node = new node(value, null);
        add_Node.next = head;
        head = add_Node;
        // 创建头节点

    }

    // 遍历链表
    public void loop() {
        node p;

        p = head;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }

    }

    // 函数接口遍历链表
    public void loop1(comsumer<Integer> comsumer) {
        node p;

        p = head;
        while (p != null) {
            comsumer.accept(p.value);
            p = p.next;
        }

    }

    @Override
    // 迭代器遍历链表
    public Iterator<Integer> iterator() {
        // 匿名内部类
        return new Iterator<Integer>() {
            node p = head;

            public boolean hasNext() {
                return p != null;

            }

            public Integer next() {
                int v = p.value;
                p = p.next;
                return v;

            }

        };
    }

    // 查找最后一个元素
    private node findLast() {
        if (head == null) {
            return null;
        }
        node p = null;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    // 尾插法
    public void add_last(int value) {
        node last = findLast();
        if (last == null) {
            addFrist(value);
            return;

        }

        last.next = new node(value, null);

    }

    // 查找节点
    private node findNode(int index) {
        node p = head;
        int i = 0;

        for (p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;

            }
        }
        return null;
    }

    public int get(int index) {
        node i = findNode(index);
        if (i == null) {
            // 异常
            throw new IllegalArgumentException(
                    String.format("index[%d]不合法%n", index));
        }
        return i.value;

    }

    // 插入节点
    public void insert(int index, int value)

    {
        if (index == 0) {// 等于0
            addFrist(value);
            return;
        }
        node prve = findNode(index - 1);// 返回找到的元素
        if (prve == null) {
            throw new IllegalArgumentException(
                    String.format("index[%d]不合法%n", index));

        }
        prve.next = new node(value, prve.next);

    }
//删除元素
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
            return;
        }
        node prve = findNode(index - 1);// 返回找到的元素(要删除元素的前驱)
        if (prve.next == null) {//找到要删除元素前驱,但删除元素不存在
            throw new IllegalArgumentException(
                    String.format("index[%d]不合法%n", index));

        }

        prve.next = prve.next.next;

    }


}

class mytest {

    public static void main(String[] args) {
        LinkList test = new LinkList();
        test.add_last(1);
        test.add_last(2);
        test.add_last(3);
        test.insert(0, 99);
        test.remove(0);
        test.loop();
        System.out.println(test.get(2));
        // test.loop1(value -> {System.out.println(value);});
        // for (Integer value : test) {
        // System.out.println(value);
        // }
    }
}
