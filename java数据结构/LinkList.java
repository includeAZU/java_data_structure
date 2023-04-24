import java.util.Iterator;

import javax.management.ValueExp;

public class LinkList implements Iterable<Integer> {

    private node head = null;// 头节点

    //
    // 节点
    private static class node {
        // 只有class node没有使用非本类属性,才可以使用 static
        int value;
        node Node;// 指向下一节点

        public node(int value, node node) {
            this.value = value;
            this.Node = node;

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
        add_Node.Node = head;
        head = add_Node;
        // 创建头节点

    }

    // 遍历链表
    public void loop() {
        node p;

        p = head;
        while (p != null) {
            System.out.println(p.value);
            p = p.Node;
        }

    }

    // 函数接口遍历链表
    public void loop1(comsumer<Integer> comsumer) {
        node p;

        p = head;
        while (p != null) {
            comsumer.accept(p.value);
            p = p.Node;
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
                p = p.Node;
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
        for (p = head; p.Node != null; p = p.Node) {

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

        last.Node = new node(value, null);

    }

    private node findNode(int index) {
        node p = head;
        int i = 0;

        for (p = head; p != null; p = p.Node, i++) {
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
}

class mytest {

    public static void main(String[] args) {
        LinkList test = new LinkList();
        test.add_last(1);
        test.add_last(2);
        test.add_last(3);
        test.loop();
        System.out.println(test.get(5));
        // test.loop1(value -> {System.out.println(value);});
        // for (Integer value : test) {
        // System.out.println(value);
        // }
    }
}