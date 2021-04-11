package com.example.simple.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.lang.Boolean.FALSE;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 6:18 下午
 * 信号量，限制最大的并发访问数，并不能保证线程安全，需要结合锁
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SemaphoreTask semaphoreTask = new SemaphoreTask();
        for (int i = 0;i < 5;++i){
            executorService.submit(semaphoreTask);
        }

    }

    static class SemaphoreTask implements Runnable{

        // 可访问的线程数，false为非公平性，true则根据等待时间优先级来调度线程
        Semaphore semaphore = new Semaphore(2,FALSE);
        @Override
        public void run() {
            try{
                semaphore.acquire();
                System.out.println("线程获取到信号量，执行--"+Thread.currentThread());
                Thread.sleep(1000);
                System.out.println("线程执行完，释放信号量--"+Thread.currentThread());
                semaphore.release();

            } catch (InterruptedException e) {

            }
        }
    }

}
