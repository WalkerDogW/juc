package site.javaee.juc;

/**
 * 生产者
 *
 * @author Tao
 * @create 2020/6/3 16:48
 */
public class ConditionConsumerDemo implements Runnable{
    private ConditionClerkDemo clerk;

    public ConditionConsumerDemo(ConditionClerkDemo clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            clerk.sale();
        }
    }
}
