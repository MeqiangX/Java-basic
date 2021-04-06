package com.example.simple.model;

import lombok.Builder;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private double[] account;

    private Lock blockLock = new ReentrantLock();

    public Account(int size,double initialValue){
        account = new double[size];
        Arrays.fill(account,initialValue);
    }

    public double getTotal(){
        double t = 0;
        for (double v : account) {
            t += v;
        }
        return t;
    }

    /**
     * 转账业务
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from,int to,double amount){
        if (account[from] < amount){
            return;
        }
        // 转账
        blockLock.lock(); //资源竞争枷锁 和synchronized差不多，synchronized基于jvm,reentrantlock基于jdk的封装，synchronized更简单基层，reentrantlock更灵活
        try{
            System.out.println("当前线程：" + Thread.currentThread());
            account[from] -= amount;
            System.out.printf("from %d to %d -> %10.2f",from,to,amount);
            account[to] += amount;
            System.out.printf("total: %10.2f",getTotal());
            // -= / += 都不是原子操作，都可能被其他线程中断
            // -= 可以分成 1、account[from]放到寄存器中，2、寄存器执行自减，3、最后赋值到account[from]
            // 如果有多个线程同时操作这个方法，对于线程来说操作的是同一个account，a线程执行，在第二步被b线程打断了，b执行完了这个方法，完成了自减（amount不同）
            // 这时候 a线程得到了执行权，指令计数器出栈，回到了第二步，把之前寄存器的值赋值回了account[from]
            // 程序a,b结束，正常结果应该是a,b都操作了，对account[from]都进行了更改，而实际上由于a执行过程中被b打断，而a,b操作同一个共享变量
            // a将结果暂时存储，等b执行完成后，将打断之前暂存的结果赋值了回来，这样就擦除了b的执行结果，返回的结果自然不对，只体现了a的执行

        }finally {
            // finally realise lock
            blockLock.unlock();
        }

    }

}
