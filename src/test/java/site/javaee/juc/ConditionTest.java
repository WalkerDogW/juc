package site.javaee.juc;

/**
 * Condition 接口描述了可能会与锁有关联的条件变量。这些变量在用 法上与使用 Object.wait 访问的隐式监视器类似，但提供了更强大的 功能。
 *
 * @author Tao
 * @create 2020/6/3 16:39
 */
public class ConditionTest {
    public static void main(String[] args) {
        //synchronize();
        //reentrantlock();
        order();
    }

    /**
     * 使用synchronized处理生产者消费者模型
     */
    public static void synchronize(){
        ConditionClerkDemo clerk = new ConditionClerkDemo();
        ConditionProductorDemo product = new ConditionProductorDemo(clerk);
        ConditionConsumerDemo consumer = new ConditionConsumerDemo(clerk);
        new Thread(product,"生产者A").start();
        new Thread(consumer,"消费者A").start();
        new Thread(product,"生产者B").start();
        new Thread(consumer,"消费者B").start();
    }

    /**
     * reentrantlock版生产者消费者模型
     */
    public static void reentrantlock() {
        ConditionClerkLockDemo clerk = new ConditionClerkLockDemo();
        ConditionProductorLockDemo product = new ConditionProductorLockDemo(clerk);
        ConditionConsumerLockDemo consumer = new ConditionConsumerLockDemo(clerk);
        new Thread(product,"生产者A").start();
        new Thread(consumer,"消费者A").start();
        new Thread(product,"生产者B").start();
        new Thread(consumer,"消费者B").start();
    }

    /**
     * lock线程按序交替
     */
    public static void order(){
        ConditionOrderLockDemo orderDemo = new ConditionOrderLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=20; i++){
                    orderDemo.loopA(i);
                }
                System.out.println("-----------------");
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=20; i++){
                    orderDemo.loopB(i);
                }
                System.out.println("-----------------");
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=20; i++){
                    orderDemo.loopC(i);
                }
                System.out.println("-----------------");
            }
        },"C").start();
    }
}
