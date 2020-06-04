package site.javaee.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Tao
 * @create 2020/6/4 19:24
 */
public class ReadWeiteLockDemo {
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int number) {
        lock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName() + ":Write:" + number);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
