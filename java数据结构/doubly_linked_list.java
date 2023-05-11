
public class doubly_linked_list {

    public Node head;
    public Node tail;

    doubly_linked_list() {// 默认无参构造方法

        head = new Node(null, 66, null);
        tail = new Node(null, 88, null);
        head.next = tail;
        tail.prev = head;
    }

    // 节点类
    class Node {
        private Node prev;
        private Node next;
        public int value;

        Node(Node prev, int value, Node next) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }

    public Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; i++, p = p.next) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void insert(int index, int value)

    {
        // Node last = findNode(index);
        // Node prev = last.prev;

        // Node insert = new Node(prev, value, last);
        // prev.next = insert;
        // last.prev = insert;
        Node prev = findNode(index - 1);
        if (prev == null) {
            return;
        }
        Node next = prev.next;
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;

    }

    public void remove(int index) {

        Node car = findNode(index);
        if (car.next == tail) {
            return;
        }
        car.prev.next = car.next;
        car.next.prev = car.prev;
        if (tail.prev == head) { // 判断链表长度是否为1
            head.next = tail;
        }

    }

    public void removeFirst() {
        head.next = head.next.next;
        head.next.prev = head;
    }

    public void removeLast() {
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
    }

    public void for_each() {

        for (Node car = head.next; car != tail; car = car.next) {
            System.out.println(car.value);
        }

    }

}

class Test2 {

    public static void main(String[] args) {
        doubly_linked_list mytest = new doubly_linked_list();
        mytest.insert(0, 660);
        mytest.insert(1, 330);
        mytest.insert(2, 970);
        mytest.insert(3, 1);
        mytest.insert(4, 1);
        mytest.remove(0);
        mytest.removeFirst();
        mytest.removeLast();
        mytest.for_each();

    }

}