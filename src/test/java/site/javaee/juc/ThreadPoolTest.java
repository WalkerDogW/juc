package site.javaee.juc;

import java.util.Random;
import java.util.concurrent.*;

/**线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁线程的额外开销，提高了响应速度。
 * @author Tao
 * @create 2020/6/5 8:32
 */
public class ThreadPoolTest {
    /*
     第四种获取线程的方法：线程池
        一个 ExecutorService，它使用可能的几个池线程之一执行每个提交的任务，通常使用 Executors 工厂方法配置

     线程池可以解决两个不同问题：
        由于减少了每个任务调用的开销，它们通常可以在 执行大量异步任务时提供增强的性能，并且还可以提供绑定和管理资源（包括执行 任务集时使用的线程）的方法。
        每个 ThreadPoolExecutor 还维护着一些基本的统计数 据，如完成的任务数。

     线程池的体系结构：
        java.util.concurrent.Executor：负责线程的使用与调度的根接口
            --ExecutorService：子接口，线程池的主要接口
                --ThreadPoolExecutor：线程池的实现类
                --ScheduleExecutorService：子接口，负责线程的调度
                    --ScheduleThreadPoolExecutor：实现了ScheduleExecutorService，继承了ThreadPoolExecutor，既能做线程池，又能调度

     工具类：Executors
         Executors.newCachedThreadPool()（线程池大小不定，可以根据需求自动更改数量，并进行自动线程回收）
         Executors.newFixedThreadPool(int)（固定大小线程池）
         Executors.newSingleThreadExecutor()（单个后台线程）
        ScheduleExecutorService new ScheduleThreadPoolExecutor():创建固定大小的连接池，可以延迟或定时的执行任务
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runnable();
        //callable();
        scheduled();
    }

    private static void scheduled() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledPool = new ScheduledThreadPoolExecutor(5);
        for(int i=0; i<10; i++){
            Future<Integer>  future= scheduledPool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    return num;
                }
            }, 3, TimeUnit.SECONDS);
            System.out.println(future.get());
        }

        scheduledPool.shutdown();

    }

    private static void callable() throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        for(int i=0; i<10; i++){
            Future<Integer> future =  fixedThreadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for(int i=0; i<100; i++){
                        sum+=i;
                    }
                    System.out.println(Thread.currentThread().getName());
                    return sum;
                }
            });

            System.out.println(future.get());
        }


        fixedThreadPool.shutdown();
    }

    public static void runnable(){
        //创建线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        //为线程池中的线程分配任务
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        for(int i=0; i<10; i++){
            fixedThreadPool.submit(threadPoolDemo);
        }


        //关闭线程池
        fixedThreadPool.shutdown();
    }
}
