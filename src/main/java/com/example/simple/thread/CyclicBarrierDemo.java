package com.example.simple.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 3:25 下午
 * 障栅 -- 大量线程运行在一次线程的不同部分的情况，当所有部分都准备好时，需要把结果组合起来，就需要设置障栅
 * 当一个线程完成了他的那部分，就把它运行到障栅处，一旦所有的线程都达到了这个障栅，障栅就赊销，线程就可以继续运行。
 * 所有线程都到达后，才能执行后面的逻辑，并且障栅是循环的，可以在所有被等待线程被释放后重用
 */
public class CyclicBarrierDemo {

    public static void cyclicBarrierFunction(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        AdderRunnable adderRunnable = new AdderRunnable();
        for (int i = 0; i < 5;++i){
            fixedThreadPool.submit(adderRunnable);
        }
    }

    public static void main(String[] args) {
        cyclicBarrierFunction();
    }

}
