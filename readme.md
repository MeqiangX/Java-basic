# Java-basic
___
基础语法和sdk知识-记录java核心技术卷笔记

# 多线程

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

3. 条件锁

> 并发竞争的产生往往是伴随着多个条件产生，a在等待b，b在等待c锁，c在等待a锁，处理不当往往容易产生死锁
>
> Condition 条件，一个锁可以有多个条件对象
>
> ```java
> public class ConditionDemo{
>   private Lock lock = new ReentrantLock();
>   private Condition c1;
>   private Condition c2;
>   
>   ConditionDemo(){
>     c1 = lock.newCondition();
>     c2 = lock.newCondition();
>   }
>   
>   public void transfer(){
>     try{
>       c1.await();
>       //do sth
>       c2.await();;
>       // do sth
>       c2.notifyAll();
>       //release condition lock
>       c1.notifyAll();
>     }catch (InterruptedException e){
>       //
>     }finally{
>       c2.notifyAll();
>       c2.notifyAll();
>     }
>   }
> }
> ```

4. Volatile域关键字

> 缓存一致性：
>
> 内存的访问速度要比高速缓存cache(cpu中的缓存寄存器)要慢的多，所以当程序运行时，会将运算需要的数据从内存复制一份到cpu的高速缓存中。cpu如果要进行计算，就直接从缓存中取数，运算结束后再将结果刷新到主存中，；而每个cpu的cache是不一致的，这就带来了缓存的不一致性；
>
> 有两种解决方案：
>
> 1、加锁Lock
>
> 2、定义缓存一致性协议
>
> 加锁会阻碍其他cpu对其他部件（如内存）的访问，从而使得只有一个cpu能使用这个变量的内存，效率低下
>
> 缓存一致性协议保证每个缓存使用的共享变量的副本都是一致的，核心思想是当cpu写数据的时候，如果发现操作的变量是共享变量，（在其他的cpu缓存中也存在此变量的副本），会发出信号给其他的cpu通知将缓存置为无效状态，当其他的线程需要读取这个变量时，发现当前cpu的缓存是无效的，那么他就回取内存的最新值来覆盖缓存，从而保证每个线程读取到的cpu缓存中的共享变量为最新的
>
> <img src="https://github.com/MeqiangX/resource/blob/main/image/212219343783699.jpg?raw=true" width=100% height=300 title='数据流' alt='loadingfail'></img>

Volatile为实例域的同步访问提供了一种免锁机制，如果变量被声明为volatile，那么编译器和jvm就知道这个域是可能被另一个线程并发更新的。

锁也可以实现这个机制，但是繁琐且耗时，并且方法阻塞，而volatile则快很多，也不用为了单独的一个变量而设立独立的Lock

> Volatile的原理和实现机制：
>
> 有volatile关键字时，生成汇编代码后，会多出一个lock的前缀指令，相当于一个内存屏障，会提供三个功能：
>
> 1、确保指令重排的时候不会将后面的指令排到内存屏障之前的位置，也不会把内存屏障之前的指令排到内存屏障的后面，也就是在执行这一行的时候，前面已经都执行完成了
>
> 2、强制对缓存的操作立即写入主存
>
> 3、如果是写操作，会使其他cpu中的缓存行无效

5. 并发编程3个概念

   1. 原子性

   一个操作或者多个操作要么全部执行，并且执行过程不会被任何因素打断，要么就都不执行

   2. 可见性

   值的改变并不会从缓存中直接刷新到内存，而是会在所有运算结束后才被写入主存，而多线程的其他缓存中的共享变量是不会因为一个变量值的改变而同步的，除非当前缓存的值被置为无效状态，即被volatile修饰，才会当值改变时从内存中重新读取

   3. 有序性

   对于程序代码逻辑没有依赖性的几行来说，jvm并不会按照代码行数顺序执行，而是会进行指令冲编排，这是处理器为了提高程序运行效率，而对输入代码的执行顺序进行的优化，前提是不会对程序的最终结果产生变化，保持一致

   要保证多线程的环境下程序正常运行，就要保证这三个条件，否则最终的结果可能不是最终想要达成的结果

6. java内存模型

> jvm定义了一种java内存模型（java Memory Model）JMM，来屏蔽各个平台硬件平台和操作系统的内存访问差异，以此在实现java程序在各种平台下都能达到一致的内存访问效果

> 为了获得较好的执行性能，java内存模型没有限制用寄存器或高速缓存来提升指定执行速度，也没有限制编译器对指令进行重排序，在底层并发的三个条件限制对jmm来说也是一样的，那么他是如何来保证这三个条件的呢：
>
> 1、原子性，只保证基本读取和赋值（只针对确定的具体值赋值给变量，而不是变量赋值给变量，a=b，这里面包含了 1.取b的值，2.赋值给a两部操作）
>
> 2、可见性，通过volatile关键字来保证可见性
>
> 3、有序性，可以通过volatile来保证一定的有序性（禁止重编排），也可以使用synchronized和lock来保证有序性

