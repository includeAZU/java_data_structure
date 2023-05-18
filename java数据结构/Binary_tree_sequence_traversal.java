
/*
 *         1
 *        / \
 *       2   3  
 * 
 * 
 */
//二叉树层序遍历
import java.util.Iterator;


 class treeNode {
    int val;
    treeNode left;
    treeNode right;

    treeNode(int val, treeNode left, treeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;

    }

}

class test7 {
    public static void main(String[] args) {
        myqueue_link_list<treeNode> test = new myqueue_link_list<>();
        treeNode root = new treeNode(1,
                new treeNode(2, new treeNode(4, null, null),
                        new treeNode(5, null, null)),
                new treeNode(3, new treeNode(6, null, null),
                        new treeNode(7, null, null)));
        test.offer(root);
        int c1 = 1;
        while (!test.isEmpty()) {
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                treeNode n = test.poll();
                System.out.print("  " + n.val);
                if (n.left != null) {
                    test.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    test.offer(n.right);
                    c2++;
                }
            }
            System.out.println();
c1=c2;
        }
    }

}

class myqueue_link_list<E> implements Iterable<E> {
    // 范型实现
    // 下面以单向环形带哨兵链表方式来实现队列
    private static class Node<E>

    {
        E val;
        Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;

        }

    }

    // 开始时,头节点和尾节点都指向哨兵节点
    // 哨兵节点即是头节点也是尾节点
    // shaobing
    // 在这里哨兵节点就是头节点
    Node<E> head = new Node<E>(null, null);
    Node<E> tail = head;
    int size;
    int capacity = Integer.MAX_VALUE;// 默认是很大的

    myqueue_link_list(int capacity) {// 重载
        // 有参构造 人为地设置队列容量
        this.capacity = capacity;
        tail.next = head;
    }

    myqueue_link_list() {
        tail.next = head;

    }

    // 从尾巴添加节点
    public boolean offer(E val) {// 新节点
        Node<E> p = new Node<E>(val, head);
        tail.next = p;
        tail = p;
        size++;
        if (size == capacity

        ) {
            return false;
        }
        return true;

    }

    public E peek() {
        // 获取第一个元素的值
        if (head == tail) {
            return null;
        } else
            return head.next.val;
    }

    public boolean isEmpty() {

        return head == tail;

    }

    public E poll() {// 获取第一个节点的值,并删除//不是哨兵节点
        if (head == tail) {
            return null;
        }

        Node<E> first = head.next;
        head.next = first.next;
        size--;
        if (first == tail)// 只有一个元素时,尾节点被删除
        {
            tail = head;
        }
        return first.val;

    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;// 从哨兵节点的下一个开始迭代

            public boolean hasNext() {
                // 判断下个元素是否是头节点
                return p.next != head;
            }

            // 怎么返回值
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }

        };
    }
}
