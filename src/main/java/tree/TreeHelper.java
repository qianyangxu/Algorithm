package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHelper {
    static int index;
    static String[] values;

    public TreeHelper() {
    }

    public static void setValues(String treeValues) {
        values = treeValues.split(",");
    }

    public static TreeNode createTree(String src) {
        src = src.substring(1, src.length() - 1);
        String[] strList = src.split(",");
        TreeNode root;
        TreeNode result = null;
        Queue<TreeNode> queue = new LinkedList<>();
        for (int i = 0; i < strList.length; i++) {
            if (i == 0) {
                root = new TreeNode(Integer.parseInt(strList[i]));
                result = root;
                queue.add(root);
            }
            if (!queue.isEmpty()) {
                root = queue.poll();
            } else {
                break;
            }
            if (i + 1 < strList.length && !strList[i + 1].equals("null")) {
                root.left = new TreeNode(Integer.parseInt(strList[i + 1]));
                queue.add(root.left);
            }
            if (i + 2 < strList.length && !strList[i + 2].equals("null")) {
                root.right = new TreeNode(Integer.parseInt(strList[i + 2]));
                queue.add(root.right);
            }
            i = i + 1;
        }
        return result;
    }

    private static TreeNode createBinaryTreeByArray(Integer[] array, int index) {
        TreeNode treeNode;
        if (index < array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            treeNode = new TreeNode(value);
            treeNode.left = createBinaryTreeByArray(array, 2 * index + 1);
            treeNode.right = createBinaryTreeByArray(array, 2 * index + 2);
            return treeNode;
        }
        return null;
    }

    public static void show(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeHeight(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static int getTreeHeight(TreeNode treeNode) {
        if (null == treeNode) return 0;
        return 1 + Math.max(getTreeHeight(treeNode.left), getTreeHeight(treeNode.right));
    }

    public static void DLR(TreeNode treeNode) {
        if (null == treeNode) return;
        System.out.print(treeNode.val + " ");
        DLR(treeNode.left);
        DLR(treeNode.right);
    }

    public static void LDR(TreeNode treeNode) {
        if (null == treeNode) return;
        LDR(treeNode.left);
        System.out.print(treeNode.val + " ");
        LDR(treeNode.right);
    }

    public static void LRD(TreeNode treeNode) {
        if (null == treeNode) return;
        LRD(treeNode.left);
        LRD(treeNode.right);
        System.out.print(treeNode.val + " ");
    }

    public static TreeNode createTree(Integer[] array, int index) {
        TreeNode treeNode;
        if (index < array.length) {
            Integer value = array[index];
            if (null == value) return null;
            treeNode = new TreeNode(value);
            treeNode.left = createTree(array, 2 * index + 1);
            treeNode.right = createTree(array, 2 * index + 2);
            return treeNode;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeHelper.createTree("[1,2,3,4,5,6,7,8,9]");
        TreeHelper.show(treeNode);
        System.out.println("===========");
        Integer[] arr = new Integer[]{1,3,7,2,4,6,8};
        TreeNode root = createBinaryTreeByArray(arr, 0);
        TreeHelper.show(root);

        TreeHelper.DLR(root);
        System.out.println();

        TreeHelper.LDR(root);
        System.out.println();

        TreeHelper.LRD(root);
        System.out.println();

    }
}
