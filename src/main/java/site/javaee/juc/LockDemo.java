package site.javaee.juc;

/**
 * no lock
 *
 * @author Tao
 * @create 2020/6/3 14:34
 */
public class LockDemo implements Runnable {
    private int tickNum = 100;

    @Override
    public void run() {
        while (true) {
            if (tickNum > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tickNum);
            } else {
                break;
            }
        }

    }
}