7. 原子操作类

在大大多数并发的情况下，java sdk编写者都会考虑到，有给开发者们提供原子操作类和线程安全集合来安全的操作基本数据类型和集合，java.util.concurrent.atomic包中包含了常见数据类型的原子操作包装类，如AtomicInteger,AtomicLong，AtomicBoolean，数组类的有AtomicIntegerArray，AtomicLongArray，引用类的有AtomicReference，AtomicReferenceArray等，源码中都是通过CAS+volatile来实现的，下面通过AtomicInteger的源码来分析原理：

```java
public class AtomicInteger extends Number implements java.io.Serializable {
   private static final long serialVersionUID = 6214790243416807050L;

   // setup to use Unsafe.compareAndSwapInt for updates
   private static final Unsafe unsafe = Unsafe.getUnsafe();
   private static final long valueOffset;

   static {
      try {
         valueOffset = unsafe.objectFieldOffset
                 (AtomicInteger.class.getDeclaredField("value"));
      } catch (Exception ex) { throw new Error(ex); }
   }

   private volatile int value;

   /**
    * Creates a new AtomicInteger with the given initial value.
    *
    * @param initialValue the initial value
    */
   public AtomicInteger(int initialValue) {
      value = initialValue;
   }
```

几个关键信息属性：

1、private volatile int value;

​	value是当前值，使用volatile修饰，来确保每次访问更新的时候拿到的都是value最新的值

2、static final Unsafe unsafe = Unsafe.getUnsafe();

​	Unsafe类，和C/C++不同，java没有直接对内核和操作系统，都是通过jvm，当然也不能直接操作一块内存区域，Unsafe就是jvm提供给我们操作管理内存的这么一个类，他的全限定名是sun.misc.Unsafe，一般开发应用者不会用到这个类，管理操作内存是比较危险的一件事情，所以在java中他被修饰为final，并且构造器私有化，只能通过反射来得到，当然类中也提供了静态方法通过类加载器来得到，原理相同，这个类中主要包含内存管理的一些api：

