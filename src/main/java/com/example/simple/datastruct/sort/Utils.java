package com.example.simple.datastruct.sort;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/28 10:52 下午
 */
public class Utils {

    public static void print(int[] array){
        for (int i = 0;i < array.length;++i){
            System.out.print("  |--"+array[i]+"--|  ");
        }
    }


    /**
     *  数组中数字求和得到目标数
     * 给定一个int数组A，数组中元素互不重复，给定一个数x，求所有求和能得到x的数字组合，组合中的元素来自A，可重复使用。
     *
     * - 例子：
     * A = [2,3,6,7]
     * x = 7
     * 其中一种输出结果：
     * [
     *     [7],
     *     [2,2,3]
     * ]
     */

    // 穷举
    public static void destNum(int[] arr,int num){
        //[2,3,6,7] 7，[7],【2，2，3】

    }

    public static void deal(int[] arr,int start,int end,int currentTotal){

        for (int i = start;i <= end;i++){
            deal(arr,start,i,currentTotal+arr[i]);
        }

    }

}
