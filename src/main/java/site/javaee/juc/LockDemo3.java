package site.javaee.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized
 *
 * @author Tao
 * @create 2020/6/3 14:34
 */
public class LockDemo3 implements Runnable {
    private int tickNum = 100;

    @Override
    public void run() {
        /*
         *synchronize有一个缺陷，就是如果同步的方法死循环了或者执行时间过长了，会影响系统的正常运行。
         * 那么为了将这种风险降到最低，一般我们使用类锁的时候，不会直接synchronize(xx.class)或者static synchronized去锁住这个类的所有同步方法，
         * 而是用先在这个类里面声明了一个对象实例：Object object=new Object()，然后再synchronize(object)。
         * 那么这个方法加锁的对象是object这个对象，当一个线程执行这个方法时，这对其他线程要执行这个类的其他同步方法是没有影响的，
         * 只会影响到要执行用synchronize(object)锁住的方法，因为他们持有的锁都完全不一样。

         */

        while (true) {
            synchronized (this) {
                if (tickNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tickNum);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }

    }
}
