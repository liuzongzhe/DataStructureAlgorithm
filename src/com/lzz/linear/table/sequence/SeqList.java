package com.lzz.linear.table.sequence;

/**
 *
 * 模拟顺序表SeqList类
 * 对象数组+控制指针
 * Author lzz
 * Date   2018/5/27
 */
public class SeqList {
    //定义对象数组表示顺序表的存储空间
    private Object[] listElem;
    //定义线性表的当前长度
    private int curLen;

    //初始化存储容量为maxSize的空表
    public SeqList(int maxSize) {
        curLen = 0;
        listElem = new Object[maxSize];
    }

    //清空方法
    public void clear() {
        curLen = 0;
    }

    //是否为空方法
    public boolean isEmpty() {
        return curLen == 0;
    }

    //表的元素个数方法
    public int length() {
        return curLen;
    }

    //取值方法
    public Object get(int i) {
        if (i < 0 || i > curLen - 1) {
            throw new RuntimeException("取值不合法");
        }
        return listElem[i];
    }

    /**
     * 根据元素对象查找位置
     */
    public int indexOf(Object obj) {
        int i = 0;
        while (i < curLen && !listElem[i].equals(obj)) {
            i++;
        }
        if (i >= curLen) {
            throw new RuntimeException("查找元素不存在");
        }
        return i; //返回元素的位置
    }

    /**
     * 顺序表的插入算法
     */
    public void insert(int i, Object obj) {
        if (curLen == listElem.length) {
            throw new RuntimeException("顺序表已满");
        }
        if (i < 0 || i > curLen) {
            throw new RuntimeException("插入位置不合法");
        }
        //循环将插入位置后面的元素 向后移一位
        for (int j = curLen; j > i; j--) {
            listElem[j] = listElem[j-1];
        }
        //将元素插入到"腾出来的空位"
        listElem[i] = obj;
        //将当前表的长度+1
        curLen++;
    }

    /**
     * 顺序表的删除算法
     */
    public void remove(int i) {
        if (i < 0 || i > curLen) {
            throw new RuntimeException("删除位置不合法");
        }
        //循环将删除位置后面的元素 向前移一位
        for (int j = i; j < curLen - 1; j++) {
            listElem[j] = listElem[j+1];
        }
        //将当前表的长度-1
        curLen--;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        SeqList list = new SeqList(10);
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(2, "c");
        list.insert(3, "d");
        list.insert(4, "e");

//		System.out.println(list.isEmpty());
//		System.out.println(list.length());
//		System.out.println(list.get(2));
//		System.out.println(list.indexOf("e"));
        list.remove(0);
//		System.out.println(list.indexOf("e"));
        System.out.println(list.length());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
    }
}
