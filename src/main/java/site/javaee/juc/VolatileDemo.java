package site.javaee.juc;

import lombok.Data;

/**
 * no volatile
 *
 * @author Tao
 * @create 2020/5/27 19:08
 */
@Data
public class VolatileDemo implements Runnable {
    private boolean flag = false;

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
