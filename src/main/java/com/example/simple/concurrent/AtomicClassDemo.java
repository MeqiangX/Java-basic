package com.example.simple.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/9 11:50 下午
 * 并发包下的原子操作类，并发条件下尽可能使用并发包或者线程同步集合
 * 并发条件下比起自己使用锁来编写同步代码，更不容易出错
 * atomic是利用CAS(Compare And Swap) 来实现原子性操作的，CAS实际上是利用处理器的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子操作
 *
 *
 */
public class AtomicClassDemo {

    // 基本数据类型的原子操作类

    /**
     * 以atomicInteger为例子
     * 主要是通过CAS+volatile+native本地方法来保证原子操作，从而避免了synchronized的高开销，提升程序执行效率
     * atomic原子类 源码中可以看到，有一个final的unsafe对象，这是cas的操作基础对象
     * Unsafe 是 JAVA实现CAS算法的类，整个类有关线程安全的操作，都是借助它来实现。
     * unsafe的objectFieldOffset()是一个本地方法，可以拿到"原来的值"的内存地址，即源码中的（private volatile int value;）当前值的对象
     * 被volatile修饰，内存可见，在任何时候都是最新的
     * 还有
     */
    public void atomicBasicClass(){
        AtomicInteger atomicInteger  = new AtomicInteger(1);
        // get得到当前值
        atomicInteger.get();
        // 自增1并返回
        atomicInteger.getAndIncrement();
        // 自减1并返回
        atomicInteger.getAndDecrement();
        // 设置值
        atomicInteger.set(2);
        atomicInteger.getAndAdd(10);
        atomicInteger.getAndAccumulate(10, (x,y) -> x*y);
    }

}
