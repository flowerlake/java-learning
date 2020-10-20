package com.base.learning.multithread;

import static java.lang.Thread.sleep;

class RunnableThreadMethod implements Runnable {
    /*
     * @program: java-learning
     * @description: 通过runnable实现多线程
     * @author: flowerlake
     * @create: 2019-07-04 10:28
     * @version: 1.0
     */

    private String name;

    RunnableThreadMethod(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "运行开始");
        for (int i = 0; i < 10; i++) {
            System.out.println(name + ":" + i);

            if (i == 8 ) {
//                System.out.println("yield start");
                // yield只是从程序上控制线程的运行状态，即将其在程序层面上转为可运行状态也就是就绪状态，但是硬件层面CPU调度还是有
                // 可能将同时处于可运行状态中的该线程拿出来跑。
//                Thread.yield();
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
        try {
            sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "运行开始");

        // Thread2类通过实现Runnable接口，使得该类有了多线程类的特征。run（）方法是多线程程序的一个约定。
        // 所有的多线程代码都在run方法里面。Thread类实际上也是实现了Runnable接口的类。
        RunnableThreadMethod runnableThreadMethod1 = new RunnableThreadMethod("C");
        RunnableThreadMethod runnableThreadMethod2 = new RunnableThreadMethod("D");

        // 在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，
        // 然后调用Thread对象的start()方法来运行多线程代码。
        // 实际上所有的多线程代码都是通过运行Thread的start()方法来运行的。因此，不管是扩展Thread类还是
        // 实现Runnable接口来实现多线程，最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。
        Thread thread1 = new Thread(runnableThreadMethod1);
        thread1.start();
        new Thread(runnableThreadMethod2).start();


        try {
            // 主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "运行结束");
    }
}
