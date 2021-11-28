package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {
    
    public static void main(String[] args) {
        
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 单线程
        // 方法一 Runnable
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                doSomething(start);
            };
        };
        Thread thread1 = new Thread(runnableTask, "thread1");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 方法二 Callable
        Callable callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                doSomething(start);
                return null;
            }
        };
        FutureTask futureTask = new FutureTask<>(callableTask);
        Thread thread2 = new Thread(futureTask);
        thread2.setName("thread2");
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 

        // 方法三 继承Thread
        class MyThread extends Thread {
            @Override
            public void run() {
                doSomething(start);
                super.run();
            }
        }
        MyThread thread3 = new MyThread();
        thread3.setName("thread3");
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 

        // 线程池
        // 方法四 Executors.newFixedThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            executorService.submit(() -> {
                doSomething(start);
            });
            // executorService.submit(callableTask);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        

        // 方法五 Executors.newScheduledThreadPool
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        try {
            scheduledExecutorService.schedule(runnableTask, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scheduledExecutorService.shutdown();
        }

        // 方法六 Executors.newCachedThreadPool
        ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
        try {
            cachedExecutorService.submit(callableTask);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cachedExecutorService.shutdown();
        }

        // 方法七 Executors.newSingleThreadExecutor
        ExecutorService singExecutorService = Executors.newSingleThreadExecutor();
        try {
            singExecutorService.execute(runnableTask);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singExecutorService.shutdown();
        }


        // 然后退出main线程
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("退出main线程");
    }

    private static void doSomething(long start) {
        int result = sum(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println(Thread.currentThread().getName() +  " 使用时间："+ (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
