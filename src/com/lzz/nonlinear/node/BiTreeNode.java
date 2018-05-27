package com.lzz.nonlinear.node;

/**
 * 二叉树的结点类
 * Author lzz
 * Date   2018/5/27
 */
public class BiTreeNode {
    public Object data;			//结点的数据域
    public BiTreeNode lchild;	//左子结点域
    public BiTreeNode rchild;	//右子结点域

    public BiTreeNode() {
        this(null);
    }
    public BiTreeNode(Object data) {
        this(data, null, null);
    }

    public BiTreeNode(Object data, BiTreeNode lchild, BiTreeNode rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }
}
