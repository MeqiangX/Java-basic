package com.example.simple.datastruct;

import com.example.simple.model.LinkedNode;

import java.util.Scanner;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/26 9:19 下午
 */
public class LruImplement {

    // LRU最近最久未使用（least recently used） 替换算法的链表实现
    /**
     * 原理：磁盘每次加载固定容量的数据到逻辑页的缓存中，方便cpu的缓存访问，
     * 当页面满了时，会执行替换策略来替换其中的某些页，如果策略不合适，系统会频繁的却页，需要从内存中重新加载
     * 从内存中加载速度会慢很多，我们称为抖动
     * 常见的替换策略有FIFO先进先出，LFU最少使用和LRU最近最久未使用
     */


    /**
     * lru实现原理，每次访问元素，如果不存在，则放入头部，如果存在，则将原先的节点置于头部，原先节点的前节点的next指向原先节点的next
     * 如果链表满了，从尾部删除指定节点即可
     */

    public static void main(String[] args) {
        //定义一个有长度限制的链表
        //设置哨兵
        LinkedNode<Integer> head = new LinkedNode<>();
        head.setData(null);
        head.setNext(null);


        //限制长度为10
        int lengthMax = 10;
        int current = 0;

        while(true){
            //使用键盘输入代替加载内存到逻辑页
            Scanner s = new Scanner(System.in);
            System.out.print("输入要访问的数，输入-1结束:");
            int input = s.nextInt();

            if (-1 == input){
                break;
            }

            //遍历链表，模拟lru
            LinkedNode<Integer> pre = head;
            LinkedNode<Integer> tail = head.getNext();


            //只有首节点，加载页面
            if (null == tail){
                LinkedNode<Integer> node = new LinkedNode<>();
                node.setData(input);
                node.setNext(null);
                tail = node;
                head.setNext(tail);
                ++current;
                // 首节点加载
                LinkedNode.transfer(head);
                continue;
            }
            while(null != tail){
                /**
                 * 中间节点查找 替换 两种情况
                 * 1、未找到，直接插到head后面
                 * 2、找到了，将原先节点移动到head后面
                 */

                // 1、找到了
                if (input == tail.getData().intValue()){
                    LinkedNode.transfer(head);
                    pre.setNext(tail.getNext());
                    tail.setNext(head.getNext());
                    head.setNext(tail);
                    System.out.println("元素 " + input + "在内存页中，置于头部");
                    LinkedNode.transfer(head);
                    break;
                }else{
                    pre = tail;
                    tail = tail.getNext();
                }
            }
            if (null == tail){
                //2、没找到
                System.out.printf("当前要从缓存逻辑页中寻找的数为:%d，未找到，当前页面容量为：%d",input,current);
                if (current == lengthMax){
                    System.out.println("遍历：");
                    LinkedNode.transfer(head);

                    // 删除最后三个

                    //删除最后三个，如果是指定大小容量的，可以计数删除，如果不知道容量，则可以通过快慢指针来删除，即
                    // 慢指针和快指针一次都走1个节点，快指针起始位置在慢指针+3个节点，举个例子，如果一个链表长度为5，那么第一次走，慢指针在第一个节点
                    // 快指针在第4个节点，第二次走的时候，因为长度为5，慢指针走到第二个节点，快指针走到第5个节点，此时，快指针走到末尾
                    // 慢指针所在节点即为 倒数第3个节点  故 令 慢指针步伐为x 快指针步伐为y  求倒数第n个节点，计算公式为 x+n=y，当y到末尾时候，
                    // x刚好到y-n 即 末尾节点-n个位置


                    // 此时快慢指针算法的时间复杂度为O(n)，空间复杂度为O(1) 借助两个指针;
                    // 而如果是一般算法，需要一个循环找到末节点，一个循环找到末节点前4个节点 时间复杂度为O(n^2)

                    //这里要删除最后三个，只需要求的倒数第4个节点node 使得node->next = null 即可实现
                    LinkedNode<Integer> slow = head.getNext();
                    LinkedNode<Integer> fast = head.getNext().getNext().getNext().getNext().getNext();

                    while (null != fast){

                        slow = slow.getNext();
                        fast = fast.getNext();

                    }

                    //删除最后三个
                    System.out.println("当前页面已经满了，根据lru策略，替换的三页为：" + slow.getNext().getData() + "-----"
                            + slow.getNext().getNext().getData() + "--------"+ slow.getNext().getNext().getNext().getData());
                    // 结束后 慢指针刚好离fast 4个节点距离
                    slow.setNext(null);
                    current -= 3;


                    System.out.println("替换后的结果：");
                }
                    // 删除后插入头部  或者是正常插入
                    //正常插入头部
                    LinkedNode<Integer> node = new LinkedNode<>();
                    node.setData(input);
                    node.setNext(head.getNext());
                    head.setNext(node);
                    ++current;
                    System.out.println("未找到元素：" + input +  " --- 从内存加载到缓存，抖动1次");
                    LinkedNode.transfer(head);


            }

        }

    }

}
