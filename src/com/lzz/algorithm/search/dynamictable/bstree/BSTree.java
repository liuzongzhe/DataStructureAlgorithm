package com.lzz.algorithm.search.dynamictable.bstree;

/**
 *  二叉排序树BSTree
 *  定义：
 *  (1) 若左子树不为空，则左子树上所有结点的值均小于根结点的值
 *  (2) 若右子树不为空，则右子树上所有节点的值均大于根结点的值
 *  (3) 左右子树也都是二叉排序树
 * Author lzz
 * Date   2018/5/27
 */
public class BSTree {
    public BiTreeNode root;	//树的根结点

    public BSTree() {
        root = null;
    }
    public BSTree(BiTreeNode root) {
        this.root = root;
    }

    /**
     * 先根遍历二叉树的 递归算法
     */
    public void preRootTraverse() {
        preRootTraverse(root);
    }
    private void preRootTraverse(BiTreeNode T) {
        if (T != null) {
            System.out.print(T.data+" ");	//访问根结点
            preRootTraverse(T.lchild);		//遍历左子树
            preRootTraverse(T.rchild);		//遍历右子树
        }
    }

    /**
     * 中根遍历二叉树的 递归算法
     */
    public void inRootTraverse() {
        inRootTraverse(root);
    }
    private void inRootTraverse(BiTreeNode T) {
        if (T != null) {
            inRootTraverse(T.lchild);		//遍历左子树
            System.out.print(T.data+" ");	//访问根结点
            inRootTraverse(T.rchild);		//遍历右子树
        }
    }

    /**
     * 后根遍历二叉树的 递归算法
     */
    public void postRootTraverse() {
        postRootTraverse(root);
    }
    private void postRootTraverse(BiTreeNode T) {
        if (T != null) {
            postRootTraverse(T.lchild);		//遍历左子树
            postRootTraverse(T.rchild);		//遍历右子树
            System.out.print(T.data+" ");	//访问根结点
        }
    }

    /**
     * 二叉排序树查找算法
     * 1. 查找树为空，则查找失败
     * 2. 查找时非空：
     *		(1) k<结点域值，则在左子树查找
     *		(2) k>结点域值，则在右子树查找
     *		(3) k=结点域值，查找成功
     */
    public Integer searchBST(Integer key) {
        if (key == null) {
            return null;						//查找失败
        }
        return searchBST(root, key);
    }
    private Integer searchBST(BiTreeNode T, Integer key) {
        if (T == null) {
            return null;						//查找失败
        }
        if (key < T.data) {
            return searchBST(T.lchild, key);	//在左子树中查找
        }
        if (key > T.data) {
            return searchBST(T.rchild, key);	//在右子树中查找
        }
        return T.data;							//查找成功
    }

    /**
     * 二叉排序树插入算法
     * 1. 查找数中待插入的结点已存在，则不用插入
     * 2. 否则，待插入结点只能作为叶子结点插入
     */
    public boolean insertBST(Integer key) {
        if (key == null) {
            return false;						//插入失败
        }
        if (root == null) {
            root = new BiTreeNode(key);			//树为空，直接插入
            return true;
        }
        return insertBST(root, key);
    }
    private boolean insertBST(BiTreeNode T, Integer key) {
        if (key < T.data) {
            if (T.lchild == null) {
                T.lchild = new BiTreeNode(key);
                return true;
            } else {
                return insertBST(T.lchild, key);
            }
        }
        if (key > T.data) {
            if (T.rchild == null) {
                T.rchild = new BiTreeNode(key);
                return true;
            } else {
                return insertBST(T.rchild, key);
            }
        }
        return false;							//结点存在，不用插入
    }

    /**
     * 二叉排序树的删除算法
     */
    public Integer removeBST(Integer key) {
        if (root == null || key == null) {
            return null;
        }
        return removeBST(root, key, null);
    }
    private Integer removeBST(BiTreeNode T, Integer key, BiTreeNode parent) {
        if (T == null) {
            return null;
        }

        if (key < T.data) {
            return removeBST(T.lchild, key, T);
        }
        if (key > T.data) {
            return removeBST(T.rchild, key, T);
        }

        //以下是key==T.data的情况

        //左右子树不为空
        if (T.lchild != null && T.rchild != null) {
            BiTreeNode next = T.rchild;
            while (next.lchild != null) {
                next = next.lchild;
            }
            T.data = next.data;
            return removeBST(T.rchild, key, T);
        }

        //T是度为1且是叶子结点也是根结点
        if (parent == null) {
            if (T.lchild != null) {
                root = T.lchild;
            } else {
                root = T.rchild;
            }
            return T.data;
        }

        //判断T是左子结点还是右子结点
        if (T == parent.lchild) {
            if (T.lchild != null) {
                parent.lchild = T.lchild;
            } else {
                parent.lchild = T.rchild;
            }
        } else {
            if (T.lchild != null) {
                parent.rchild = T.lchild;
            } else {
                parent.rchild = T.rchild;
            }
        }
        return T.data;
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
		/*----------------------
					 49
				12		    65
			 11     35    50
				  15		60
		 -----------------------*/
        //创建一个二叉树(由叶子结点往上依次添加,最后是根结点)
        BiTreeNode N11 = new BiTreeNode(11);
        BiTreeNode N15 = new BiTreeNode(15);
        BiTreeNode N60 = new BiTreeNode(60);
        BiTreeNode N35 = new BiTreeNode(35, N15, null);
        BiTreeNode N12 = new BiTreeNode(12, N11, N35);
        BiTreeNode N50 = new BiTreeNode(50, null, N60);
        BiTreeNode N65 = new BiTreeNode(65, N50, null);
        BiTreeNode N49 = new BiTreeNode(49, N12, N65);

        BSTree tree = new BSTree(N49);
        BiTreeNode root = tree.root;

        System.out.print("中根遍历：");
        tree.inRootTraverse();
        System.out.println();

        System.out.println(tree.searchBST(20));
        System.out.println(tree.insertBST(25));
        System.out.println(tree.removeBST(50));

        System.out.print("中根遍历：");
        tree.inRootTraverse();
        System.out.println();

        System.out.println("创建一个空树:");
        tree = new BSTree();
        for (int i = 1; i <= 10; i++) {
            tree.insertBST(i);
        }
        root = tree.root;
        System.out.print("中根遍历：");
        tree.inRootTraverse();
        System.out.println();
    }

}
