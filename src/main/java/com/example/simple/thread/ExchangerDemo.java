package com.example.simple.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 11:40 下午
 * 交换器，类似SynchronousQueue，但是交换器的数据流是双向的
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Producer p = new Producer(exchanger);
        Consumer c = new Consumer(exchanger);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(p);
        executorService.submit(c);
    }

    static class Producer implements Runnable{

        private Exchanger<Integer> exchanger;

        public Producer(Exchanger exchanger){
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try{
                int data = 1;
                System.out.println("交换前Producer-"+data);
                //exchange
                TimeUnit.SECONDS.sleep(2);
                Integer exchange = exchanger.exchange(data);
                System.out.println("交换后Producer-"+exchange);
            }catch (Exception e){

            }
        }
    }


    static class Consumer implements Runnable{
        private Exchanger<Integer> exchanger;

        public Consumer(Exchanger exchanger){
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try{
                int data = 0;
                System.out.println("交换前Consumer-"+data);
                //exchange
                TimeUnit.SECONDS.sleep(1);
                Integer exchange = exchanger.exchange(data);
                System.out.println("交换后Consumer-"+exchange);
            }catch (Exception e){

            }
        }
    }
}
