package site.javaee.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要 求输出的结果必须按顺序显示
 *
 * @author Tao
 * @create 2020/6/3 16:43
 */
public class ConditionOrderLockDemo  {
    private int number = 1;//当前正在执行线程的标记
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void loopA(int tatolLoop){
        lock.lock();
        try {

            if(number != 1){
                conditionA.await();
            }

            for(int i=1; i<=1; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+tatolLoop);
            }

            number = 2;
            conditionB.signal();

        }catch (Exception e){

        } finally{
            lock.unlock();
        }
    }

    public void loopB(int tatolLoop){
        lock.lock();
        try {

            if(number != 2){
                conditionB.await();
            }

            for(int i=1; i<=1; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+tatolLoop);
            }

            number = 3;
            conditionC.signal();

        }catch (Exception e){

        } finally{
            lock.unlock();
        }
    }

    public void loopC(int tatolLoop){
        lock.lock();
        try {

            if(number != 3){
                conditionC.await();
            }

            for(int i=1; i<=1; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+tatolLoop);
            }

            number = 1;
            conditionA.signal();
        }catch (Exception e){

        } finally{
            lock.unlock();
        }
    }
}