![alt 加载失败](https://github.com/MeqiangX/resource/blob/main/image/11963487-607a966eba2eed13.png?raw=true "Unsafe常用功能")

原子操作类和线程安全集合底层都是操作unsafe，依靠unsafe的cas和volatile来完成

3、private static final long valueOffset

这是个比较有意思的属性，可以看到，在AtomicInteger的static代码块中随着AtomicInteger.class加载进jvm，也被初始化，

```java
static {
        try {
        valueOffset = unsafe.objectFieldOffset
        (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
        }
```

即：在类加载后就固定了，思考下这个属性的作用，通过unsafe.objectFieldOffse()方法得到value在内存中相对AtomicInteger实例的偏移量，通过valueOffset来得到value在AtomicInteger对象中的位置是这个属性的核心作用，图解：

![alt loading](https://github.com/MeqiangX/resource/blob/main/image/6824285-2b78f25356cfd9fb.png?raw=true "valueOffset图解")

- valueOffset可以定位到AtomicInteger中value的位置
- AtomicInteger中valueOffset是固定的（static final）,不因不同实例而改变，随着类文件被加载的jvm，相对于value的偏移量就确定了

4、方法实例解析：

`getAndAdd`方法

```java

/**
 * Atomically adds the given value to the current value.
 *
 * @param delta the value to add
 * @return the previous value
 */
public final int getAndAdd(int delta) {
        return unsafe.getAndAddInt(this, valueOffset, delta);
        }
```

Unsafe.getAndAddInt：

源码中的变量名不太好理解，替换成好理解的变量名来看

```java
public final int getAndAddInt(Object atomicInstance, long valueOffset, int delta) {
        int result;
        do {
        result = this.getIntVolatile(atomicInstance, valueOffset);
        } while(!this.compareAndSwapInt(atomicInstance, valueOffset, result, result + delta));

        return result;
        }
```

unsafe.compareAndSwapInt：

```java
/**
 * unsafe中基本都是native本地方法来对内存直接操作
 * var1 需要更新的原子对象
 * valueOffset 原子对象中value相对于对象首地址的偏移量
 * except 希望field中的value值
 * 如果期望值except和filed-value的当前值相同，则设置field-value为这个值
 **/
public final native boolean compareAndSwapInt(Object var1, long valueOffset, int except, int update);
```

这就是CAS的核心，他会以原子性的操作来完成三个操作:

- 获取原子对象的value相对对象首地址的偏移量offset
- 通过偏移量来获取value，比较value和期望值是否相同
- 相同则更新value为update，否则不更新，循环直到工作内存中的值和主存中的同步

现在思考模拟一下多线程（A,B两个线程）的情况下对AtomicInteger执行从1加到100的操作：

- 线程A首先执行`getIntVolatile()` 方法，第一次执行，所以`getIntVolatile()`得到的一定是内存中最性的值，即为1
- 线程下一步要执行CAS比较，假如这时线程A被阻塞了（`getAndAddInt()`并不是原子性），线程B开始执行
- 线程B执行`getIntVolatile()`，工作内存和主存中的值都为1，获取工作内存的值为1
- 线程B执行CAS，expect和value的值相同，执行update，更新值为1+1=2，volatile将工作内存的2刷新到主存2，线程B执行结束
- 此时线程A恢复，继续执行，执行到CAS时，此时expext和value（获取到的为B线程更新后的最新值）的值不相同，不执行更新，进行下一轮循环
- 线程A进入下一次循环，执行`getIntVolatile()`，此时获取到的期望值更新为最新的2
- 线程A执行CAS，期望值和value值相同=2，执行更新操作2+1=3，工作内存为3刷新到主存，线程A结束。

---

8. 线程类和线程池

   1. Runnable、Future、Callable

      Runnable无返回结果，执行方法是`run()`，Callable有返回结果，执行方法是`call()`，Future保存线程执行状态和计算结果

   2. FutureTask包装器

      实现RunnableFuture(同时继承Runnable和Future的接口)，可以将Runnable和Callable都转为Futur

   3. 执行器Executors和线程池

      1. 构建一个新的线程是有一定代价的，涉及到操作系统的交互和分配资源调度，正确的做法应该是创建一个线程池，而不是频繁的去创建和销毁线程（如果涉及到需要创建许多短周期的线程），将线程对象交给线程池，就会调用run/call方法，执行完成后，线程会回到线程池准备下一次的执行，而不是销毁，执行器（Executors）包含了很多静态工厂方法来构建不同需求的线程池：

         |               方法               |                         描述                          |
                  | :------------------------------: | :---------------------------------------------------: |
         |       newCachedThreadPool        | 创建缓存线程池，空闲线程会被保留60s，必要时创建新线程 |
         |        newFixedThreadPool        |       创建固定线程数的线程池，空闲线程会被保留        |
         |     newSingleThreadExecutor      |   只有一个线程的线程池，会按照提交任务的顺序来执行    |
         |      newScheduledThreadPool      |           预定时间间隔/周期执行任务的线程池           |
         | newSingleThreadScheduledExecutor |          预定时间/周期执行的单个线程的线程池          |

---

9. 同步器

   java.util.concurrent包包含了几个能帮助人们管理相互合作的线程集，这些机制具有为线程之间的共用集结点模式

   | 类               | 作用                                                         | 说明                                                         |
      | ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | CyclicBarrier    | 让线程集等待直至其中预定数目的线程都到达一个公共障栅（barrier），然后可以选择性的执行一个处理障栅的动作 | 当障栅后续的操作需要得到前面线程集的操作结果和的时候         |
   | CountDownLatch   | 允许线程集等待直到计数器减为0                                | 当一个线程或多个线程需要等待直到指定数目的事件发生           |
   | Semaphore        | 流量控制，限制最大的并发访问线程数                           | 为线程操作设置信号量，数量为最大的并发访问线程数，数量之内的线程可以获取信号-1，当信号量为0时，达到最大并发，不允许另外的线程进入直到拿到信号量的线程执行完释放信号量，这只是限制并发数量，限流，并不能保证线程安全，要达到线程安全还需配合锁来完成 |
   | SynchronousQueue | 允许一个线程把对象交给另一个线程                             | 生产者消费者，在没有显示同步的情况下，把一个对象从一个线程传递给另外一个对象（数据流的方向是确定单向的，不同与Exchanger是双向的） |
   | Exchanger        | 允许两个线程在要交换的对象准备好时交换对象                   | 当一个线程到达exchange调用点时，如果其他线程此前调用了此方法，则其他线程会被调度唤醒并与之进行对象交换，然后各自返回，如果其他线程还没达到交换点，则当前线程会被挂起，直到其他线程到达调用才会完成交换并正常返回，或者有超时中断返回 |
   | Phaser           | 类似于cyclicbarrier，更加灵活，计数器是可以手动改变的        | （`register()`为part(phaser中计数器的叫法)+1，`arriveAndDeregister()` part-1）另外可以自定义各个周期到达后的触发事件 |


