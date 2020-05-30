package site.javaee.juc;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile
 *
 * @author Tao
 * @create 2020/5/28 9:38
 */
@Data
public class AtomicDemo implements Runnable {
    private volatile int serialNumber = 0;


    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber++;
    }

}
