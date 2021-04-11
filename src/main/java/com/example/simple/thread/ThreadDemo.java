package com.example.simple.thread;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.*;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 9:14 上午
 */
public class ThreadDemo {

    // runnable无返回线程
    public void runnableTest(){
        Runnable runnable = () -> System.out.println("this runnable thread: " + Thread.currentThread());
        new Thread(runnable).start();
    }

    //future Callable有返回线程，Future用来接收异步返回的计算结果，而Callable是业务需要实现的有返回的线程类
    //future中有get,isDone,isCancelled,Cancel这些阻塞的获取计算结果的方法
    public void futureTest(){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1+1;
            }
        };
        // FutureTask包装器 可以将Callable转换为Future和Runnable,他实现了RunnableFuture(同时继承了Runnable和Future)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            // 阻塞方法，可能会被中断Interrupter
            Integer result = futureTask.get();
            System.out.println("result:"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        Future<Integer> future = new Future<Integer>() {
//            @Override
//            public boolean cancel(boolean mayInterruptIfRunning) {
//                return false;
//            }
//
//            @Override
//            public boolean isCancelled() {
//                return false;
//            }
//
//            @Override
//            public boolean isDone() {
//                return false;
//            }
//
//            @Override
//            public Integer get() throws InterruptedException, ExecutionException {
//                return null;
//            }
//
//            @Override
//            public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                return null;
//            }
//        }
    }

}
