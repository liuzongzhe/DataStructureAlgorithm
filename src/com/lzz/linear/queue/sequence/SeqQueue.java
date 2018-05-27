package com.lzz.linear.queue.sequence;

/**
 * 队列-操作受限的线性表。进行删除的一端叫队头，进行插入的一端叫队尾。
 *
 * 顺序队列用顺序存储。删除队首元素有两种方式：
 *
 * ①不要求队首元素必须在下标为零的数组元素中；
 * 每次删除元素只需修改队首指针的位置，令front=front+1；
 * 显然优点为无须改变队列元素的位置，缺点为front值随删除元素而不断增加，
 * 整个队列向后移动，随着队尾元素的不断插入，必然会导致数组后端没有可用
 * 空间的情况，而数组前端的大量空间却被闲置。
 *
 * ②要求队首元素必须在下标为零的数组元素中；
 * 每次删除队首元素，令所有元素元素向前移动一个位置，显然优点为不浪费
 * 空间，缺点为所有元素的地址都要改变，效率低。
 *
 * 解决"假溢出"而在逻辑上采用循环顺序队列
 * 空队时，front = 0， rear = 0，
 *
 * 出队，front顺时针移动一个位置；front = (front+1) % size；
 * 入队，rear顺时针移动一个位置； rear = (rear+1) % size；
 * Author lzz
 * Date   2018/5/27
 */
//对象数组+控制指针
public class SeqQueue {
    //存放队列元素的数组
    private Object[] queueArray;

    private int front;      //队首所在数组元素的下标
    private int rear;       //队尾所在数组元素的下标的下一个位置

    private int count;      //队列元素的个数
    private int size;       //队列元素的容量

    public SeqQueue() {
        this.size = 10;
        this.queueArray = new Object[size];
        front = rear = count = 0;
    }

    public SeqQueue(int size) {
        this.size = size;
        this.queueArray = new Object[size];
        front = rear = count = 0;
    }

    //清空队列
    public void clear(){
        front = rear = count = 0;
    }

    //队列是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    //队列是否已满
    public boolean isFull() {
        return count == size;
    }

    //队列长度
    public int length() {
        return count;
    }

    /**
     * 得到队首元素
     */
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return queueArray[front];
    }
    /**
     * 删除队首元素(出队)
     */
    public Object poll() {
        if (isEmpty()) {
            return null;
        }
        Object obj = queueArray[front];
        front = (front+1) % size;
        count--;
        return obj;
    }
    /**
     * 在队尾插入元素(入队)
     */
    public void insert(Object obj) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        queueArray[rear] = obj;
        rear = (rear+1) % size;	//修改队尾指针
        count++;
    }

    //测试
    public static void main(String[] args) {
        SeqQueue queue = new SeqQueue(6);
        queue.insert("A");
        queue.insert("B");
        queue.insert("C");
        queue.insert("D");
        queue.insert("E");
        queue.insert("F");

        System.out.println(queue.length());
        System.out.println(queue.isFull());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.length());

    }
}
