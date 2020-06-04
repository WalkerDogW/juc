package site.javaee.juc;

/**
 * @author Tao
 * @create 2020/6/4 20:09
 */
public class EightLockTest {
    public static void main(String[] args) {
        /*
            非静态方法的锁默认为this，静态方法的锁为对应的Class实例(NumberDemo.Class)
            某一个时刻内，只能有一个线程持有锁，无论几个方法。
         */

        //one();
        two();
        //three();
        //four();
        //five();
        //six();
        //seven();
        //eight();


        /*
        • 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用 其中的一个synchronized方法了，
            其它的线程都只能等待，换句话说，某一个时刻 内，只能有唯一一个线程去访问这些synchronized方法
        • 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的 synchronized方法
        • 加个普通方法后发现和同步锁无关
        • 换成两个对象后，不是同一把锁了，情况立刻变化。
        • 都换成静态同步方法后，情况又变化
        • 所有的非静态同步方法用的都是同一把锁——实例对象本身，也就是说如果一个实 例对象的非静态同步方法获取锁后，
            该实例对象的其他非静态同步方法必须等待获 取锁的方法释放锁后才能获取锁，
            可是别的实例对象的非静态同步方法因为跟该实 例对象的非静态同步方法用的是不同的锁，
            所以毋须等待该实例对象已获取锁的非 静态同步方法释放锁就可以获取他们自己的锁。
        • 所有的静态同步方法用的也是同一把锁——类对象本身，这两把锁是两个不同的对 象，
            所以静态同步方法与非静态同步方法之间是不会有竞态条件的。但是一旦一个 静态同步方法获取锁后，
            其他的静态同步方法都必须等待该方法释放锁后才能获取 锁，而不管是同一个实例对象的静态同步方法之间，
            还是不同的实例对象的静态同 步方法之间，只要它们同一个类的实例对象！
         */
    }

    /**
     * 1、两个普通同步方法，两个线程，标准打印 1 2 / 2 1
     */
    public static void one() {
        NumberDemo numberDemo = new NumberDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getTwo();
            }
        }).start();
    }

    /**
     * 2、给getOne()增加Thread.sleep()方法
     */
    public static void two() {
        NumberDemo2 numberDemo = new NumberDemo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getTwo();
            }
        }).start();
    }

    /**
     * 3、新增普通方法getThree()
     */
    public static void three() {
        NumberDemo3 numberDemo = new NumberDemo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getThree();
            }
        }).start();
    }


    /**
     * 4、两个普通同步方法，两个对象
     */
    public static void four() {
        NumberDemo2 numberDemo = new NumberDemo2();
        NumberDemo2 numberDemo2 = new NumberDemo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo2.getTwo();
            }
        }).start();
    }

    /**
     * 5、一个静态同步方法，一个普通同步方法
     */
    public static void five() {
        NumberDemo5 numberDemo = new NumberDemo5();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getTwo();
            }
        }).start();

    }

    /**
     * 6、两个静态同步方法
     */
    public static void six() {
        NumberDemo6 numberDemo = new NumberDemo6();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getTwo();
            }
        }).start();


    }

    /**
     * 7、一个静态同步方法，一个普通同步方法,两个对象
     */
    public static void seven() {
        NumberDemo5 numberDemo = new NumberDemo5();
        NumberDemo5 numberDemo2 = new NumberDemo5();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo2.getTwo();
            }
        }).start();

    }


    /**
     * 8、两个静态同步方法，两个对象
     */
    public static void eight() {
        NumberDemo6 numberDemo = new NumberDemo6();
        NumberDemo6 numberDemo2 = new NumberDemo6();
        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberDemo2.getTwo();
            }
        }).start();


    }
}


class NumberDemo {
    public synchronized void getOne() {
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }


}


class NumberDemo2 {
    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

}


class NumberDemo3 {
    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("Three");
    }
}

class NumberDemo5 {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

}

class NumberDemo6 {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

}


