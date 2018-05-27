package com.lzz.linear.queue.link;

import com.lzz.linear.node.Node;

/**
 * 链表队列LinkQueue
 * Author lzz
 * Date   2018/5/27
 */
public class LinkQueue {
    private Node front;		//队首指针
    private Node rear;		//队尾指针

    public LinkQueue() {
        front = rear = null;
    }

    //置空
    public void clear() {
        front = rear = null;
    }

    //是否为空
    public boolean isEmpty() {
        return front == null;
    }

    //队列长度
    public int length() {
        Node p = front;		//p指向首结点(队首元素)
        int length = 0;		//计数器
        while (p != null) {
            p = p.next;
            ++length;
        }
        return length;
    }

    //取队首元素
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    //删除队首元素(出队)
    public Object poll() {
        if (isEmpty()) {
            return null;
        }
        Node p = front;			//p指向队首结点
        front = front.next;		//修改指针，front指向下一个结点
        if (p == rear) {		//只有一个结点时，被删除的也是
            rear = null;		//队尾结点
        }
        return p.data;			//返回结点中的数据域
    }

    //在队尾插入元素(入队)
    public void insert(Object obj) {
        Node p = new Node(obj);	//将插入对象封装成新的结点
        if (isEmpty()) {
            front = rear = p;	//新插入元素即是队首元素也是队尾元素
        } else {
            rear.next = p;		//当前队尾指针指向新插入的结点
            rear = p;			//将新插入节点变为队尾
        }
    }
}
