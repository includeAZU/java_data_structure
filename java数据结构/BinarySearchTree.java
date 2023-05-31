//1.树节点增加ky属性，用来比较谁大谁小，key不可以重复
//2.对于任意一个树节点，它的key比左子树(所有 )的ky都大，同时也比右子树的key都小

import java.util.ArrayList;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;
import javax.swing.text.html.parser.Element;

import Deque_link_list.node;

public class BinarySearchTree<T extends Comparable<T>> {
    // 如果 key 是范型 ,比较大小要使用 compare to 接口

    BSTtree<T> root; // 根节点

    static class BSTtree<T> {// 静态
        T key;
        Object val;
        BSTtree<T> left;
        BSTtree<T> right;

        BSTtree(T key, Object val, BSTtree<T> left, BSTtree<T> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;

        }

    }

    // 根据值查找节点
    // key 的值唯一

    public BSTtree<T> get(BSTtree<T> root, T key) {
        BSTtree<T> node = root;

        while (node != null) {
            if (node.key.compareTo(key) == -1) {
                node = node.right;
            }
            if (node.key.compareTo(key) >= 1) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;

    }

    public BSTtree<T> get1(BSTtree<T> root, T key) {
        if (root == null) {
            return null;
        }
        BSTtree<T> node = root;

        if (node.key.compareTo(key) == -1) {
            return get1(node.right, key);
        } else if (node.key.compareTo(key) >= 1) {
            return get1(node.left, key);
        } else {
            return node;
        }

    }

    // 查找key 最小的节点
    public BSTtree<T> min(BSTtree<T> root) {
        BSTtree<T> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 查找key 最小的节点
    public BSTtree<T> min1(BSTtree<T> root)

    {
        if (root == null) {
            return null;
        }

        if (root.left == null) {
            return root;
        }
        return min1(root.left);

    }

    // 查找key 最大的节点
    public BSTtree<T> max(BSTtree<T> root) {
        BSTtree<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 存储关键字和对应值
    // Params:key-关键字
    // value-值
    // 1.key有更新
    // 2.key没有 新增
    public void put(T key, Object val) {
        BSTtree<T> node = root;

        BSTtree<T> parent = null;
        while (node != null) {
            parent = node;// 记录上个节点
            if (node.key.compareTo(key) == -1) {
                node = node.right;
            }
            if (node.key.compareTo(key) >= 1) {
                node = node.left;
            } else {// 找到了
                node.val = val;

            }
        }
        // 没有找到 //新增
        if (parent == null) {
            root = new BSTtree<T>(key, val, null, null);
        }

        if (parent.key.compareTo(key) == -1) {
            parent.right = new BSTtree<T>(key, val, null, null);

        }
        if (parent.key.compareTo(key) >= 1) {
            parent.left = new BSTtree<T>(key, val, null, null);

        }

    }

    // 就是中序遍历
    // 节点有左子树，此时前驱节点就是左子树的最大值，图中属于这种情况的有
    // 2的前驱是1
    // 4的前驱是3
    // 6的前驱是5
    // 7的前驱是6
    // 节点没有左于树，若离它最近的祖先自从左而来，此祖先即为前驱，如
    // 3的祖先2自左而来，前驱2
    // 5的祖先4自左而来，前驱4
    // 8的祖先7自左而来，前驱7
    // 1没有这样的祖先，前驱null
    public Object predecessor(T key, BSTtree<T> root) {
        BSTtree<T> node = root;
        BSTtree<T> insfromleft = null;

        while (node != null) {
            // 先找到需要查找元素,再找前继
            if (node.key.compareTo(key) >= 1) {

                node = node.left;
            } else if (node.key.compareTo(key) == -1) {
                insfromleft = node;
                node = node.right;

            } else {// 找到了
                break;
            }
        }
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            // 节点有左子树，此时前驱节点就是左子树的最大值
            return max(node.left).key;
        }
        // 节点没有左于树，若离它最近的祖先自从左而来，此祖先即为前驱，
        return insfromleft != null ? insfromleft.key : null;

    }

    // 1.节点有右于树，此时后继节点即为右于树的最小值，如
    // 2的后继3
    // 3的后继4
    // 5的后继6
    // 7的后继8
    // 节点没有右于树，若离它最近的祖先自从右而来，此祖先即为后继，如
    // 1的祖先2自右而来，后继2
    // 4的祖先5自右而来，后继5
    // 6的祖先7自右而来，后继7
    // 8没有这样的祖先，后继null
    public Object posdecessor(T key, BSTtree<T> root) {
        BSTtree<T> node = root;
        BSTtree<T> insfromright = null;

        while (node != null) {
            // 先找到需要查找元素,再找前继
            if (node.key.compareTo(key) >= 1) {
                insfromright = node;
                node = node.left;
            } else if (node.key.compareTo(key) == -1) {

                node = node.right;

            } else {// 找到了
                break;
            }
        }
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            // 节点有右于树，此时后继节点即为右于树的最小值
            return min(node.right).key;
        }
        // 节点没有右于树，若离它最近的祖先自从右而来，此祖先即为后继
        return insfromright != null ? insfromright : null;

    }

    // 1.删除节点没有左孩子，将右孩于托孤给Parent
    // 2.删除节点没有右孩子，将左孩子托孤给Parent
    // 3.删除节点左右孩子都没有，已经被涵盖在情况1、情
    // 况2当中，把null托孤给Parent
    // 4.删除节点左右孩子都有，可以将它的后继节点（称为S)
    // 托孤给Parent,再称S的父亲为SP,又分两种
    // 情况
    // 1.SP就是被删除节点，此时D与S紧邻，只需将
    // S托孤给Parent
    // 2.SP不是被删除节点，此时D与S不相邻，此时
    // 需要将S的后代托孤给SP,再将S托孤给
    // Parent
    public void delete(T key, BSTtree<T> root) {
        BSTtree<T> node = root;
        BSTtree<T> parent = null;
        BSTtree<T> posParent = null;// 后继节点的父节点
        while (node != null) {
            // 先找到需要查找元素
            if (node.key.compareTo(key) >= 1) {
                parent = node;// 记录父节点
                node = node.left;
            } else if (node.key.compareTo(key) == -1) {
                parent = node;// 记录父节点
                node = node.right;

            } else {// 找到了
                break;
            }

        }
        if (node == null) {
            // 树为空
            return;
        }
        // 删除操作
        // node 需要删除的节点
        if (node.left == null && node.right != null) {
            // 删除节点没有左孩子，将右孩于托孤给Parent
            shift(parent, node, node.right);
        } else if (node.right == null && node.left != null) {
            // 删除节点没有右孩子，将左孩子托孤给Parent
            shift(parent, node, node.left);
        } else {
            // 左右孩子都有
            BSTtree<T> pos = (BSTtree<T>) posdecessor(key, node); // node 的后继节点
            if (node.right == pos) {
                // 后继节点一定在右边
                // 1.SP就是被删除节点，此时D与S紧邻，只需将
                // S托孤给Parent
                shift(parent, node, pos);

            } else {
                // 2.SP不是被删除节点，此时D与S不相邻，此时
                // 需要将S的后代托孤给SP,再将S托孤给
                // Parent

                if (pos.left == null && pos.right == null) {// 如果后继节点的左右孩子是空的//不相邻
                    node = pos;
                    pos = null;

                } else {
                    // 不相邻 ,且 左右子树不为空
                    // 寻找 被删除元素的后继节点的父亲
                    // 从node 开始 找
                    BSTtree<T> node1 = node;
                    while (node1 != null) {
                        // 寻找 被删除元素的后继节点的父亲
                        if (node1.key.compareTo(pos.key) >= 1) {
                            posParent = node1;// 记录父节点
                            node1 = node1.left;
                        } else if (node1.key.compareTo(pos.key) == -1) {
                            posParent = node1;// 记录父节点
                            node1 = node1.right;

                        } else {// 找到了
                            break;
                        }
                    }
                    // 处理后继节点的孩子

                    if (pos.left == null && pos.right != null) {
                        // 删除节点没有左孩子，将右孩于托孤给Parent
                        shift(posParent, pos, pos.right);
                        pos.right = node.right;
                    } else {
                        // 当后继节点有左子树时
                        BSTtree<T> posChild = pos.left; // 记录后继节点的左子树
                        shift(posParent, pos, posChild); // 将后继节点的左子树托孤给其父节点
                        posChild = node.left; // 将被删除节点的左子树接在后继节点的左子树上
                        pos.left = node.left;
                        if (pos.right != null) {
                            // 将被删除节点的右子树接在后继节点的右子树中的最左侧节点上
                            BSTtree<T> rightMost = getRightMost(pos.right);
                            rightMost.right = node.right;
                        }
                    }

                }
            }
        }
    }

    private BSTtree<T> getRightMost(BSTtree<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    // 删除节点

    public void shift(BSTtree<T> parent, BSTtree<T> delete, BSTtree<T> child) {
        if (parent == null) {
            // 删除头节点
            root = child;
        } else if (delete == parent.left) {
            // 需要删除节点在父节点左 边
            parent.left = child;

        } else if (delete == parent.right) {
            // 需要删除节点在父节点右 边
            parent.right = child;
        }

    }

    // 删除 递归 ,返回值是删除之后的子树
    public BSTtree<T> delete1(BSTtree<T> node, T key) {
        if (node == null) {
            return null;
        }

        // 查找节点
        if (node.key.compareTo(key) == -1) {
            // 需要查找的元素比 父节点要大
            node.right = delete1(node.right, key);
            return node;
        }
        if (node.key.compareTo(key) >= 1) {
            node.left = delete1(node.left, key);
            return node;
        }
        // 到这步 ,已经找到节点了
        if (node.right == null && node.left != null) {// 只有一个节点 ,左子树为空,或者右子树为空
            return node.left;
        }
        if (node.right != null && node.left == null) {
            return node.right;
        } else {
            // 有左右子树 ,找后继
            BSTtree<T> s = node.right;
            // 后继 ,右子树最小的
            while (s.left != null) {
                s = s.left;
            }
            s.right = delete1(node.right, s.key);
            s.left = node.left;
            return s;
        }

    }

    // 查询范围 //打印 小于key 的值
    // 中序遍历
    public ArrayList<T> less(BSTtree<T> root, T key) {
        BSTtree<T> node = root;
        Stack<BSTtree<T>> stack = new Stack<>();
        ArrayList<T> arr = new ArrayList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTtree<T> pop = stack.pop();
                if (pop.key.compareTo(key) == -1) {
                    arr.add(pop.key);// 小于

                }
                if (pop.key.compareTo(key) == 1) {
                    break;
                }
                node = pop.right;
            }
        }
        return arr;

    }

