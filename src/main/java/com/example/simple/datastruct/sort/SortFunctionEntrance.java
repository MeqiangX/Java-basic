package com.example.simple.datastruct.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/28 10:47 下午
 */
public class SortFunctionEntrance {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入要生成的随机数组的长度：");
        int length = in.nextInt();
        System.out.println("请输入要生成的随机数组的数字范围：");
        int range = in.nextInt();
        System.out.println("请输入排序方式：：1：冒泡 2：插入 3：选择 4：归并");
        int sort = in.nextInt();


        int[] array = new int[length];
        for (int i = 0;i<length;++i){
            array[i] = (int)(range * Math.random());
        }

        SortTypeEnum sortType = SortTypeEnum.getByKey(sort);

        if (null == sortType){
            return ;
        }

        long start = System.currentTimeMillis();
        if (sortType.getKey().equals(SortTypeEnum.BUBBLE.getKey())){
            BaseSort.bubbleSort(array);
        }
        if (sortType.getKey().equals(SortTypeEnum.INSERT.getKey())){
            BaseSort.insertSort(array);
        }
        if (sortType.getKey().equals(SortTypeEnum.CHOOSE.getKey())){
            BaseSort.chooseSort(array);
        }
        if (sortType.getKey().equals(SortTypeEnum.MERGE.getKey())){
            BetterSort.mergeSort(array);
        }
        System.out.println("耗时ms："+(System.currentTimeMillis() - start));
        //Utils.print(array);
    }

}
