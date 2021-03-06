package site.javaee.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**Fork/Join 框架：就是在必要的情况下，将一个大任务，进行拆分(fork)成 若干个小任务（拆到不可再拆时），再将一个个的小任务运算的结果进 行 join 汇总。
 * @author Tao
 * @create 2020/6/5 11:43
 */
public class ForkJoinTest {
    /*
    “工作窃取”模式（work-stealing）：
        当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加 到线程队列中，
        然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。

    fork/join框架的优势：
        体现在对其中包含的任务的处理方式上.在一般的线程池中，如果一个线程正在执行的任务由于某些原因无法继续运行，
        那么该线程会处于等待状态。而在fork/join框架实现中，如果某个子问题由于等待另外一个子问题的完成而无法继续运行。
        那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行.这种方式减少了线程的等待时间，提高了性能。
     */

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinDemo(0L, 100000000L);
        Long sum = (Long) forkJoinPool.invoke(task);
        System.out.println(sum);
    }
}
