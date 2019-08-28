package com.base.learning.multithread;

class ThreadMethod extends Thread {
    /*
     * @program: java-learning
     * @description: TODO
     * @author: flowerlake
     * @create: 2019-07-04 10:11
     * @version: 1.0
     */

    private String name;

    ThreadMethod(String name) {
        this.name = name;
    }

    // 这里通过重写Thread类的run()方法达到起多线程的目的
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + ":" + i);
        }
        try {
            sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 这里就起了两个线程，线程的代码顺序和线程的执行顺序无关
        ThreadMethod threadClass1 = new ThreadMethod("AA");
        ThreadMethod threadClass2 = new ThreadMethod("BB");

        // 这里可以通过调用run方法来看看线程和非线程的区别，如果是调用run方法时，得到的结果是顺序执行的，
        // 但是start()方法是线程执行的方法，因此如果用多线程的方法就要使用start()
        // 注意：start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
        threadClass1.start();
        threadClass2.start();

    }
}


/*
* 学习文章：https://blog.csdn.net/evankaka/article/details/44153709
* 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
总结：
实现Runnable接口比继承Thread类所具有的优势：
1）：适合多个相同的程序代码的线程去处理同一个资源
2）：可以避免java中的单继承的限制
3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
*/