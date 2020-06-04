package site.javaee.juc;

/**
 * ReadWriteLock 维护了一对相关的锁，一个用于只读操作， 另一个用于写入操作。只要没有 writer，读取锁可以由 多个 reader 线程同时保持。写入锁是独占的。
 *
 * @author Tao
 * @create 2020/6/4 19:23
 */
public class ReadWriteLockTest {
    /*
    ReadWriteLock 读取操作通常不会改变共享资源，但执行 写入操作时，必须独占方式来获取锁。
    对于读取操作占 多数的数据结构，ReadWriteLock 能提供比独占锁更高 的并发性。
    而对于只读的数据结构，其中包含的不变性 可以完全不需要考虑加锁操作。
     */
    public static void main(String[] args) {
        readWrite();

    }

    public static void readWrite(){
        ReadWeiteLockDemo rw = new ReadWeiteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set((int)(Math.random()*100));
            }
        }).start();
        for(int i=0; i<100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rw.get();
                }
            }).start();
        }
    }
}
