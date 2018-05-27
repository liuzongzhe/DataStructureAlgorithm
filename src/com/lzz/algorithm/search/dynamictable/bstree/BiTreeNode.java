package com.lzz.algorithm.search.dynamictable.bstree;

/**
 * 二叉树的结点类
 * 数据域是Integer类型
 * 可引用RecordNode具有比较功能的类
 * Author lzz
 * Date   2018/5/27
 */
public class BiTreeNode {
    public Integer data;		//结点的数据域

    public BiTreeNode lchild;	//左子结点域
    public BiTreeNode rchild;	//右子结点域

    public BiTreeNode() {
        this(null);
    }
    public BiTreeNode(Integer data) {
        this(data, null, null);
    }

    public BiTreeNode(Integer data, BiTreeNode lchild, BiTreeNode rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }
}