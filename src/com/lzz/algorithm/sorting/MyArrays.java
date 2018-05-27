package com.lzz.algorithm.sorting;

import java.util.Arrays;

/**
 * 1.顺序存储结构
 * 2.整型数组为例
 * 3.从小到大排序
 * Author lzz
 * Date   2018/5/27
 */
public class MyArrays {
    //两个整型数组交换
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 直接插入排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定的排序算法
     */
    public static int[] insertSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int i, j ,temp;
        for (i = 1; i < arr.length; i++) {
            temp = arr[i];				//暂存待插入数据
            for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];	//将前面比arr[i]大的数据向后移动
            }
            arr[j + 1] = temp;			//插入到j+1的位置
        }
        return arr;
    }

    /**
     * 希尔排序
     * 时间复杂度：平均：O(nlogn), 最坏为O(n2)
     * 空间复杂度：O(1)
     * 不稳定的排序算法
     */
    public static int[] shellSort(int[] arr, int[] d) { //d[]为增量数组
        if (arr.length == 0) {
            return arr;
        }
        int i, j, temp;
        for (int k = 0; k < d.length; k++) {
            int dk = d[k];			//取增量，注意最后一次增量值必须是1
            for (i = dk; i < arr.length; i++) {
                temp = arr[i];
                for (j = i - dk; j >= 0 && arr[j] > temp; j -= dk) {
                    arr[j + dk] = arr[j];
                }
                arr[j + dk] = temp;
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定的排序算法
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int i, j;
        boolean flag = true;
        for (i = 1; i < arr.length && flag; i++) {
            flag = false;
            for (j = 0; j < arr.length-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j+1);
                    flag = true;
                }//一次交换都未发生时，为false，表明已排序好
            }
        }
        return arr;
    }

    /**
     * 快速排序
     * 时间复杂度：平均：O(nlogn), 最坏为O(n2)
     * 空间复杂度：O(logn), 最坏为O(n)
     * 不稳定的排序算法
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotLoc = partition(arr, low, high);
            quickSort(arr, low, pivotLoc-1);	//低子表递归排序
            quickSort(arr, pivotLoc+1, high);	//高字表递归排序
        }
        return arr;
    }
    //一趟快速排序
    private static int partition(int[] arr, int i, int j) {
        int pivot = arr[i];		//暂存基准数据
        while (i != j) {
            while (i < j && arr[j] >= pivot) {
                j--;			//从后遍历查找小于基准的数据
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] <= pivot) {
                i++;			//从前遍历查找大于基准的数据
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = pivot;
        return i;
    }

    /**
     * 直接选择排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定的排序算法
     */
    public static int[] selectSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i])
                    min = j;
            }
            swap(arr, i, min);
        }
        return arr;
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * 稳定的排序算法
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    //将两段排序好的数组结合成一个排序数组
    private static int[] merge(int[] left, int[] right) {
        int[] r = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < r.length; index++) {
            if (i >= left.length)
                r[index] = right[j++];
            else if (j >= right.length)
                r[index] = left[i++];
            else if (left[i] > right[j])
                r[index] = right[j++];
            else
                r[index] = left[i++];
        }
        return r;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        int[] arr = {7,2,4,5,3,6,2,8};
        int[] brr = MyArrays.insertSort(arr);
        System.out.println(Arrays.toString(brr));

        int[] d = {5, 3, 1};
        brr = MyArrays.shellSort(arr, d);
        System.out.println(Arrays.toString(brr));

        brr = MyArrays.bubbleSort(arr);
        System.out.println(Arrays.toString(brr));

        brr = MyArrays.quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(brr));

        brr = MyArrays.selectSort(arr);
        System.out.println(Arrays.toString(brr));

        brr = MyArrays.mergeSort(arr);
        System.out.println(Arrays.toString(brr));
    }
}
