package site.javaee.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tao
 * @create 2020/5/30 15:11
 */
public class CopyOnWriteDemo implements Runnable {
    //并发修改异常
    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it=list.iterator();
        while (it.hasNext()){
            System.out.println(Thread.currentThread().getName()+" - "+it.next());
            list.add("GG");
        }
    }
}
