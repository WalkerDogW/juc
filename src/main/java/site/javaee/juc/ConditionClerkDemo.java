package site.javaee.juc;

/**
 * 店员
 *
 * @author Tao
 * @create 2020/6/3 16:43
 */
public class ConditionClerkDemo {
    private int product = 0;

    //进货-生产者A:
    public synchronized void get() {
        //if：多个生产者进来后产生虚假唤醒，可以换成while多次判断product数量
        while (product >= 1) {
            System.out.println(Thread.currentThread().getName() + ": " +"产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } //else {//加else的问题：如果消费者线程先执行完而生产者线程等待，则无法唤醒生产者线程
            System.out.println(Thread.currentThread().getName() + "生产后数量: " + ++product);
            this.notifyAll();
        //}
    }

    //卖货-消费者B:
    public synchronized void sale() {
        while (product <= 0) {
            System.out.println(Thread.currentThread().getName() + "消费后数量: " +"产品缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } //else {
            System.out.println(Thread.currentThread().getName() + ": " + --product);
            this.notifyAll();
        //}
    }
}
