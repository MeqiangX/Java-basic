package com.example.simple.thread;

import java.util.concurrent.*;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 2:18 下午
 * 线程池 处理器
 */
public class ThreadPoolDemo {

    // Executors执行器的静态工厂方法获取不同的线程池对象

    // 通过线程池对象的submit(Runnable/Callable)来提交任务给线程池的线程执行

    // 如果是Callable可以通过Future来获取返回值，得到运行状态和计算结果

    public void executorsThreadFunction() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //Callable<String> callable = () -> "带缓存的线程池";
        Future<String> submit = cachedThreadPool.submit((Callable<String>) () -> "带缓存的线程池");
        try {
            System.out.println("result:" + submit.get());
        } catch (InterruptedException | ExecutionException e) {

        }

        // SingleThreadExecutor  总线程=核心线程数=1
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(() -> System.out.println("single thread executors===="));


        //fixedThreadPool 指定核心线程数
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        fixedThreadPool.submit(() -> System.out.println("fixedThreadPool======"));

        //scheduleThreadPool 延迟线程池  定时执行或者周期性执行的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        // 延迟5分钟执行
        Runnable delay = () -> System.out.println("延迟执行的线程池ScheduledThreadPool");
        scheduledExecutorService.schedule(delay,5, TimeUnit.MINUTES);
        scheduledExecutorService.submit(delay);
        //周期执行scheduleAtFixedRate 或者 scheduleWithFixedDelay
        Runnable rate = () -> System.out.println("周期执行任务");
        scheduledExecutorService.scheduleAtFixedRate(rate, 5, 1, TimeUnit.MINUTES);
        scheduledExecutorService.submit(rate);
    }

}
