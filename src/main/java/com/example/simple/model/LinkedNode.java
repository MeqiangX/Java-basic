package com.example.simple.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/26 9:17 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkedNode<T> {
    private T data;
    private LinkedNode next;


    /**
     * 静态遍历方法
     */
    public static <T> void transfer(LinkedNode<T> head){
        LinkedNode<T> tail = head.getNext();
        while(null != tail){
            System.out.printf("%2d | ",tail.getData());
            tail = tail.getNext();
        }
    }
}
