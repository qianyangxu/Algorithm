package skipList;

import java.util.Random;

/**
 * 不固定层级的跳跃表实现
 * @param <T>
 */
public class SkipList<T> {

    private SkipListNode<T> head, tail;
    private int nodes; // 节点总数
    private int listLevel; // 层数
    private Random random; // 投掷硬币
    private static final double PROBABILITY = 0.5; // 向上提升的概率

    public SkipList() {
        random = new Random();
        clear();
    }

    // 清空跳表
    public void clear() {
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
        horizontalLink(head, tail);
        listLevel = 0;
        nodes = 0;
    }

    public boolean isEmpty() {
        return nodes == 0;
    }

    public int size() {
        return nodes;
    }

    /**
     * 水平双向连接
     */
    public void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.right = node2;
        node2.left = node1;
    }

    /**
     * 垂直双向连接
     * @param node1
     * @param node2
     */
    public void vertiacallLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }

    /**
     * node1后面插入node2
     * @param node1
     * @param node2
     */
    private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {

    }
}
