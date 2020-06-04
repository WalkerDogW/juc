package site.javaee.juc;

/**
 * volatile
 *
 * @author Tao
 * @create 2020/6/3 14:34
 */
public class LockDemo2 implements Runnable {
    private volatile int tickNum = 100;

    @Override
    public void run() {
        while (true) {
            if (tickNum > 0) {
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tickNum);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }

    }
}
