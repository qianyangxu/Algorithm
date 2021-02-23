package tree.RBTree;

import java.util.TreeMap;

/**
 * 红黑树
 * 规则:
 * 1.每个节点不是红色就是黑色
 * 2.根节点为黑色
 * 3.如果节点为红，其子节点必须为黑
 * 4.任一节点至nil的任何路径，所包含的黑节点数必须相同。
 * 5.叶子节点nil为黑色
 */
public class RBTree {

    private RBNode root;
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    /**
     * 红黑树节点数据结构
     * @param <K>
     * @param <V>
     */
    public class RBNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private boolean color;
        private RBNode<K, V> parent;
        private RBNode leftChild;
        private RBNode rightChild;

        public RBNode(K key, V value, boolean color, RBNode<K, V> parent, RBNode leftChild, RBNode rightChild) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    /**
     * 获取父节点
     * @param node
     * @return
     */
    public RBNode getParentNode(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

}
