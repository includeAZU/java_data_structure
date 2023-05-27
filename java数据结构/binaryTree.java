import java.nio.ReadOnlyBufferException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.html.parser.Element;

public class binaryTree {

    static class treeNode<E> {

        E  val;
        treeNode<E> left;
        treeNode<E> right;

        treeNode(E val, treeNode<E> left, treeNode<E> right) {
            this.val = val;
            this.left = left;
            this.right = right;

        }

    }

    // 前序遍历规则
    // 先访问该节点
    // 然后是左子树
    // 最后是右子树
    // 前序遍历
    public static void preorder(treeNode<Integer > node) {
        //是整数
        System.out.println(node.val + "  ");
        if (node.left != null) {
            preorder(node.left);

        }
        if (node.right != null) {
            preorder(node.right);
        }
        return;

    }

    // 前序遍历
    // 非递归实现
    public static void preorder1(treeNode<Integer> node)

    {
        treeNode<Integer> cur = node;
        stack_link_list<treeNode<Integer > > test = new stack_link_list<>(20);
        while (cur != null || !test.isEmpty()) {

            if (cur != null) {
                System.out.println(cur.val);
                test.push(cur);
                cur = cur.left;
            } else {
                treeNode<Integer> pop = test.pop().value;// 给了整个节点
                // System.out.println(pop.val);
                cur = pop.right;

            }

        }

    }

    // 中序遍历

    public static void inorder(treeNode<Integer > node) {

        if (node == null) {
            return;
        }
        inorder(node.left);

        System.out.println(node.val + "  ");

        inorder(node.right);

    }

    // 中序遍历
    // 非递归
    public static void inorder1(treeNode<Integer> node)

    {
        treeNode<Integer> cur = node;
        stack_link_list<treeNode<Integer>> test = new stack_link_list<>(20);
        while (cur != null || !test.isEmpty()) {

            if (cur != null) {
                // System.out.println(cur.val);
                test.push(cur);// 压栈
                cur = cur.left;
            } else {
                treeNode<Integer > pop = test.pop().value;
                System.out.println(pop.val);
                cur = pop.right;

            }

        }

    }

    // 后序遍历
    public static void posorder(treeNode<Integer > node) {

        if (node == null) {
            return;
        }
        posorder(node.left);

        posorder(node.right);
        System.out.println(node.val + "  ");
    }

    // 后序遍历
    // 非递归实现
    public static void posorder1(treeNode<Integer >  node) {
        treeNode<Integer >  cur = node;
        stack_link_list<treeNode<Integer > > test = new stack_link_list<>(20);
        treeNode<Integer >  pop = null;
        while (cur != null || !test.isEmpty()) {

            if (cur != null) {
                // System.out.println(cur.val);
                test.push(cur);// 压栈
                cur = cur.left;
            } else {
                treeNode<Integer >  peek = test.peek().value;
                if (peek.right == null || peek.left == pop) {
                    pop = test.pop().value;
                    System.out.println(pop.val);
                } else {
                    cur = pop.right;
                }

            }

        }

    }

    // 判断是否是 对称二叉树
    public static boolean isSymmetry(treeNode<Integer >  p) {
        return detection(p.left, p.right);
    }

    public static boolean detection(treeNode<Integer >  left, treeNode<Integer >  right)

