package com.base.learning.multithread;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class CallThreadMethod {
    /*
     * @program: java-learning
     * @description: 使用callable、线程池executor、future来创建可以返回值的线程。如果对一些场合需要线程返回的结果。
     * 就要使用用Callable、Future、FutureTask、CompletionService这几个类。Callable只能在ExecutorService的线程池中跑，但有返回结果，也可以通过返回的Future对象查询执行状态。Future 本身也是一种设计模式，它是用来取得异步任务的结果
     * @author: flowerlake
     * @create: 2019-07-06 11:08
     * @version: 1.0
     */

    static class CallThread implements Callable<Object> {
        private String taskName;

        CallThread(String name) {
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 2;
        // 创建一个线程池
        ExecutorService executorServicePool = Executors.newFixedThreadPool(taskSize);
        // 创建具有返回值的任务
        Callable callThreadMethod1 = new CallThread("AAa");
        Callable callThreadMethod2 = new CallThread("BBA");

        // way-1:执行任务并获取 Future对象
        Future future1 = executorServicePool.submit(callThreadMethod1);
        Future future2 = executorServicePool.submit(callThreadMethod1);

        System.out.println("通过方式1获得的10的和为：" + future1.get().toString());
        System.out.println("通过方式1获得的10的和为：" + future2.get().toString());

        // =============================================
        // way-2 通过invokeAll执行任务
        List<Future<Object>> futureList = executorServicePool.invokeAll(asList(new CallThread("AAAA"), new CallThread("BBB")));

        // 关闭线程池
        executorServicePool.shutdown();

        for (Future<Object> item : futureList) {
            System.out.println("通过方式2获得的结果："+item.get());
        }

    }

}


