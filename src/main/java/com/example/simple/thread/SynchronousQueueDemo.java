package com.example.simple.thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/11 7:21 下午
 * 同步队列将生产者和消费者配对，当一个线程调用offer生产时候，他会阻塞直到另一个线程调用take消费，反之一样，如果消费者线程调用take，队列中没有产品时候，则色
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        // 生产者消费者
        // SynchronousQueue实现了BlockingQueue阻塞队列，但是他没有包含任何元素，size为0。所以他不是概念上的队列
        // 作用域不同线程之间的数据传递，和Exchanger不同，数据流向一个方向
        SynchronousQueue queue = new SynchronousQueue<Integer>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        fixedThreadPool.submit(consumer);
        fixedThreadPool.submit(producer);
    }


    static class Producer implements Runnable{
        private SynchronousQueue queue;
        public Producer(SynchronousQueue synchronousQueue){
            this.queue = synchronousQueue;
        }


        @Override
        public void run() {
            while(true){
                try{
                    int product = (int)(100 * Math.random());
                    System.out.println("生产者生产了一个产品===="+product);

                    Thread.sleep(3000);
                    System.out.println("等待了3S将产品运输了出去-----");
                    queue.offer(product);
                    System.out.println("产品生产完成："+product);
                }catch (Exception e){

                }
            }

        }
    }



    static class Consumer implements Runnable{
        private SynchronousQueue queue;
        public Consumer(SynchronousQueue synchronousQueue){
            this.queue = synchronousQueue;
        }
        @Override
        public void run() {
            while(true){
                //消费
                try{
                    int product = (int) queue.take();
                    System.out.println("消费者 消费产品-----:"+product);
                }catch (Exception e){

                }
            }

        }
    }

}
