public class CircularBidirectionalLinkedList {
    // 双向环形链表带哨兵，这时哨兵既作为头，也作为尾
    private static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private final Node sentinel = new Node(null, -1, null);

    public CircularBidirectionalLinkedList() {
        // 双向环形链表带哨兵，这时哨兵既作为头，也作为尾
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 添加到第一个
     *
     * @param value 待添加值
     */
    public void addFirst(int value) {// 头插法
        Node prev = sentinel;
        Node Next = sentinel.next;
        Node insert = new Node(prev, value, Next);
        prev.next = insert;
        Next.prev = insert;

    }

    /**
     * 添加到最后一个
     *
     * @param value 待添加值
     */
    public void addLast(int value) {// 尾插法
        Node prev = sentinel.prev;
        Node Next = sentinel;
        Node insert = new Node(prev, value, Next);
        prev.next = insert;
        Next.prev = insert;

    }

    /**
     * 删除第一个
     */
    public void removeFirst() {
        Node remove_data = sentinel.next;
        sentinel.next = remove_data.next;
        remove_data.next.prev = sentinel;

    }

    /**
     * 删除最后一个
     */
    public void removeLast() {
        Node remove_data = sentinel.prev;
        sentinel.prev = remove_data.prev;
        remove_data.prev.next = sentinel;
    }

    /**
     * 根据值删除
     *
     * @param value 目标值
     */
    public void removeByValue(int value) {
        Node car = findByValue(value);
        car.next.prev = car.prev;
        car.prev.next = car.next;
    }

    private Node findByValue(int value) {

        for (Node car = sentinel.next; car != sentinel; car = car.next) {
            if (car.value == value) {
                return car;
            }

        }
        return null;

    }

    public void for_each() {

        for (Node car = sentinel.next; car != sentinel; car = car.next)

        {
            System.out.println(car.value);
        }

    }

}

class test3 {
    public static void main(String[] args) {
        CircularBidirectionalLinkedList test = new CircularBidirectionalLinkedList();
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(17);
        test.addFirst(18);
        test.addFirst(19);
        test .addLast(19);
        test .addLast(20);
        test .removeFirst();
        test.removeLast();
        test.removeByValue(17);
        test.for_each();
    }

}
