package site.javaee.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Tao
 * @create 2020/5/30 16:23
 */
public class CountDownLatchDemo implements Runnable {
    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                countDownLatch.countDown();
            }
        }


    }
}
