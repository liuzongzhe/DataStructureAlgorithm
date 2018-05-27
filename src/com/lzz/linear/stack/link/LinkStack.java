package com.lzz.linear.stack.link;

import com.lzz.linear.node.Node;

/**
 * 链表栈LinkStack
 * 没有头结点，直接将栈顶元素放在单链表的首部为首结点
 * Author lzz
 * Date   2018/5/27
 */
public class LinkStack {
    //链表的头指针
    private Node top;

    public LinkStack() {
        top = null;
    }

    //置空
    public void clear() {
        top = null;
    }

    //是否为空
    public boolean isEmpty() {
        return top == null;
    }

    //长度
    public int length() {
        Node p = top;		//p指向首结点(栈顶元素)
        int length = 0;		//计数器
        while (p != null) {
            p = p.next;		//依次指向后继结点
            ++length;
        }
        return length;
    }

    //取栈顶元素
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    //入栈操作
    public void push(Object obj) {
        Node p = new Node(obj);		//将插入对象封装成新的结点
        p.next = top;				//p指向之前的栈顶结点元素
        top = p;					//p再重新成为新的栈顶结点
    }

    //出栈操作（删除并返回栈顶元素）
    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        Node p = top;				//p指向栈顶结点
        top = top.next;				//修改指针，top指向下一个结点
        return p.data;				//返回结点中的数据域
    }

    /**
     * 打印所有元素
     */
    public void display() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");
        stack.push("ddd");
        stack.push("eee");

        stack.display();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.display();
    }
}
