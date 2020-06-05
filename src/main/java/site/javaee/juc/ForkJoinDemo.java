package site.javaee.juc;

import java.util.concurrent.RecursiveTask;

/**
 * @author Tao
 * @create 2020/6/5 11:44
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;

    private static final long THRESHOLD = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end-start;
        if(length <= THRESHOLD){
            long sum = 0L;
            for(long i=start; i<=end; i++){
                sum += i;
            }
            return sum;
        }else {
            long middle = (start+end)/2;
            ForkJoinDemo left=new ForkJoinDemo(start,middle);
            left.fork();

            ForkJoinDemo right=new ForkJoinDemo(middle+1,end);
            right.fork();


            return left.join()+right.join();
        }
    }
}
