package tree.BST;

import tree.TreeHelper;
import tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Java实现判断一棵树是否为BST二叉搜索树
 * 特征：
 *  节点左子树小于当前节点
 *  节点右子树大于当前节点
 *  所以左右子树也必须满足
 */
public class IsValidBST {
    public boolean isValidBSTRecursion(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {return true;}
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeHelper.createTree("[2,1,3]");
        IsValidBST isValidBST = new IsValidBST();
        System.out.println(isValidBST.isValidBST(treeNode));
    }
}
