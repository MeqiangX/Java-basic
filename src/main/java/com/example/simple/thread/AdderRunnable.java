package com.example.simple.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 3:33 下午
 */
public class AdderRunnable implements Runnable {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5,this::run2);
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println("线程："+Thread.currentThread() + "到达障栅 1");
            // 如果任何一个在障栅上等待的线程离开了障栅。那么障栅就破坏了（中断或者等待超时），那些正在等待的线程立即终止await
            cyclicBarrier.await();
            System.out.println("线程："+Thread.currentThread() + "突破障栅 1");

            Thread.sleep(1000);
            System.out.println("线程："+Thread.currentThread() + "到达障栅 2");
            cyclicBarrier.await();
            System.out.println("线程："+Thread.currentThread() + "突破障栅 2");

            Thread.sleep(1000);
            System.out.println("线程："+Thread.currentThread() + "到达障栅 3");
            cyclicBarrier.await();
            System.out.println("线程："+Thread.currentThread() + "突破障栅 3");

            Thread.sleep(1000);
            System.out.println("线程："+Thread.currentThread() + "到达障栅 4");
            cyclicBarrier.await();
            System.out.println("线程："+Thread.currentThread() + "突破障栅 4");

            Thread.sleep(1000);
            System.out.println("线程："+Thread.currentThread() + "到达障栅 5");
            cyclicBarrier.await();
            System.out.println("线程："+Thread.currentThread() + "突破障栅 5");


        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("障栅结束=====");
    }

    private void doWork(){

    }

    private void run2() {
        System.out.println("最后达到最后障栅的线程会执行的方法："+Thread.currentThread());
    }

}
