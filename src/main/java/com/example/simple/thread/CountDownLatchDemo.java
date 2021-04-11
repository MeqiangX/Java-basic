package com.example.simple.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: DruidQiang
 * 倒计时门闩，CountDownLatch 不可重复用，调用countDown使计数器-1。await()在计数器为0前会一直阻塞
 * 这样可以使用多个倒计时门闩来完成多个线程集之间的顺序执行
 * 这里以跑步竞赛为例子
 * 1、=
 * @Date: 2021/4/11 5:14 下午
 */
public class CountDownLatchDemo {

    /**
     * 裁判门闩
     */
    final static CountDownLatch refereeLatch = new CountDownLatch(1);

    /**
     * 运动员门闩
     */
    final static CountDownLatch athleteLatch = new CountDownLatch(5);

    public static void main(String[] args) {

        // 运动员开跑
        Runnable athleteThread = () -> {
            // 等待发号施令完成
            try{
                Thread.sleep(1000);
                refereeLatch.await();
                System.out.println("当前运动员开始跑===="+Thread.currentThread());
                // 跑完
                athleteLatch.countDown();
            }catch (Exception e){

            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i =0;i < 5;++i) {
            executorService.submit(athleteThread);
        }
        try{
            // 教练发号
            System.out.println("教练发号施令主线程："+Thread.currentThread());
            refereeLatch.countDown();
            // 结束，裁判统计
            athleteLatch.await();
            System.out.println("所有 运动员都完成了，教练统计======"+Thread.currentThread());
        }catch (Exception e){

        }

    }


}
