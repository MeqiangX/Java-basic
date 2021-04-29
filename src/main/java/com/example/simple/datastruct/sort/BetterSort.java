package com.example.simple.datastruct.sort;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/29 10:30 下午
 */
public class BetterSort {

    /**
     * 归并排序入口
     * @param array
     */
    public static void mergeSort(int[] array){
        merge_main(array,0,array.length-1);
    }

    public static void merge_main(int[] array,int start,int end){
        // 递归退出条件
        if (start >= end){
            return ;
        }

        int p = (start + end) / 2;

        /**
         * 下层递归
         */
        merge_main(array,start,p);
        merge_main(array,p+1,end);

        //子序列排序合并
        merge(array,start,p,p+1,end);
    }

    /**
     * 子序列排序 合并
     * @param array
     * @param leftPoint
     * @param leftEnd
     * @param rightPoint
     * @param rightEnd
     */
    public static void merge(int[] array,int leftPoint,int leftEnd,
                             int rightPoint,int rightEnd){
        int arrayStart = leftPoint;
        int[] temp = new int[leftEnd-leftPoint+1 + rightEnd-rightPoint+1];
        int tempIndex = 0;
        while (leftPoint <= leftEnd && rightPoint <= rightEnd){
            if (array[leftPoint] <= array[rightPoint]){
                temp[tempIndex++] = array[leftPoint++];
            }else{
                temp[tempIndex++] = array[rightPoint++];
            }
        }

        while (leftPoint <= leftEnd){
            temp[tempIndex++] = array[leftPoint++];
        }
        while (rightPoint <= rightEnd){
            temp[tempIndex++] = array[rightPoint++];
        }

        //合并完成，替换原数组子串
        for (int i = 0;i < tempIndex;++i){
            array[arrayStart++] = temp[i];
        }
    }



}
