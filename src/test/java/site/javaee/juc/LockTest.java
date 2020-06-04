package site.javaee.juc;

/**
 * 同步锁Lock是显式锁，需要通过lock()上锁，然后通过unlock()释放锁
 * synchronized:隐式锁，能同步代码块和方法
 *
 * @author Tao
 * @create 2020/6/3 11:12
 */
public class LockTest {
    public static void main(String[] args) {

        //noLock();
        //volatileo();
        //synchronize();
        reentrantLock();
    }

    /**
     * 没上锁存在多线程问题
     */
    public static void noLock(){
        LockDemo ticket = new LockDemo();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }

    /**
     * volatile不能保证变量状态的“原子性操作”
     */
    public static void volatileo(){
        LockDemo2 ticket  = new LockDemo2();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }

    /**
     * synchronized
     */
    public static void synchronize(){
        LockDemo3 ticket  = new LockDemo3();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }

    /**
     * ReentrantLock
     */
    public static void reentrantLock(){
        LockDemo4 ticket  = new LockDemo4();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}
