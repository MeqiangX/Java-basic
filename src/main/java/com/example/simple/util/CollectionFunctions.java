package com.example.simple.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class CollectionFunctions {
    public void collectionsFunctions(){
        List<String> stringList = new ArrayList<>();
        boolean b = Collections.addAll(stringList, "Nancy", "kris", "mars");
        // sort 排序 可传入一个实现Comparator的接口类来自定义排序规则
        stringList.forEach(System.out::println);
        Collections.sort(stringList);

        stringList.forEach(System.out::println);

        //sort implements comparators 传入一个排序规则,stream是链式的，可以多个条件thenComparing以及reverse
        Collections.sort(stringList, Comparator.comparing(String::length));
        stringList.forEach(System.out::println);
        //reverse
        Collections.reverse(stringList);
        stringList.forEach(System.out::println);
        // shuffle 如果列表长度小于5或者列表实现了RandomAccess,会直接调用swap方法，根据随机数取下标进行交换（即使是没有传入一个随机数Random对象，会使用默认的随机对象）
        // 否则会将列表转化为数组 进行数组内的swap，对于数组列表来说（ArrayList）内部是数组，对于随机访问和交换将会很快，shuffle并不需要增删元素，
        // 并且ArrayList实现了RandomAccess接口，这个接口没有方法，只是一个标记作用，对于一些方法通过判断是否是一个RandomAccess的子类来走不同的算法
        // shuffle就是其中一种，linkedList是链表结构，增删很快，但是随机访问慢，并且他没有实现RandomAccess接口，但是长度很小时并无多大差距
        Collections.shuffle(stringList);
        stringList.forEach(System.out::println);
    }


    /**
     * Collections.binarySearch 二分查找，必须给一个顺序的列表，否则结果是错误的
     * 存在则返回下标，否则返回-i i为插入的位置 参考-i-1
     * 只有随机访问 二分查找才有意义，线性结构对于二分查找来说没有优势（链表），如果传入的list是一个链表，将会自动转换为线性查找
     *
     * 并且如果列表的元素类扩展了AbstractSequentialList类，则自动转为线性查找，否则使用二分查找
     * @param list
     * @return
     */
    public int binarySearch(List list,String key){
        return Collections.binarySearch(list,key);
    }
}