    // 查询范围 //打印 大于key 的值
    // 中序遍历
    public ArrayList<T> grater(BSTtree<T> root, T key) {
        BSTtree<T> node = root;
        Stack<BSTtree<T>> stack = new Stack<>();
        ArrayList<T> arr = new ArrayList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTtree<T> pop = stack.pop();
                if (pop.key.compareTo(key) == 1) {
                    // 大于
                    arr.add(pop.key);

                }

                node = pop.right;
            }
        }
        return arr;

    }

    // 验证 合法二叉搜素树
    public boolean isBinaryTree(BSTtree<T> root) {
        BSTtree<T> node = root;
        Stack<BSTtree<T>> stack = new Stack<>();
        int pre = Integer.MIN_VALUE;// 很小
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTtree<T> pop = stack.pop();
                if ((int) pop.key >= pre) {
                    // 大于
                    return false;

                }
                pre = (int) pop.key;
                node = pop.right;
            }
        }
        return true;

    }

    // 验证 合法二叉搜素树//递归实现
    // 中序遍历
    public boolean isBinaryTree1(BSTtree<T> root) {
        BSTtree<T> node = root;
        if (node == null) {
            return true;
        }

        int pre = Integer.MAX_VALUE;
        boolean a = isBinaryTree(node.left);
        if (pre > (int) node.key) {
            return false;
        }
        pre = (int) node.key;
        boolean b = isBinaryTree(node.right);
        return a && b;
    }

    // 验证 合法二叉搜素树//递归实现
    // 上下限判断法
    // min =Integer .MIN_VALUE;
    // max =Integer .MAX_VALUE;
    public boolean isBinaryTree2(BSTtree<T> root, int min, int max) {
        BSTtree<T> node = root;
        if (node == null) {
            return true;
        }

        if ((int) node.key < min || (int) node.key > max) {
            return false;
        }
        return isBinaryTree2(node.left, min, (int) node.key) && isBinaryTree2(node.right, (int) node.key, max);
    }

    // key 范围 和
    public int rangeadd(BSTtree<T> node, int min, int max)

    {
        if (node == null) {
            return 0;
        }
        if ((int) node.key < min) {
            return rangeadd(node.right, min, max);
        }
        if ((int) node.key > max) {
            return rangeadd(node.left, min, max);
        }
        return (int) node.key + rangeadd(node.left, min, max) + rangeadd(node.right, min, max);

    }
}

class test13 {

    public static void main(String[] args) {
        BinarySearchTree<Integer> test = new BinarySearchTree<>();
        BinarySearchTree.BSTtree<Integer> n4 = new BinarySearchTree.BSTtree<Integer>(6, 01, null, null);
        BinarySearchTree.BSTtree<Integer> n5 = new BinarySearchTree.BSTtree<Integer>(6, 01, null, null);
        BinarySearchTree.BSTtree<Integer> n6 = new BinarySearchTree.BSTtree<Integer>(6, 01, null, null);

        BinarySearchTree.BSTtree<Integer> n3 = new BinarySearchTree.BSTtree<Integer>(7, 02, n4, null);
        BinarySearchTree.BSTtree<Integer> n1 = new BinarySearchTree.BSTtree<Integer>(9, 03, null, null);
        BinarySearchTree.BSTtree<Integer> n2 = new BinarySearchTree.BSTtree<Integer>(8, 04, n3, n1);
        System.out.println(test.posdecessor(6, n2));
    }

}
