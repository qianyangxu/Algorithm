package skipList;

/**
 * https://www.jianshu.com/p/60d2561b821c
 * 跳表数据结构
 * Java API中提供了支持并发操作的跳跃表ConcurrentSkipListSet和ConcurrentSkipListMap。
 * 有序的情况下：
 *  在非多线程的情况下，应当尽量使用TreeMap（红黑树实现）。
 *  对于并发性相对较低的并行程序可以使用Collections.synchronizedSortedMap将TreeMap进行包装，也可以提供较好的效率。但是对于高并发程序，应当使用ConcurrentSkipListMap。
 * 无序情况下：
 *  并发程度低，数据量大时，ConcurrentHashMap 存取远大于ConcurrentSkipListMap。
 *  数据量一定，并发程度高时，ConcurrentSkipListMap比ConcurrentHashMap效率更高。
 * @param <T>
 */
public class SkipListNode<T> {
    public int key;
    public T value;
    public SkipListNode<T> up, down, left, right; // 上下左右四个指针
    public static final int HEAD_KEY = Integer.MAX_VALUE;
    public static final int TAIL_KEY = Integer.MIN_VALUE;

    public SkipListNode(int key, T value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof SkipListNode<?>)) {
            return false;
        }

        SkipListNode<T> ent;

        try {
            ent = (SkipListNode<T>) o; // 检测类型
        } catch (ClassCastException ex) {
            return false;
        }
        return (ent.getKey() == key) && (ent.getValue() == value);
    }


    @Override
    public String toString() {
        return "key-value:" + key + "-" + value;
    }

}
