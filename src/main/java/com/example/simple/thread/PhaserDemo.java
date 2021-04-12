package com.example.simple.thread;

import sun.jvm.hotspot.opto.Phase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/12 10:14 下午
 * 和cyclicBarrier类似，分阶段周期来控制多线程集，但是他比障栅更灵活，可以动态控制计数器（Phaser中叫做party）
 * 通过register()来使计数器+1
 * 通过arriveAndDeregister()使计数器-1
 * 通过arriveAndAwaitAdvance()来阻塞进程，直到所有的party都到达arrive
 * 可以通过重写onAdvance(int phase,int registeredParties) 来控制多个周期
 * 比如：int phase表示当前为第几个周期，registeredParties为当前的计数器数
 * when phase = 0时候 处于第一个给阶段，1 为第二个阶段 2 为第三个阶段 等等，可以分别处理不同的业务
 *
 */
public class PhaserDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        PhaserSub phaserSub = new PhaserSub();
        PhaserFunction phaserFunction = new PhaserFunction(phaserSub);

        for (int i = 0;i < 4;++i){
            // 注册表示phaser维护的线程个数
            phaserSub.register();
            executorService.submit(phaserFunction);
        }
        executorService.shutdown();
    }

    static class PhaserFunction implements Runnable{

        private PhaserSub phaserSub;

        public PhaserFunction(PhaserSub s){
            this.phaserSub = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "参加预赛");
            phaserSub.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread() + "参加初赛");
            phaserSub.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread() + "参加半决赛");
            phaserSub.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread() + "参加决赛");
            phaserSub.arriveAndAwaitAdvance();

        }
    }

}
