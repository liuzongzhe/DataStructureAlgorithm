package com.lzz.algorithm.search.statictable;

/**
 * 查找算法类
 * 以整形数组为例
 * Author lzz
 * Date   2018/5/27
 */
public class MySearch {
    /**
     * 顺序查找
     */
    public static int seqSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分法查找(折半查找)
     * 条件：
     * 顺序存储的有序表(从小到大)
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;	//中间位置，当前比较的数据元素位置
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 分块查找(线性索引查找)
     * 条件：
     * 1. 块间有序，让每一块的最大值记录到一个索引表中，
     *              并且每块的最大值要小于后块的最小值。
     *				可以用顺序查找或者二分查找。
     * 2. 块内无序，每个块内数据集无序，只能用顺序查找
     */
    //...


    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] arr = {7,5,2,3,4,9,1,0,2};
        System.out.println(MySearch.seqSearch(arr, 7));

        int[] brr = {1,2,3,4,5,6,7,8,9};
        System.out.println(MySearch.binarySearch(brr, 7));
    }
}
