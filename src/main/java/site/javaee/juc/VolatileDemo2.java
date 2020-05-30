package site.javaee.juc;

import lombok.Data;

/**
 * volatile
 *
 * @author Tao
 * @create 2020/5/27 19:08
 */
@Data
public class VolatileDemo2 implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(Thread.currentThread().getName() + ": flag= " + isFlag());
    }


}
