package com.example.simple.datastruct.sort;

/**
 * @Author: DruidQiang
 * 三种基础排序方法 O(n^2) 冒泡 插入 选择
 * @Date: 2021/4/28 10:15 下午
 */
public class BaseSort {

    // 引入一个计算需要移动的计算方法
    // 有序度 逆序度 满有序度
    // 有序度就是数组中具有有序关系的元素对的个数 不往回溯
    /**
     * 比如：1 6 3 4 5
     * 此时 有序度  7 = （1，6）（1，3）（1，4）（1，5）（3，4）（3，5）（4，5）
     * 满有序 在全部有序的时候的有序度 为 n*(n-1)/2 等比数列的累加公式 5个都是有序的  n=5 -> 10
     * 逆序度 = 满序度 - 有序度 = 3
     * 逆序度  即为需要移动的次数
     * 排序的过程 就是增加有序度，降低逆序度的过程
     */

    /**
     * 冒泡排序在最坏情况下，有序度=0，需要进行n(n-1)/2次交换
     * 最好的情况下，有序度=n(n-1)/2，需要进行0次交换
     * 平均交换次数= n(n-1)/4 即O(n^2)
     * 而比较次数一定会大于交换次数，而冒泡的复杂度上限是O(n^2)
     * 所以平均情况下的时间复杂度就是O(n^2)
     */

    /**
     * 这里提供的一种不太严格的 平均时间复杂度的推导，但很多时候实用
     * 概率论的定量分析太过复杂
     */

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array){
        boolean isFinish = true;
        for (int i = 0; i < array.length-1;++i){
            isFinish = true;
            for (int j = i; j < array.length;++j){
                if (array[i] > array[j]){
                    // 优化版的冒泡排序，如果有一次没有发生任何交换，数组即是有序的，无需进行后续循环比较
                    int num = array[i];
                    array[i] = array[j];
                    array[j] = num;
                    isFinish = false;
                }
            }
            if (isFinish){
                break;
            }
        }
    }

    /**
     * 插入排序
     * 前面的子数组是有序的，比较新元素应该插入到前面子数组的位置
     * 和冒泡排序类似，在最坏情况下， 移动次数为n(n-1)/2，最好0，平均时间复杂度O(n^2)
     * 但是观察内层循环，假如执行一行代码的单元时间为t;
     * 冒泡需要3t,而插入只需要一行，t，故虽然时间复杂度的粗略分析是相同，但是
     * 实际执行的效率 插入还是优于冒泡，并且两个都是稳定排序
     * @param array
     */
    public static void insertSort(int[] array){
        if (array.length == 1){
            return;
        }
        for (int j = 1; j < array.length;++j){
            int val = array[j];
            int i = j-1;
            for (;i >= 0;--i){
                if (array[i] > val){
                    // 比较 后移
                    array[i+1] = array[i];
                }else{
                    // 前面子数组有序，当满足 前面的最后一个 <= array[j] 即为插入点
                    break;
                }
            }
            // i 为 array[i] <= array[j]的点， 故下一个就是array[j]要插入的位置
            array[i+1] = val;
        }
    }

    /**
     * 选择排序
     * 冒泡和插入 最优情况都是O（n） 最坏也都是O(n^2)
     * 选择最坏和最好都是O(n^2) 而且选择是不稳定排序
     *
     * @param array
     */
    public static void chooseSort(int[] array){
        for (int i = 0;i < array.length - 1;++i){
            for (int j = i+1;j < array.length;++j){
                if (array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

}