    {
        if (left == null && right == null) {

            return true;// 只有一个元素
        }
        if (right == null || left == null)

        {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return detection(left.left, right.right) && detection(left.right, right.left);

    }

    // 二叉树深度 -后序遍历实现
    public int deep(treeNode<Integer >  p) {
        // 如果树为空
        if (p == null) {
            return 0;
        }
        int a = deep(p.left);
        int b = deep(p.right);
        return Integer.max(a, b) + 1;

    }

    // 求最小深度
    public int minDeep(treeNode<Integer >  p) {
        // 如果树为空
        if (p == null) {
            return 0;
        }
        int a = minDeep(p.left);
        int b = minDeep(p.right);

        if (a == 0) {
            // 如果 左子树为空,以右子树为基准
            return b + 1;

        }
        if (b == 0) {
            // 如果 右子树为空,以左子树为基准
            return a + 1;

        }
        return Integer.min(a, b) + 1;

    }

    // 二叉树深度 -后序遍历实现 //非递归
    public int deep1(treeNode<Integer >  root) {
        treeNode<Integer >  cur = root;// 头节点
        treeNode<Integer >  pop = null;// 节点的 右子树是否为空,或者是上一次弹出栈的值,就是右子树所有值都被弹出了
        stack_link_list<treeNode<Integer > > stack = new stack_link_list<>(10);
        int max = 0;// 记录栈的最大深度
        while (cur != null || !stack.isEmpty()) {
            if (cur != null)// 遍历左子树
            {
                stack.push(cur);// 遍历过的元素放进栈中
                cur = cur.left;
                int size = stack.size;
                // 还有右子树会一直添加
                if (size > max) {
                    max = size;
                }

            } else {
                treeNode<Integer >  peek = stack.peek().value;// 弹出上面 元素 //左子树最后 一个元素

                if (peek.right == null || peek.right == pop) {// 右子树没有,或者刚刚弹出
                    pop = stack.pop().value;
                } else {
                    cur = peek.right;
                }
            }

        }
        return max;

    }

    // 二叉树深度 -层遍历 实现
    // 先放一个元素,出队(一个一个出),判断出队是否有 左右孩子,有就入队,
    // 然后一个元素出队,判断出队是否有 左右孩子,有就入队
    public int deep2(treeNode<Integer >  root) {
        if (root == null) {
            return 0;
        }
        Queue<treeNode<Integer > > queue = new LinkedList<>(null);
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                treeNode<Integer >  poll = queue.poll();// 出队
                // System.out.print(poll.val + "\t");
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            // System.out.println();
            depth++;
        }
        return depth;

    }

    // 翻转二叉树
    // 左右 翻转
    public treeNode<Integer >  invertTree(treeNode<Integer >  root) {
        swap(root);
        return root;
    }

    public void swap(treeNode<Integer >  root) {
        if (root == null) {
            return;
        }
        treeNode<Integer >  a = root.left;
        root.left = root.right;
        root.right = a;
        swap(root.left);
        swap(root.right);

    }

    /*
     * 根据后缀表达式构造表达式树
     * 1.遇到数字入栈
     * 2.遇到运算符出栈，建立节点关系，再入栈
     * 21-3
     * ..
     * *
     * / \
     * - 3
     * / \
     * 2 1
     * 
     */
    public treeNode<String > constructExpressionTree(String[]tokens){


    }
    public static void main(String[] args) {
        treeNode<Integer > root = new treeNode(1,
                new treeNode(1, new treeNode(1, null, null),
                        new treeNode(1, null, null)),
                new treeNode(1, new treeNode(1, null, null),
                        new treeNode(1, null, null)));
        // 节点赋值
        System.out.println(isSymmetry(root));

    }

}

// 使用链表的方式实现

// 栈
class stack_link_list<E> {
    node<E> s = new node<>(null, null);// 哨兵节点,添加元素时在头部添加
    int size = 0;
    int capacity;

    stack_link_list(int capacity) {
        this.capacity = capacity;

    }

    static class node<E> {
        // 只有class node没有使用非本类属性,才可以使用 static
        E value;
        node<E> next;// 指向下一节点

        public node(E value, node<E> node) {
            this.value = value;
            this.next = node;

        }
    }

    // 向栈顶压入元素
    // Params:value-待压入值
    // Returns:压入成功返回true,否则返回false
    // s ->1 ->2
    public boolean push(E e) {
        if (isEmpty()) {
            return false;
        }
        s.next = new node<>(e, s.next);
        size++;
        return true;
    }

    // 从栈中弹出元素
    // Returns:栈非空返回栈顶元素，栈为空返回nul
    public node<E> pop() {
        if (s.next == null) {
            return null;
        }
        node<E> first = s.next;
        s.next = first.next;
        size--;
        return first;

    }

    // 返回栈顶元素，不弹出
    // Returns:栈非空返回栈顶元素，栈为空返回null
    public node<E> peek() {
        return s.next;

    }

    public boolean isEmpty() {
        return size == 0 || s.next == null;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void print() {
        node<E> p = s.next;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }

    }

}