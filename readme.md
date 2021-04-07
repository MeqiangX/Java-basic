# Java-basic
___
基础语法和sdk知识-记录java核心技术卷笔记

1. 线程

Cpu和线程数的关系：

> 计算机五大组成：控制器，运算器，存储器，输入设备，输出设备。
>
> 其中控制器主要是指指令计数器和指令寄存器，存放各种指令二进制，指挥整个计算机的有序运行，执行方法后如何回到原来的代码位置继续执行剩余方法（指令计数器的入栈出栈）；多线程之间的切换（指令计数器保存各就绪线程的锚指令）等等，都依赖于控制器
>
> 运算器即我们常说的cpu（包含内部的缓存寄存器属于存储部分），以前的cpu都是单核的，即一个cpu一个内核，同一时间只能处理一个线程，也就不存在所谓的并发和多线程执行了，这里说的cpu和内核都是物理上的区分，即你拆开主机板就可以看到cpu的个数，而后面随着发展，cpu还可以分为多个内核，常说的1cpu内部还可以细分为多个内核，这里的拆分也是物理的拆分，后面一个内核又可以分为多个线程（超线程技术），常说的单核4线程，双核4线程，四核8线程等等，这种属于逻辑拆分。

进程和线程的关系：

> 进程是操作系统进行资源（cpu，内存，磁盘，io，带宽）等分配的最小单位
>
> 而线程是cpu调度和分配的基本单位
>
> 举个例子：打开一个浏览器，一个聊天窗口分别是一个进程，而进程可以有多个子任务，聊天工具接受消息，发送消息，浏览器渲染图片，加载视频等是线程，cpu执行调度分配的最小单位都可以是一个线程来单独处理，而进程是一个完整的模块

java中多线程的创建方式：

```java
1、继承Thread类（java单继承，扩展性差，放弃）
  public MyThread extends Thread{}
2、实现Runnable(接口好扩展，可行)，重写run方法
  public MyThread implements Runnable{}
3、java8后使用函数式编程，通过匿名类的方式创建（推荐）
  Runnable r = () -> {//do sth};
创建好线程后并没有启动，需要通过Thread t = new Thread(r); t.start(); 来启动线程
```

多线程竞争问题：

1. synchronized关键字：

> 1. 对方法或者代码块进行显示加锁，底层是基于jvm的
> 2. 用法
>
> ```java
> //1.方法使用synchronized修饰
> public synchronized void syn(){}
> // 使用同步锁关键字修饰的方法为同步方法，并发操作同时只有一个线程进入，synchronized是隐式释放锁的，在块区域结束后自动释放锁。
> 
> //2.代码块中使用synchronized
> synchronized {}
> // 和修饰方法是一样的，并发操作的多个线程只有一个获取到对象的内部锁的线程才执行这段代码，执行完后释放锁，唤醒其他线程执行
> ```
>
> 3. 锁可以有一个或多个相关的条件对象
>
> ```java
> public void transfer(double[] accounts,int from,int to,double amount){
>   synchronized (accounts){
>     
>   }
> }
> ```
>
> 4. 如果需要在锁代码块内部条件控制，可以调用object的wait()，notifyAll()
>
> ```java
> public synchronized void conditionSynTest(){
>   while (conditon == false){
>     wait();//当前线程由运行态 转变为等待态
>   }
>   // do sth
>   
>   // 唤醒
>   notifyAll();
> }
> ```

2. ReentrantLock重入锁

> 可重入的定义为对象内部锁有一个计数，在锁的块区域中每次方法压栈内部锁计数都会+1，进入锁区域也会+1，弹出栈计数-1，直到结束锁块，计数=0，释放锁；事实上，synchronized和ReentrantLock都是可重入锁，只是一个基于jvm（synchronized），一个是基于他的封装，基于jdk，两者功能类似，一个隐式，一个显式释放锁
>
> ___
>
> 1. 使用
>
> ```java
> public class Demo{
>   private Lock blockLock = new ReentrantLock();
>   public void lockTest(){
>      try{
>        blockLock.lock();
>        // do sth
>      }finally{
>        blockLock.unLock();
>        //记得在finally中释放锁
>      }
>   }
> }
> ```