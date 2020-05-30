package site.javaee.juc;

/**
 * @author Tao
 * @create 2020/5/28 15:58
 */
public class CompareAndSwapDemo {
    private int value;

    /**
     * 获取内存值
     */
    public synchronized int get(){
        return value;
    }

    /**
     * 比较
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    /**
     * 设置值
     */
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue==compareAndSwap(expectedValue, newValue);
    }
}
