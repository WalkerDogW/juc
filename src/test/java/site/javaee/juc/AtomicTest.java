package site.javaee.juc;

/**
 * CAS (Compare-And-Swap) 是多处理器对并发的支持，用于管理对共享数据的并发访问，是一种无锁的非阻塞算法的实现。
 *
 * @author Tao
 * @create 2020/5/28 10:37
 */
public class AtomicTest {
    public static void main(String[] args) {

        volatileo();

        //atomic();

    }

    /**
     * volatile处理不了i++的原子性问题
     */
    public static void volatileo(){
        /*
         i++ 的原子性问题：
            //i++的操作实际上分为读-改-写三个步骤
            i = i++;
            //等价于
            int temp = i;
            i = i + 1;
            i = temp;

         */
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 100; i++) {
            new Thread(ad).start();
        }

        System.out.println("-------------------------------");
    }

    /**
     * 原子变量
     */
    public static void atomic() {
         /*
         原子变量：jdk1.5后juc包下的atomic包下提供了常用的原子变量
             原子变量都用volatile修饰，保证内存可见性
             CAS(Compare-And-Swap)算法保证数据的原子性，
                CAS包含三个操作数，内存值V 预估值A 更新值B，
                仅当V==A时，V=B，如果V!=A，则不更新
         */
        AtomicDemo2 ad2 = new AtomicDemo2();
        for (int i = 0; i < 100; i++) {
            new Thread(ad2).start();
        }

        System.out.println("-------------------------------");
    }

    /**
     * CAS算法模拟
     */
    public static void cas() {
        final  CompareAndSwapDemo cas = new CompareAndSwapDemo();
        for(int i=0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue =  cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random()*101));
                }
            }).start();
        }
    }
}
