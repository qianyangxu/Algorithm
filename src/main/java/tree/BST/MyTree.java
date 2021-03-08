package tree.BST;

public class MyTree {

    private static TreeNode root = null;

    public TreeNode createTreeNode(TreeNode i, TreeNode j) {
        //把将要添加的结点与根结点比较，若小于根结点就放在根结点左边
        //(Object不能用于比较大小，int可以)
        if (i.getObj() < j.getObj()) {
            //如果被比较结点的左边为空
            if (j.getLeft() == null) {
                //添加结点
                j.setLeft(i);
                i.setParent(j);
            } else {
                //递归，继续添加
                createTreeNode(i, j.getLeft());
            }
        } else {
            //如果被比较结点的右边为空
            if (j.getRight() == null) {
                //添加结点
                j.setRight(i);
                i.setParent(j);
            } else {
                //递归，继续添加
                createTreeNode(i, j.getRight());
            }

        }
        //返回根结点
        return root;
    }

    public void add(int i) {
        TreeNode fat = null;
        //定义一个空指针
        //创建新结点，取出数组中的值添加到新结点中
        TreeNode newNode = new TreeNode(i);
        if (root == null) {//根结点为空
            root = newNode;//直接赋值给根结点
        } else {
            fat = root;
            createTreeNode(newNode, fat);//调用方法
        }
    }

    public void printTree(TreeNode root) {
        if (null != root) {//根结点不为空
            //得到左边的下一个结点
            TreeNode left = root.getLeft();
            printTree(left);//递归
            //得到根结点的数据
            Object data = root.getObj();
            //输出数据
            System.out.println(data);
            //得到右边的下一个结点
            TreeNode right = root.getRight();
            printTree(right);//递归
        }
    }

    public static void main(String[] args) {
        //给出数组
        int[] a = {6, 3, 1, 5, 8, 9, 7, 2, 7};
        //创建对象
        MyTree mt = new MyTree();
        //遍历数组，将数组转成二叉树
        for (int i = 0; i < a.length; i++) {
            mt.add(a[i]);
        }
        //遍历二叉树
        mt.printTree(root);
    }
}
