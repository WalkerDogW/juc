package site.javaee.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过Callable接口创建线程，相较于Runnable接口，方法可以有返回值，并且可以抛出异常
 *
 * @author Tao
 * @create 2020/5/30 17:02
 */
public class CallableTest {
    /*
     创建执行线程的方式有4种
         继承Thread类，重写run方法（其实Thread类本身也实现了Runnable接口）
         实现Runnable接口，重写run方法
         实现Callable接口，重写call方法（有返回值）
         使用线程池（有返回值）
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        callable();
    }


    public static void callable() throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        //Callable 需要依赖FutureTask(Future接口的实现)，FutureTask用于接收运算结果 ， 也可以用作闭锁。
        FutureTask<Integer> result = new FutureTask<>(callableDemo);
        new Thread(result).start();
        //接收线程运行的结果
        System.out.println("result = " + result.get());
    }
}
