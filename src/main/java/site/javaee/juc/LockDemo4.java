package site.javaee.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 实现了 Lock 接口，并提供了与 synchronized 相同的互斥性和内存可见性。
 * 但相较于 synchronized 提供了更高的处理锁的灵活性
 *
 * @author Tao
 * @create 2020/6/3 14:34
 */
public class LockDemo4 implements Runnable {
    private int tickNum = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tickNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tickNum);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            } finally {
                //记得在finally释放锁
                lock.unlock();
            }
        }
    }
}
