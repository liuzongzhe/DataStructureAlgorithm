package com.lzz.algorithm.search.dynamictable.bbtree;

/**
 * B树定义
 * Author lzz
 * Date   2018/5/27
 */
public class BBTree<T> {
    public Node<T> root = null;
    public int degree;
    public BBTree(int t) {
        degree = t;
    }
    //...
}

/**
 * m阶B树的结点类
 */
class Node<T> {
    public int keyNum;			//关键字个数
    public boolean isLeaf;		//是否为树叶
    public T[] key;				//关键字数组
    public Node[] child;		//子树指针数组

    public Node parent;			//双亲结点数组
    public Node(int m) {		//设置m阶次
        keyNum = 0;
        isLeaf = true;key = (T[]) (new Object[2*m-1]);
        child = new Node[2*m];
        parent = null;
    }

}
/**
 * 查找结果类
 */
class Result {
    public Node resultNode;
    public int i;
    public boolean found;

}
