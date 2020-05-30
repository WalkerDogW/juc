package site.javaee.juc;

/**
 * Java 5.0 在 java.util.concurrent 包中提供了多种并发容器类来改进同步容器的性能。
 * 当期望许多线程访问一个给定 collection 时，
 * ConcurrentHashMap 通常优于同步的 HashMap，
 * ConcurrentSkipListMap 通常优于同步的 TreeMap。
 * 当期望的读数和遍历远远大于列表的更新数时，CopyOnWriteArrayList 优于同步的 ArrayList。
 *
 * @author Tao
 * @create 2020/5/28 16:27
 */
public class ConcurrentHashMapTest {
    /*
    HashMap和HashTable底层都是Hash表，HashMap线程不安全，
    HashTable线程安全，但独占锁效率低且存在复合操作上的安全问题


    ConcurrentHashMap 同步容器类是Java 5 增加的一个线程安全的哈希表。
    对与多线程的操作，性能介于 HashMap 与 Hashtable 之间。
    内部采用“锁分段”(默认16段)机制替代 Hashtable 的独占锁，进而提高性能。

    java8取消了分段锁，改成了CAS（无所算法）
     */

    public static void main(String[] args) {
        //copyOnWrite();
        copyOnWrite2();
    }

    /**
     * synchronizedList
     */
    public static void copyOnWrite() {
        /*
        写入时复制（CopyOnWrite，简称COW）思想是计算机程序设计领域中的一种优化策略。
        其核心思想是，如果有多个调用者（Callers）同时要求相同的资源（如内存或者是磁盘上的数据存储），
        他们会共同获取相同的指针指向相同的资源，直到某个调用者视图修改资源内容时，
        系统才会真正复制一份专用副本（private copy）给该调用者，而其他调用者所见到的最初的资源仍然保持不变。
        这过程对其他的调用者都是透明的（transparently）。
        此做法主要的优点是如果调用者没有修改资源，就不会有副本（private copy）被创建，
        因此多个调用者只是读取操作时可以共享同一份资源。
         */
        CopyOnWriteDemo copyOnWriteDemo = new CopyOnWriteDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(copyOnWriteDemo).start();
        }
    }

    /**
     * CopyOnWriteArrayList，添加操作多时，效率低，因为每次添加时都会进行复制，开销非常大，并发迭代多时可以提高效率。
     */
    public static void copyOnWrite2() {
        /*
        实现原理:在添加的时候是需要加锁的，内部持有一个ReentrantLock lock = new ReentrantLock()，否则多线程写的时候会Copy出N个副本出来。
        读的时候不需要加锁，如果读的时候有多个线程正在向CopyOnWriteArrayList添加数据，读还是会读到旧的数据，因为写的时候不会锁住旧的CopyOnWriteArrayList。

        为什么CopyOnWriteArrayList并发安全且性能比Vector好
        Vector是增删改查方法都加了synchronized，保证同步，但是每个方法执行的时候都要去获得锁，性能就会大大下降，
        而CopyOnWriteArrayList 只是在增删改上加锁，但是读不加锁，在读方面的性能就好于Vector，CopyOnWriteArrayList支持读多写少的并发情况。
         */
        CopyOnWriteDemo2 copyOnWriteDemo2 = new CopyOnWriteDemo2();
        for (int i = 0; i < 3; i++) {
            new Thread(copyOnWriteDemo2).start();
        }
    }

}
