package com.lzz.linear.table.link;

import com.lzz.linear.node.Node;

/**
 * 链表类LinkList
 * Author lzz
 * Date   2018/5/27
 */
public class LinkList {
    public Node head; //链表的头指针

    public LinkList() {
        head = new Node();
    }

    //将一个已存在头结点的链表置为空表
    public void clear() {
        head.data = null;
        head.next = null;
    }

    //判断带有结点的链表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    //求带头结点的链表的长度
    public int length() {
        Node p = head.next; //p指向首结点
        int length = 0;		//计数器
        while (p != null) {
            p = p.next;		//依次指向后继结点
            ++length;
        }
        return length;
    }

    //查找带头结点的链表的第i个结点
    public Object get(int i) {
        Node p =  head.next; //p指向首结点
        int j = 0;
        //从首结点开始查找
        while (p != null && j < i) {
            p = p.next;
            j++;
        }
        if (j > i || p == null) { //循环结束判断
            throw new RuntimeException("查找元素不存在");
        }
        return p.data;
    }

    //查找元素为obj的结点
    public int indexOf(Object obj) {
        Node p =  head.next; //p指向首结点
        int j = 0;
        //从首结点开始查找
        while (p != null && !p.data.equals(obj)) {
            p = p.next;
            j++;
        }
        if (p == null) {
            return -1;
        }
        return j;
    }

    //在第i个结点之前插入新节点元素
    public void insert(int i, Object obj) {
        Node p =  head;		//p指向头结点
        int j = -1;
        //寻找第i个结点的前结点
        while (p != null && j < i-1) {
            p = p.next;
            j++;
        }
        if (j > i-1 && p == null) {
            throw new RuntimeException("插入位置不合法");
        }
        Node s = new Node(obj);	//生成新结点
        s.next = p.next;		//当前结点p的后结点引用赋给新节点
        p.next = s;				//当前结点p的引用指向新节点
    }

    //头部添加元素
    public void startAdd(Object obj) {
        insert(0, obj);
    }
    //尾部添加元素
    public void endAdd(Object obj) {
        insert(length(), obj);
    }
    //默认尾部添加元素
    public void add(Object obj) {
        insert(length(), obj);
    }

    //删除第i个结点
    public void remove(int i) {
        Node p =  head;
        int j = -1;
        //寻找第i个结点的前结点
        while (p != null && j < i-1) {
            p = p.next;
            j++;
        }
        if (j > i-1 && p.next == null) {
            throw new RuntimeException("删除位置不合法");
        }
        p.next = p.next.next;	//当前结点p的引用指向下下个结点
    }

    /**
     * 去处重复结点的方法
     */
    public void removeRepeatElem() {
        Node p = head.next;		 //p指向首结点
        while (p != null) {		 //从首结点向后查找，直到p为空
            int index = indexOf(p.data);  //记录p的位置
            Node q = p.next;
            while (q != null) {
                if (p.data.equals(q.data)) {
                    remove(index+1);	   //去除q所在位置的重复元素
                } else {
                    index++;
                }
                q = q.next;
            }
            p = p.next;
        }
    }

    /**
     * 打印输出所有结点
     */
    public void display() {
        Node node = head.next;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");

        list.display();
        System.out.println(list.indexOf("ccc"));
        System.out.println(list.get(2));
        list.remove(2);
        list.display();
        list.startAdd("aaa");
        list.startAdd("bbb");
        list.display();
        list.removeRepeatElem();
        list.display();
    }
}
