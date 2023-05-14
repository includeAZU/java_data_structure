public class Link_list_by_recursion {
    Node head = null;// 头节点

    class Node {// 节点类
        int val;// 值
        Node next;// 下一节点

        Node(int val, Node node) {// 给节点赋值
            this.val = val;
            this.next = node;

        }

    }

    // 给你单链表的头节点head,请你反转链表，并返回反转后的链表。
    // 方法,建一个新链表,从旧链表取元素进行头插
    // 翻转链表
    public Node reverseList(Node o1)// 传入旧链表

    {
        Node n1 = null;// 新链表头节点
        Node p = o1;// 旧链表头节点
        n1 = o1;
        while (p != null) {
            n1 = new Node(p.val, n1);// 插入节点的下一个节点是n1
            p = p.next;

        }
        return n1;
    }

    // 方法2,和方法1类似,但是是移除,不是复制
    public void add_first(Node first)// 传一个节点
    {// 头插法
        Node p = first;
        p.next = head;
        head = p;

    }

    public void add(int val) {// 头插,传入一个数据
        Node p = new Node(val, null);// 新节点
        p.next = head;
        head = p;

    }

    public void print(Node head) {// 传头指针
        Node p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }

    }

    public Node remove_first(Node o1) {
        Node first = o1;
        if (first != null) {
            o1 = first.next;// 修改头指针
        }
        return first;
    }

    public Node reverseList1(Node o1) {
        Node p = o1;
        while (p != null) {
            add_first(remove_first(o1));
            p = p.next;
        }
        return p;
    }

    // 翻转链表,递归实现
    // 举个例子，假设原链表为 1->2->3->null，那么按照上述思路进行反转：

    // 递归调用 reverseList2(1)，返回的是 3->2->1->null，其中 last=3。
    // 对于节点 2，执行 p.next.next=p 操作，将其指向节点 1，然后将 p.next 设为 null。
    // 对于节点 1，执行与节点 2 相同的操作。
    // 返回旧链表的最后一个节点 3（即新链表的头节点），结束函数执行。
    public Node reverseList2(Node p)// 传入头指针
    {
        if (p == null || p.next == null) {// 说明是最后一个节点
            // 返回最后一个节点就是头节点
            return p;// p就是最后一个了

        }
        Node last = reverseList2(p.next);// 传回旧链表最后一个,就是新链表的头指针
        p.next.next = p;// 例如123,最后返回3,回到2时,p是4, p.next是5,想5指向4就是p.next .next =p;
        p.next = null;// 尾巴为空
        return last;// last 层层返回
    }

    // 翻转链表,双指针实现
    public Node reverseList3(Node o1)// 传入旧链表头指针
    {
        Node o2 = o1.next;// o2是o1的下一个节点
        Node n1 = o1;// n1是新节点
        if (o1 == null || o1.next == null) {
            return o1;
        }
        while (o2 != null) {
            o1.next = o2.next;// 断开o2节点
            n1 = o2;
            n1.next = o1;// n1是新节点
            o2 = o1.next;

        }
        return n1;

    }

    // 翻转链表,复制链表,面向过程
    public Node reverseList4(Node o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        Node n1 = null;// 新节点
        while (o1 != null) {
            Node o2 = o1.next;
            o1.next = n1;
            n1 = o1;// 新节点
            o1 = o2;

        }
        return n1;
    }

    // 根据值删除节点
    public Node removeElements(Node head, int val) {
        Node s = new Node(0, head);// 哨兵节点
        Node p1 = s;
        Node p2 = s.next;// p2指向被删元素
        while (p2 != null) {
            if (p2.val == val) {
                p1.next = p2.next;
                p2 = p2.next;

            } else {
                p1 = p1.next;
                p2 = p2.next;

            }

        }
        return s.next;// 哨兵的下一个节点
    }

    // 思路，递归函数负责返回：从当前节点（我）开始，完成删除的的链表
    // 1.若我与V相等，应该返回下一个节点递归结果
    // 2.若我与v不等，应该返回我，但我的next应该更新
    // 这是一个递归方法，用于删除链表中的指定元素。其中，p参数表示链表的头节点，val参数表示要删除的元素。算法实现如下：

    // 1.如果头节点p为空，则返回null。
    // 2.如果头节点p的值等于要删除的元素val，则将p指向下一个节点，并递归地调用removeElements1(p.next, val)方法。
    // 3.如果头节点p的值不等于要删除的元素val，则递归地调用removeElements1(p.next, val)方法，将返回值赋值给p.next。
    // 4.返回头节点p。

    // 该算法使用了递归方式进行链表遍历和删除操作，由于涉及到递归调用，因此应该注意控制递归深度。
    // 另外，由于原链表可能存在多个连续的要删除元素，因此需要进行多次递归调用以确保所有要删除的元素都被正确删除。
    public Node removeElements1(Node p, int val) {// p是头节点
        if (p == null) {
            return null;
        }
        if (p.val == val) {
            return removeElements2(p.next, val);
            // 有重复只删除第一个
        } else {

            p.next = removeElements1(p.next, val);
            return p;
        }
    }

    // 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
    // 递归实现\
    public int i = 0;

    // 变量i应该定义在方法体外，作为类级别的实例变量。如果将i定义在方法内
    // 则每次递归调用时都会重新赋值为0，递归过程中i的值无法正确累加，
    // 导致无法准确判断当前节点的下标是否等于要删除的元素的下标se。
    public Node removeElements2(Node p, int se) {
        // 删除不了第一个节点
        if (p == null) {
            return null;
        } else {
            p.next = removeElements2(p.next, se);
            i++;
            if (i == se) {
                return p.next;

            }

            return p;
        }

    }

    // 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
    // 快慢指针法
    public Node removeElements3(Node p, int se) {
        Node p1 = p;
        Node p2 = p;
        Node s = new Node(-1, this.head);
        for (int i = 0; i < se + 1; i++) {
            p2 = p2.next;

        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;

        }
        p1.next = p1.next.next;
        return s.next;

    }

    // 删除有序链表重复元素
    // 有序
    public Node removeElements4(Node p, int val) {
        Node p1 = p;
        Node p2 = p;

        if (p == null || p.next == null) {
            return p;
        }
        while (p2 != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;
                p2 = p2.next;

            } else {
                p1 = p1.next;
                p2 = p2.next;
            }

        }
        return p;

    }

    // 删除有序链表重复元素//递归实现
    // 重复元素一个不留
    public Node deleteDuplicates(Node p)

    {
        if (p == null || p.next == null)// 链表为空,链表只有两个数
        {
            return p;

        } else {
            // 若我与net重复，一直找到下一个不重复的节点，已它的返回结果为准
            if (p.val == p.next.val) {
                return deleteDuplicates(p.next.next);

            }
            p.next = deleteDuplicates(p.next);
            return p;
        }

    }

    // 删除有序链表重复元素//递归实现
    // 重复元素一个不留
    public Node deleteDuplicates1(Node p) {
        if (p == null || p.next == null) {
            return p;

        }
        if (p.val == p.next.val)

        {
            Node x = p.next.next; // 发现重复,直接跳过两个
            while (x != null && x.val == p.val)// 如果跳过两个之后还是重复
            {
                x = x.next;

            }
            return deleteDuplicates1(x);// 返回后面去重后的结果
        } else {
            p.next = deleteDuplicates1(p.next);
            return p;
        }

    }

    // 删除有序链表重复元素//
    // 重复元素一个不留//三指针法
    // p1：用来修改新链表的结构，最终返回的是p1的后继节点
    // p2：指向当前待处理的重复元素的子链表的头部
    // p3：指向当前待处理的重复元素的子链表的尾部（即第一个不同于p2节点的节点）
    public Node deleteDuplicates2(Node p) {
        if (p == null || p.next == null) {
            return p;
        }
        Node s = new Node(-1, p);
        Node p1 = s;
        Node p2 = s.next;
        Node p3 = s.next.next;
        while (p3.next != null) {
            while (p2.val == p3.val) {
                p3 = p3.next;

            }

            // p1.next = p3;
            // p2 = p3;
            // p1.next = p2;
            // p3 = p3.next;
            if (p2.next == p3) {// 没有重复值
                p1.next = p2;
                p1 = p1.next;
            }
            p2 = p3;
            p3 = p3.next;
        }
        p1.next = p2;

        return s.next;// 哨兵节点不是头节点

    }

    // 合并两个有序链表
    public Node mergeTwoList(Node p1, Node p2) {
        Node s = new Node(-1, null);// 新节点的哨兵节点
        Node p = s;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p1;
                p1 = p1.next;

            } else {

                p.next = p2;
                p2 = p2.next;

            }
            p = p.next;// 记录新链表节点

        }
        if (p1 != null) {
            p.next = p1;

        }
        if (p2 != null) {
            p.next = p2;

        }
        return s.next;

    }

    // 合并两个有序链表
    // 递归实现
    // 更小的那个链表节点，并把它剩余节点与另一个链表再次递归
    // 返回之前，更新此节点的next
    // mergeTwoLists(p1=[1,3,8,9],p2=[2,4]){
    // 1.next=mergeTwoLists(p1=[3,8,9],p2=[2,4]){
    // 2.next=mergeTwoLists(p1=[3,8,9],p2=[4])
    // 3.next=mergeTwoLists(p1=[8,9],p2=[4]){
    // 4.next=mergeTwoLists(p1=[8,9],p2=nu11){
    // return [8,9]
    // %
    // 3
    // return 4
    // 3
    // return 3
    // 3
    // return 2
    // 3
    // return 1
    // 3
    public Node mergeTwoList1(Node p1, Node p2) {

        if (p1 == null) {
            return p2;

        }
        if (p2 == null) {
            return p1;
        }

        if (p1.val < p2.val)//

        {
            p1.next = mergeTwoList1(p1.next, p2);// 返回小的
            return p1;

        } else {
            p2.next = mergeTwoList1(p1, p2.next);
            return p2;
        }

    }

    // 合并多个有序链表//两两合并
    // 递归实现
    public Node merbeList(Node[] list)// 存放每个链表的头指针

    {
        if (list.length == 0)// 没有元素需要合并

        {
            return null;
        } else {
            return spilt(list, 0, list.length);
        }

    }

    public Node spilt(Node[] list, int i, int j)// i,j 是左右边界

    {
        if (i == j) {// 只有一个数组
            return list[i];
        }
        int m = (i + j) >> 1;
        Node left = spilt(list, i, m);
        Node right = spilt(list, m + 1, j);
        return mergeTwoList(left, right);

    }

    // 查找链表中间节点
    // 快慢指针法
    public Node findMid(Node p) {
        Node p1 = p;
        Node p2 = p;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

        }
        return p1;
    }

    // 判断是否是回文结构
    public boolean ispalidrome(Node p)

    {// 截后半部分再翻转

        Node p1 = findMid(p);// 后半部分
        Node p2 = reverseList4(p1);
        while (p1 != null && p != null) {
            p = p.next;
            p2 = p2.next;
            if (p.val != p2.val) {
                return false;
            }

        }
        return true;

    }

    // 检测链表是否有环
    // ·设起点到入口走步a（本例是7），绕环一圈长度为b(本例是5)，
    // ·那么从起点开始，走ā+绕环n圈，都能找到环入口
    // ·第一次相遇时
    // o兔走了+绕环n圈（本例2圈）+k,k是它们相遇距环入口位置（本
    // 例3，不重要)
    // o龟走了+绕环n圈（本例0圈）+k,当然它绕的圈数比兔少
    // 。兔走的距离是龟的两倍，所以龟走的=兔走的-龟走的=绕环圈
    // ·而前面分析过，如果走+绕环n圈，都能找到环入口，因此从相遇点开始，
    // 再走a步，就是环入口
    // 阶段1
    // ·龟一次走一步，兔子一次走两步
    // ·当兔于能走到终点时，不存在环
    // ·当兔子能追上龟时，可以判断存在环
    // 阶段2
    // ·从它们第一次相遇开始，龟回到起点，兔子保持原位不变
    // 。龟和兔子一次都走一步
    // ·当再次相遇时，地点就是环的入口
    public boolean isRing(Node p)// 快慢指针法

    {
        Node p1 = p;// 一次一步
        Node p2 = p;// 一次两步
        while (p2 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;

            }
        }
        return false;

    }

    // 阶段2
    // ·从它们第一次相遇开始，龟回到起点，兔子保持原位不变
    // 。龟和兔子一次都走一步
    // ·当再次相遇时，地点就是环的入口
    public Node detectCycle(Node p) {// 不可以首尾相接
        Node p1 = p;// 一次一步
        Node p2 = p;// 一次两步

        while (p2 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2) {
                p1 = p;
                while (true) {
                    if (p1 == p2) {
                        return p1;
                    }

                    p1 = p1.next;
                    p2 = p2.next;

                }

            }
        }
        return null;
    }

}

class test5 {
    public static void main(String[] args) {
        Link_list_by_recursion test = new Link_list_by_recursion();
        test.add(13);
        test.add(13);
        test.add(11);
        test.add(4);
        test.add(2);
        test.add(2);
        test.add(2);
        test.add(2);
        test.add(1);
        test.deleteDuplicates2(test.head);
        test.print(test.head);

    }
}