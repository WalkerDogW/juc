package site.javaee.juc;

/**
 * @author Tao
 * @create 2020/6/5 8:32
 */
public class ThreadPoolDemo implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<5; i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
