package com.lzz.linear.stack.sequence;

/**
 * 顺序栈SeqStack
 * 对象数组+控制指针
 * Author lzz
 * Date   2018/5/27
 */
public class SeqStack {
    //定义对象数组
    private Object[] stackElem;
    //栈为空时，top=0，非空栈时，top指向栈顶元素的下一个存储位置
    private int top;

    //初始化存储容量为maxSize的空栈
    public SeqStack(int maxSize) {
        top = 0;
        stackElem = new Object[maxSize];
    }

    //清空方法
    public void clear() {
        top = 0;
    }

    //是否为空方法
    public boolean isEmpty() {
        return top == 0;
    }

    //元素个数方法
    public int length() {
        return top;
    }

    //取栈顶元素
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return stackElem[top-1];
    }

    //入栈操作
    public void push(Object obj) {
        if (top == stackElem.length) {
            throw new RuntimeException("栈已满");
        }
        stackElem[top++] = obj;		//数据放入栈顶top，然后top再+1
    }

    //出栈操作（删除并返回栈顶元素）
    public Object pop() {
        if (isEmpty()) {
            return -1;		//表示空栈
        }
        return stackElem[--top];	//top-1指向栈顶，然后返回栈顶元素
    }

    /*
     * 打印所有元素
     */
    public void display() {
        for (int i = top-1; i >= 0; i--) {
            System.out.print(stackElem[i] + " ");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        SeqStack stack = new SeqStack(10);
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
