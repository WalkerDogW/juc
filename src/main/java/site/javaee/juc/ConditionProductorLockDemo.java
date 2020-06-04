package site.javaee.juc;

/**
 * 生产者
 *
 * @author Tao
 * @create 2020/6/3 16:48
 */
public class ConditionProductorLockDemo implements Runnable{
    private ConditionClerkLockDemo clerk;

    public ConditionProductorLockDemo(ConditionClerkLockDemo clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}
