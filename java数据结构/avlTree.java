//如果一个节点的左右孩子，高度差超过1，则此节点失衡，才需要旋转
// AVL树
// ·二叉搜索树在插入和删除时，节点可能失衡
// ·如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，称为自平衡的二叉搜索树
// ·AVL是自平衡二叉搜索树的实现之一

// LL-右旋
// -失衡节点（图中5红色）的bf>1,即左边更高
// -失衡节点的左孩子（图中3黄色）的bf>=0即左孩子这边也是左边更高或等
// LR-左子树向左旋,失衡节点向右旋
// -失衡节点（图中6）的bf>1,即左边更高
// -失衡节点的左孩子（图中2红色）的bf<0即左孩子这边是右边更高
// RL-右子树向右旋,失衡节点向左旋
// -失衡节点（图中2）的bf<-1,即右边更高
// -失衡节点的右孩子（图中6红色）的bf>0，即右孩子这边左边更高
// RR-左旋
// 
// -失衡节点（图中2红色）的bf<-1,即右边更高
// -失衡节点的右孩子（图中6黄色）的bf<=0,即右孩子这边右边更高或等高

public class avlTree {
    avlNode root; // 根节点

    static class avlNode {// 静态
        int key;
        Object val;
        int hight = 1;
        avlNode left;
        avlNode right;

        avlNode(int key, Object val, avlNode left, avlNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;

        }

    }

    // 求某节点深度
    public int deep(avlNode node) {
        if (node == null) {
            return 0;
        }
        int a = deep(node.left);// 左子树高度
        int b = deep(node.right);// 右子树高度
        return Integer.max(a, b) + 1;// 只有一个节点,高度为1
    }

    // 更新节点高度
    public void updateDeep(avlNode node) {
        node.hight = deep(node);
    }

    // 平衡因子
    public int bf(avlNode node) {
        return deep(node.left) - deep(node.right);
    }

    // node 要旋转的节点
    // 右转
    public avlNode rightRotate(avlNode node) {
        avlNode left = node.left;
        avlNode a = left.right;
        left.right = node;
        node.left = a;
        updateDeep(node);
        updateDeep(left);
        return left;

    }

    // node 要旋转的节点
    // 左 转
    public avlNode leftRotate(avlNode node) {
        avlNode right = node.right;
        avlNode a = right.left;
        right.left = node;
        node.right = a;
        updateDeep(node);// 先更新下面节点高度
        updateDeep(right);// 最后更新更新根节点
        return right;

    }
    // LL-右旋
    // -失衡节点（图中5红色）的bf>1,即左边更高
    // -失衡节点的左孩子（图中3黄色）的bf>=0即左孩子这边也是左边更高或等
    // LR-左子树向左旋,失衡节点向右旋
    // -失衡节点（图中6）的bf>1,即左边更高
    // -失衡节点的左孩子（图中2红色）的bf<0即左孩子这边是右边更高
    // RL-失衡节点右子树向右旋,失衡节点向左旋
    // -失衡节点（图中2）的bf<-1,即右边更高
    // -失衡节点的右孩子（图中6红色）的bf>0，即右孩子这边左边更高
    // RR-左旋
    //
    // -失衡节点（图中2红色）的bf<-1,即右边更高
    // -失衡节点的右孩子（图中6黄色）的bf<=0,即右孩子这边右边更高或等高

    // 先左(左子树)再右 (失衡节点) LR
    public avlNode leftRightRotate(avlNode node) {

        node.left = leftRotate(node.left);

        return rightRotate(node);
    }

    // RL-失衡节点右子树向右旋,失衡节点向左旋
    public avlNode RightleftRotate(avlNode node) {

        node.right = rightRotate(node.right);

        return leftRotate(node);
    }

    private avlNode balance(avlNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) { // LL
            // 右旋
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            // 先左旋再右旋
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL

            return RightleftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { // RR
            return leftRotate(node);
        }
        return node;
    }

    // 添加
    public void put(int key, int val) {
        root = toPut(root, key, val);
    }
    // 递归

    public avlNode toPut(avlNode node, int key, int val) {
        if (node == null) {
            // 如果找到空位 返回新节点
            return new avlNode(key, val, null, null);
        }
        if (node.key == key) {
            node.val = val;
            return node;
        }
        // 没有找到
        if (node.key > key) {
            // 在左边
            node.left = toPut(node.left, key, val);
        }
        if (node.key < key) {
            // 在左边
            node.right = toPut(node.right, key, val);
        }
        // 找到了
        // 更新

        updateDeep(node);
        return balance(node);
    }

    // 根据key 删除节点
    public avlNode remove(avlNode node, int key) {// 返回删除节点后剩余的节点
                                                  // 删除结点不存在
        if (node == null) {
            return null;
        }
        // 找节点
        if (node.key > key) {
            node.left = remove(node.left, key);
        }
        if (node.key < key) {
            node.right = remove(node.right, key);
        } else {
            // 找到了
            if (node.left == null && node.right == null)

            {
                // 是叶子节点
                return null;
            }
            if (node.right == null) {
                // 只有左孩子
                node = node.left;
            }
            if (node.left == null) {
                // 只有左孩子
                node = node.right;
            } else {
                // 左右孩子都有
                // 找后继
                avlNode s = node.right;
                // 右子树最小的
                while (s.left != null) {
                    s = s.left;

                }
                // 处理 s孩子
                // 从node .right 删除 s.key
                s.right = remove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        updateDeep(node);
        // 5. balance
        return balance(node);

    }

    public static void main(String[] args) {
        avlTree test = new avlTree();
        avlNode n1 = null;
        avlNode n2 = null;
        avlNode n3 = null;
        avlNode n4 = null;
        avlNode n5 = null;
        avlNode n6 = null;
        avlNode n7 = null;
        avlNode n8 = null;
        n8 = new avlNode(15, 04, null, null);
        n1 = new avlNode(1, 2, null, null);
        n2 = new avlNode(3, 2, n1, null);
        n3 = new avlNode(5, 01, n2, null);
        n7 = new avlNode(13, 04, null, n8);
        n6 = new avlNode(11, 04, null, n7);
        n5 = new avlNode(9, 03, null, n6);

        n4 = new avlNode(7, 02, n3, n5);

        // 节点赋值
        System.out.println(test.deep(n4));

    }
}
