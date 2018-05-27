package com.lzz.nonlinear.tree;

import com.lzz.linear.queue.link.LinkQueue;
import com.lzz.nonlinear.node.BiTreeNode;

/**
 * 二叉树的链式存储结构
 * Author lzz
 * Date   2018/5/27
 */
public class BiTree {
    private BiTreeNode root;	//树的根结点

    public BiTree() {
        root = null;
    }

    public BiTree(BiTreeNode root) {
        this.root = root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }
    public BiTreeNode getRoot() {
        return root;
    }

    /**
     * 由先根遍历和中根遍历序列 创建一个二叉树
     */
    public BiTree(String preOrder, String inOrder, int preIndex, int inIndex, int count) {
        if (count > 0) {
            char r = preOrder.charAt(preIndex);			//取先根遍历序列中的第一个结点作为根结点
            int i = 0;
            for (; i < count; i++) {
                if (r == inOrder.charAt(i + inIndex)) { //寻找根结点在中根遍历序列中的位置
                    break;
                }
            }
            root = new BiTreeNode(r);
            root.lchild = new BiTree(preOrder, inOrder, preIndex+1, inIndex, i).root;
            root.rchild = new BiTree(preOrder, inOrder, preIndex+i+1, inIndex+i+1, count-i-1).root;
        }
    }

    /**
     * 由标明空子树的先根遍历序列 创建一个二叉树
     */
    private static int index = 0;	//记录preStr的索引值
    public BiTree(String preStr) {

    }

    /**
     * 先根遍历二叉树的 递归算法(时空开销大，效率低)
     */
    public void preRootTraverse() {
        preRootTraverse(root);
    }
    private void preRootTraverse(BiTreeNode T) {
        if (T != null) {
            System.out.print(T.data);		//访问根结点
            preRootTraverse(T.lchild);		//遍历左子树
            preRootTraverse(T.rchild);		//遍历右子树
        }
    }

    /**
     * 先根遍历二叉树的 非递归算法
     * 1. 创建一个栈对象，根结点入栈
     * 2. 当栈不为空时，将栈顶结点弹出栈，并访问该结点
     * 3. 对当前结点的非空左子结点依次访问，并将当前访问结点的非空右子结点压入栈中
     * 4. 重复步骤2,3, 直到栈为空结束
     */
/*	public void preRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {
			LinkStack stack = new LinkStack();
			stack.push(T);								//根结点压入栈
			while (!stack.isEmpty()) {
				T = (BiTreeNode) stack.pop();
				System.out.print(T.data);				//访问当前结点
				while (T != null) {
					if (T.lchild != null) {
						System.out.print(T.lchild.data);//访问左子结点
					}
					if (T.rchild != null) {
						stack.push(T.rchild);			//右子结点入栈
					}
					T = T.lchild;						//左子结点变为下次循环结点
				}
			}
		}
	}*/

    /**
     * 中根遍历二叉树的 递归算法(时空开销大，效率低)
     */
    public void inRootTraverse() {
        inRootTraverse(root);
    }
    public void inRootTraverse(BiTreeNode T) {
        if (T != null) {
            inRootTraverse(T.lchild);		//遍历左子树
            System.out.print(T.data);		//访问根结点
            inRootTraverse(T.rchild);		//遍历右子树
        }
    }

    /**
     * 中根遍历二叉树的 非递归算法
     * 1. 创建一个栈对象，根结点入栈
     * 2. 当栈不为空时，将栈顶结点的非空左子结点相继压入栈
     * 3. 栈顶结点出栈并访问非空的栈顶结点，其中将栈顶结点的非空右子结点压入栈中
     * 4. 重复步骤2,3, 直到栈为空结束
     */
/*	public void inRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {
			LinkStack stack = new LinkStack();
			stack.push(T);								//根结点压入栈
			while (!stack.isEmpty()) {
				while (stack.peek() != null) {			//栈顶结点的左子结点相继入栈
					stack.push( ((BiTreeNode)stack.peek()).lchild );
				} stack.pop();							//空的栈顶结点出栈
				if (!stack.isEmpty()) {
					T = (BiTreeNode) stack.pop();		//非空栈顶结点出栈
					System.out.print(T.data);
					stack.push(T.rchild);				//结点的右子结点入栈
				}
			}
		}
	}*/

    /**
     * 后根遍历二叉树的 递归算法(时空开销大，效率低)
     */
    public void postRootTraverse() {
        postRootTraverse(root);
    }
    private void postRootTraverse(BiTreeNode T) {
        if (T != null) {
            postRootTraverse(T.lchild);		//遍历左子树
            postRootTraverse(T.rchild);		//遍历右子树
            System.out.print(T.data);		//访问根结点
        }
    }

