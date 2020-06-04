package site.javaee.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 店员 lock版，Condition 接口描述了可能会与锁有关联的条件变量，单个 Lock 可能与多个 Condition 对象关联。
 *
 * @author Tao
 * @create 2020/6/3 16:43
 */
public class ConditionClerkLockDemo {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    //Condition 实例实质上被绑定到一个锁上。要为特定 Lock 实例获得Condition 实例，请使用其 newCondition() 方法。
    private Condition condition = lock.newCondition();
    //进货-生产者A:
    public  void get() {
        lock.lock();

        try {
            //if：多个生产者进来后产生虚假唤醒，可以换成while多次判断product数量
            while (product >= 1) {
                System.out.println(Thread.currentThread().getName() + ": " +"产品已满");
                try {
                    /*为了避免兼容性问题，Condition 方法的名称与对应的 Object 版本中的不同。
                    Condition 对象中，与 wait、notify 和 notifyAll 方法对应的分别是await、signal 和 signalAll。*/
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } //else {//加else的问题：如果消费者线程先执行完而生产者线程等待，则无法唤醒生产者线程
            System.out.println(Thread.currentThread().getName() + "生产后数量: " + ++product);
            condition.signalAll();
            //}
        } finally {
            lock.unlock();
        }

    }

    //卖货-消费者B:
    public  void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println(Thread.currentThread().getName() + "消费后数量: " +"产品缺货");
                try {
                    condition .await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } //else {
            System.out.println(Thread.currentThread().getName() + ": " + --product);
            condition.signalAll();
            //}
        } finally {
            lock.unlock();
        }
    }
}
