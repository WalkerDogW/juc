package site.javaee.juc;

/**
 * volatile:当多个线程进行操作共享数据时，可以保证内存中的数据可见性
 *
 * @author Tao
 * @create 2020/5/28 10:55
 */
public class VolatileDemoTest {
    public static void main(String[] args) {
        /*
         内存可见性：
             内存可见性（Memory Visibility）是指当某个线程正在使用对象状态而另一个线程在同时修改该状态，
                需要确保当一个线程修改了对象状态后，其他线程能够看到发生的状态变化。(JVM会为每一个线程分配独立的缓存用于提高效率)。
             可见性错误是指当读操作与写操作在不同的线程中执行时，我们无法确保执行读操作的线程能适时地看到其他线程写入的值。
             我们可以通过同步来保证对象被安全地发布。
             也可以使用一种更加轻量级的 volatile 变量。
         */


        /*
         volatile与synchronized锁的异同：
             可以将volatile看成轻量级的锁，有同步功能，但volatile在底层不进行重排序。
             对于多线程，volatile不具备互斥性
             volatile不能保证变量状态的“原子性操作”
         */

        synchronizedd();
        volatileo();

    }

    /**
     * synchronized锁
     */
    public static void synchronizedd() {
        VolatileDemo td = new VolatileDemo();
        new Thread(td).start();
        System.out.println(Thread.currentThread().getName() + ": flag= " + td.isFlag());
        while (true) {
            //加锁
            synchronized (td) {
                if (td.isFlag()) {
                    System.out.println(Thread.currentThread().getName() + ": flag= " + td.isFlag());
                    break;
                }
            }
        }
    }

    /**
     * volatile
     */
    public static void volatileo() {
        VolatileDemo2 td2 = new VolatileDemo2();
        new Thread(td2).start();
        System.out.println(Thread.currentThread().getName() + ": flag= " + td2.isFlag());
        while (true) {
            if (td2.isFlag()) {
                System.out.println(Thread.currentThread().getName() + ": flag= " + td2.isFlag());
                break;
            }

        }
    }
}
