package com.example.simple;

import com.example.simple.model.Account;
import com.example.simple.thread.CyclicBarrierDemo;
import com.example.simple.thread.ThreadDemo;
import com.example.simple.thread.ThreadPoolDemo;
import com.example.simple.util.BitSetUtil;
import com.example.simple.util.CollectionFunctions;
import com.example.simple.util.PropertiesUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.descriptor.ClasspathResourceSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootTest
class SimpleApplicationTests {

    @Autowired
    private CollectionFunctions collectionFunctions;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private BitSetUtil bitSetUtil;


    @Test
    void contextLoads() {
        List<String> list = Lists.list("amy", "lili", "luola", "kiki");
        Collections.sort(list);
        System.out.println(list.toString());
        int key = collectionFunctions.binarySearch(list, "xq");
        System.out.println(key);
    }

    @Test
    void propertiesTest(){
        try {
            propertiesUtil.load(new FileInputStream(new File("/Users/xiaoqiang/IdeaProjects/simple/src/main/resources/application.properties")));
            String mykey = propertiesUtil.getProperty("mykey");
            System.out.println(mykey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void bitsetTest(){
        BitSet bitSet = bitSetUtil.init(10);
        boolean b = bitSet.get(1);
        System.out.println(b);
        bitSet.set(1);
        boolean b1 = bitSet.get(1);
        System.out.println(b1);
        // Sets all of the bits in this BitSet to false.
        bitSet.clear();
    }

    @Test
    void synchronizedTest(){
        Account account = new Account(30,100);
        for (int i = 0;i <30;++i){
            Runnable r = () -> {
                try{
                    while(true){
                        account.transfer(new Random().nextInt(30),new Random().nextInt(30),100 * new Random().nextDouble());
                        //Thread.sleep((int) (10 * Math.random()));
                    }
                }catch (Exception e){

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }


    @Test
    public void runnableTest(){
        ThreadDemo threadDemo = new ThreadDemo();
        for (int time = 0;time < 10;++time){
            System.out.println( "I ======"+time);
            threadDemo.runnableTest();
        }
    }

    @Test
    public void callableTest(){
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.futureTest();
    }

    @Test
    public void threadPoolTest(){
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        threadPoolDemo.executorsThreadFunction();
    }

    @Test
    public void cyclicBarrierTest(){
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.cyclicBarrierFunction();
    }
}
