package site.javaee.juc;

import java.util.concurrent.Callable;

/** 通过Callable接口创建线程，相较于Runnable接口，方法可以有返回值，并且可以抛出异常
 * @author Tao
 * @create 2020/5/30 17:02
 */
public class CallableDemo  implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i=0; i<=100; i++){
            System.out.println(i);
            sum +=i;
        }
        return sum;
    }
}

/**
 * Runnable 不会返回结果，并且无法抛出经过检查的异常。
 */
class  RunnableDemo implements  Runnable{
    @Override
    public void run() {
    }
}