    /**
     * 后根遍历二叉树的 非递归算法
     * 设置标记位：flag = false 表示当前栈顶结点未被访问
     *			  flag = true  表示当前栈顶结点已被访问
     * 指针 p ：指向当前遍历过程中最后一个被访问的结点
     * 1. 创建一个栈对象，根结点入栈，p初始值为null
     * 2. 若栈非空，则栈顶结点的非空左子结点相继入栈
     * 3. 若栈非空，查看栈顶结点：
     *    (1) 若栈顶结点的右子结点为空或者与p相等，(表明当前结点的右子树已经遍历完成，可以
     *    访问当前栈顶结点了)，则将栈顶结点弹出栈并访问，同时使p指向该结点， 并置flag=true;
     *    (2) 否则，将栈顶结点的右子结点压入栈，并置flag=false.
     * 4. flag=true，则重复步骤3; flag=false， 则重复步骤2,3
     */
/*	public void postRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {
			LinkStack stack = new LinkStack();
			stack.push(T);								//根结点压入栈
			Boolean flag;
			BiTreeNode p = null;

			while (!stack.isEmpty()) {

				while (stack.peek() != null) {			//栈顶结点的左子结点相继入栈
					stack.push( ((BiTreeNode)stack.peek()).lchild );
				} stack.pop();							//空的栈顶结点出栈

				while (!stack.isEmpty()) {
					T = (BiTreeNode) stack.peek();		//查看栈顶结点
					if (T.rchild == null || T.rchild == p) {
						System.out.print(T.data);
						stack.pop();					//栈顶结点出栈
						p = T;							//p指向刚被访问的结点
						flag = true;					//设置为访问标记
					} else {
						stack.push(T.rchild);			//右子结点入栈
						flag = false;					//设置为未访问标记
					}
					if (!flag) {						//flag=false,跳出循环
						break;
					}
				}
			}
		}
	}*/

    /**
     * 层次遍历二叉树的 (自上而下、自左向右)
     * 1. 创建一个队列对象，根结点入队
     * 2. 若队列非空，则将队首结点出队并访问该结点，再将该结点的非空左、右子结点依次入队
     * 3. 重复步骤2，直到队列为空结束
     */
    public void levelTraverse() {
        BiTreeNode T = root;
        if (T != null) {
            LinkQueue queue = new LinkQueue();
            queue.insert(T);
            while (!queue.isEmpty()) {
                T = (BiTreeNode) queue.poll();		//取首结点(父结点)，打印结点数据
                System.out.print(T.data);
                if (T.lchild != null) {
                    queue.insert(T.lchild);			//左子结点非空，入队列
                }
                if (T.rchild != null) {
                    queue.insert(T.rchild);			//右子结点非空，入队列
                }
            }
        }
    }

    /**
     * 查找算法
     * 基于先根遍历
     */
    public BiTreeNode searchNode(BiTreeNode T, Object x) {
        if (T != null) {
            if (T.data.equals(x)) {
                return T;
            } else {
                BiTreeNode lresult = searchNode(T.lchild, x);
                return lresult != null ? lresult : searchNode(T.rchild, x);
            }
        }
        return null;
    }

    /**
     * 统计结点数
     * 基于先根遍历
     */
    public int countNode(BiTreeNode T) {
        if (T != null) {
            return countNode(T.lchild) + countNode(T.rchild) + 1;
        } else {
            return 0;
        }
    }

    /**
     * 二叉树的深度
     * 基于后根遍历
     * 先求出左子树的深度，在求出右子树的深度，二者较大的一方+1就是二叉树深度
     */
    public int getDepth(BiTreeNode T) {
        if (T != null) {
            int lDepth = getDepth(T.lchild);				//遍历左子树
            int rDepth = getDepth(T.rchild);				//遍历右子树
            return (lDepth > rDepth ? lDepth : rDepth) + 1; //访问根结点
        }
        return 0;
    }

    /**
     * 相等算法
     * 注意：传入参数为结点对象
     * 基于先根遍历
     */
    public boolean isEqual(BiTreeNode T1, BiTreeNode T2) {
        if (T1 == null && T2 == null) {
            return true;
        }
        if (T1 != null && T2 != null) {
            if (T1.data.equals(T2.data)) {
                if (isEqual(T1.lchild, T2.lchild)) {
                    if (isEqual(T1.rchild, T2.rchild)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
		/*----------------------
					 A
				B		   C
			 D     E    F
				 G		 H
		 -----------------------*/
        //创建一个二叉树(由叶子结点往上依次添加,最后是根结点)
        BiTreeNode d = new BiTreeNode('D');
        BiTreeNode g = new BiTreeNode('G');
        BiTreeNode h = new BiTreeNode('H');
        BiTreeNode e = new BiTreeNode('E', g, null);
        BiTreeNode b = new BiTreeNode('B', d, e);
        BiTreeNode f = new BiTreeNode('F', null, h);
        BiTreeNode c = new BiTreeNode('C', f, null);
        BiTreeNode a = new BiTreeNode('A', b, c);

        BiTree tree = new BiTree(a);
        BiTreeNode root = tree.getRoot();

        System.out.print("先根遍历：");
        tree.preRootTraverse();
        System.out.println();

        System.out.print("中根遍历：");
        tree.inRootTraverse();
        System.out.println();

        System.out.print("后根遍历：");
        tree.postRootTraverse();
        System.out.println();

        System.out.print("层次遍历：");
        tree.levelTraverse();
        System.out.println();

        System.out.println(tree.searchNode(root, 'E'));
        System.out.println(tree.countNode(root));
        System.out.println(tree.getDepth(root));

        BiTree tree2 = tree;
        BiTreeNode root2 = tree2.getRoot();
        System.out.println(tree.isEqual(root, root2));
        BiTree tree3 = new BiTree();
        BiTreeNode root3 = tree3.getRoot();
        System.out.println(tree.isEqual(root, root3));

        String preOrder = "ABDGHCEIF";
        String inOrder = "GDHBAEICF";
        BiTree T = new BiTree(preOrder, inOrder, 0, 0, preOrder.length());
        System.out.print("后根遍历：");
        T.postRootTraverse();
        System.out.println();

    }
}
