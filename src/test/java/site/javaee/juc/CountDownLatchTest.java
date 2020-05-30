package site.javaee.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 闭锁，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待，
 * 即只有其他线程的运算全部完成时，当前运算才继续执行。
 *
 * @author Tao
 * @create 2020/5/30 16:19
 */
public class CountDownLatchTest {
    /*
     闭锁可以延迟线程的进度直到其到达终止状态，可用来确保某些活动直到其他活动都完成才继续执行：
         确保某个计算在其需要的所有资源都被初始化之后才继续执行;
         确保某个服务在其依赖的所有其他服务都已经启动之后才启动;
         等待直到某个操作所有参与者都准备就绪再继续执行。
     */

    public static void main(String[] args) throws InterruptedException {
        countDownLatch();
    }

    public static void countDownLatch() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo(countDownLatch);

        long start = System.currentTimeMillis();

        for(int i=0; i<5; i++){
            new Thread(countDownLatchDemo).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("time= "+(end-start));
    }
}
