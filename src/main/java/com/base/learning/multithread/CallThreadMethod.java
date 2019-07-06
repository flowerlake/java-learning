package com.base.learning.multithread;

import java.util.Date;
import java.util.concurrent.*;

import static com.sun.xml.internal.ws.client.ContentNegotiation.none;

public class CallThreadMethod implements Callable<Object> {
    /*
     * @program: java-learning
     * @description: 使用callable、线程池executor、future来创建可以返回值的线程
     * @author: flowerlake
     * @create: 2019-07-06 11:08
     * @version: 1.0
     */
    private String taskName;

    CallThreadMethod(String name) {
        this.taskName = name;
    }

    @Override
    public Object call() throws Exception {

        System.out.println(Thread.currentThread().getName() + " start");

        int sum = 0;
        Date date1 = new Date();
        for (int i = 0; i < 10; i++) {
            sum = sum + i;
            System.out.println(taskName + ":" + i);
        }
        Date date2 = new Date();
        long time = date2.getTime() - date1.getTime();

        System.out.println(Thread.currentThread().getName() + " end");

        return sum;
    }
}

class CallMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 5;
        // 创建一个线程池
        ExecutorService executorServicePool = Executors.newFixedThreadPool(taskSize);
        // 创建具有返回值的任务
        Callable callThreadMethod1 = new CallThreadMethod("AAa");
        Callable callThreadMethod2 = new CallThreadMethod("BBA");

        // 执行任务并获取 Future对象
        Future future1 = executorServicePool.submit(callThreadMethod1);
        Future future2 = executorServicePool.submit(callThreadMethod2);
        // 关闭线程池
        executorServicePool.shutdown();

        System.out.println("10的和为：" + future1.get().toString());
        System.out.println("10的和为：" + future2.get().toString());
    }

}


