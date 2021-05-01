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


    /**
     * 快速排序，和归并类似，但是和归并自下而上不同，快排是自上而下，每次选举一个中间点，划分成两块区域，再向下拆分，
     * 直到最后为1 即完成排序
     * 空间复杂度来说，归并排序需要在每次递归方法（内部子序列合并的时候分配 临时数组空间来合并和替换 为O(n)）
     * 不是个原地排序算法
     * 而快排 在分区函数中，只需要用常数级的临时变量来进行替换，空间复杂度为O(1)，为原地排序算法，
     * 解决归并需要占用太多内存的问题
     */

    /**
     * 如何求数组中第k大的元素
     * 使用快排 分区函数，选取最后一个为pivot
     * 第一次需要遍历n个元素，第二次要遍历n/2个元素,n/4.n/8。。。 等等比数列求和
     * (a1-an*q)/(1-q) = a1/(1-q) = 2n-1 -> O(n)
     * @param array
     */

    public static void quickSort(int[] array){
        divide(array,0,array.length-1);
    }

    // start p p+1 r
    public static void divide(int[] array,int p,int r){
        // 停止划分条件
        if (p >= r){
            return ;
        }

        //分区
        int pivot = partition(array,p,r);

        //分区后的两边区域  被pivot分隔，继续划分
        divide(array,p,pivot-1);
        divide(array,pivot+1,r);
    }

    /**
     * 分区函数
     * 时间复杂度为O(nlogn)
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array,int start,int end){
        int pivot = array[end];
        int i = start;
        for (int j = i;j < end;++j){
            //定义两个游标i,j指向start,当a[j] < pivot时候，交换j i 并且i++,j++;
            // 否则j++,i不变，这样i就始终指向第一个>= pivot的下标，（
            // 如果j < pivot） j i 的值会进行交换 交换后i指向的值是<pivot，i+1是大于pivot的下标
            //极端情况来说，整个数组都是小于pivot，i和j都是走到最后，即end就是分区点
            if (array[j] < pivot){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        // 此时array[i] 是 第一个>= pivot的位置(也就是pivot分区后的位置)，即分区点
        int k = array[i];
        array[i] = pivot;
        array[end] = k;
        return i;
    }

    /**
     * 快排方法找 第k大的元素
     * @param array
     * @return
     */
    public static int findK(int[] array,int k){
        if (k < 0 || array.length == 0){
            return -1;
        }
        if (k > array.length){
            k = array.length;
        }

        // 找k大 pivot为最后一个元素
        int index = find(array, 0, array.length - 1, k);
        return array[index];
    }
    public static int find(int[] array,int p,int r,int k){
        int m = partition(array,p,r);
        if (m < k-1){
            return find(array,m+1,r,k);
        } else if (m > k-1){
            return find(array,p,m-1,k);
        }else{
            // 得到了 返回
            return m;
        }
    }
}
