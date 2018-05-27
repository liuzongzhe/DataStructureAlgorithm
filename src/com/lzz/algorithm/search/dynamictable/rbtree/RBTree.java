package com.lzz.algorithm.search.dynamictable.rbtree;

/**
 * Author eson_15
 * Date   2016-4-18
 */
public class RBTree<T extends Comparable<T>> {
    private RBNode<T> root; //根节点
    private static final boolean RED = false; //定义红黑树标志
    private static final boolean BLACK = true;

    //内部类：节点类
    public class RBNode<T extends Comparable<T>>{
        boolean color; //颜色
        T key; //关键字(键值)
        RBNode<T> left; //左子节点
        RBNode<T> right; //右子节点
        RBNode<T> parent; //父节点

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "" + key + (this.color == RED? "R" : "B");
        }
    }

    public RBTree() {
        root = null;
    }

    public RBNode<T> parentOf(RBNode<T> node) { //获得父节点
        return node != null? node.parent : null;
    }

    public void setParent(RBNode<T> node, RBNode<T> parent) { //设置父节点
        if(node != null)
            node.parent = parent;
    }

    public boolean colorOf(RBNode<T> node) { //获得节点的颜色
        return node != null? node.color : BLACK;
    }

    public boolean isRed(RBNode<T> node) { //判断节点的颜色
        return (node != null)&&(node.color == RED)? true : false;
    }

    public boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RBNode<T> node) { //设置节点的颜色
        if(node != null)
            node.color = RED;
    }

    public void setBlack(RBNode<T> node) {
        if(node != null) {
            node.color = BLACK;
        }
    }

    public void setColor(RBNode<T> node, boolean color) {
        if(node != null)
            node.color = color;
    }

    /***************** 前序遍历红黑树 *********************/
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(RBNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /***************** 中序遍历红黑树 *********************/
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(RBNode<T> tree) {
        if(tree != null) {
            preOrder(tree.left);
            System.out.print(tree.key + " ");
            preOrder(tree.right);
        }
    }

    /***************** 后序遍历红黑树 *********************/
    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(RBNode<T> tree) {
        if(tree != null) {
            preOrder(tree.left);
            preOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /**************** 查找红黑树中键值为key的节点 ***************/
    public RBNode<T> search(T key) {
        return search(root, key);
//      return search2(root, key); //使用递归的方法，本质一样的
    }

    private RBNode<T> search(RBNode<T> x, T key) {
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if(cmp < 0)
                x = x.left;
            else if(cmp > 0)
                x = x.right;
            else
                return x;
        }
        return x;
    }
    //使用递归
    private RBNode<T> search2(RBNode<T> x, T key) {
        if(x == null)
            return x;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return search2(x.left, key);
        else if(cmp > 0)
            return search2(x.right, key);
        else
            return x;
    }

    /**************** 查找最小节点的值  **********************/
    public T minValue() {
        RBNode<T> node = minNode(root);
        if(node != null)
            return node.key;
        return null;
    }

    private RBNode<T> minNode(RBNode<T> tree) {
        if(tree == null)
            return null;
        while(tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /******************** 查找最大节点的值 *******************/
    public T maxValue() {
        RBNode<T> node = maxNode(root);
        if(node != null)
            return node.key;
        return null;
    }

    private RBNode<T> maxNode(RBNode<T> tree) {
        if(tree == null)
            return null;
        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    /********* 查找节点x的后继节点,即大于节点x的最小节点 ***********/
    public RBNode<T> successor(RBNode<T> x) {
        //如果x有右子节点，那么后继节点为“以右子节点为根的子树的最小节点”
        if(x.right != null)
            return minNode(x.right);
        //如果x没有右子节点，会出现以下两种情况：
        //1. x是其父节点的左子节点，则x的后继节点为它的父节点
        //2. x是其父节点的右子节点，则先查找x的父节点p，然后对p再次进行这两个条件的判断
        RBNode<T> p = x.parent;
        while((p != null) && (x == p.right)) { //对应情况2
            x = p;
            p = x.parent;
        }
        return p; //对应情况1

    }

    /********* 查找节点x的前驱节点，即小于节点x的最大节点 ************/
    public RBNode<T> predecessor(RBNode<T> x) {
        //如果x有左子节点，那么前驱结点为“左子节点为根的子树的最大节点”
        if(x.left != null)
            return maxNode(x.left);
        //如果x没有左子节点，会出现以下两种情况：
        //1. x是其父节点的右子节点，则x的前驱节点是它的父节点
        //2. x是其父节点的左子节点，则先查找x的父节点p，然后对p再次进行这两个条件的判断
        RBNode<T> p = x.parent;
        while((p != null) && (x == p.left)) { //对应情况2
            x = p;
            p = x.parent;
        }
        return p; //对应情况1
    }

    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;

        if(y.left != null)
            y.left.parent = x;

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;

        if(x.parent == null) {
            this.root = y; //如果x的父节点为空，则将y设为父节点
        } else {
            if(x == x.parent.left) //如果x是左子节点
                x.parent.left = y; //则也将y设为左子节点
            else
                x.parent.right = y;//否则将y设为右子节点
        }

        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;

        if(x.right != null)
            x.right.parent = y;

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        x.parent = y.parent;

        if(y.parent == null) {
            this.root = x; //如果x的父节点为空，则将y设为父节点
        } else {
            if(y == y.parent.right) //如果x是左子节点
                y.parent.right = x; //则也将y设为左子节点
            else
                y.parent.left = x;//否则将y设为右子节点
        }

        //3. 将y的左子节点设为x，将x的父节点设为y
        x.right = y;
        y.parent = x;
    }

    /*********************** 向红黑树中插入节点 **********************/
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(key, RED, null, null, null);
        if(node != null)
            insert(node);
    }

    //将节点插入到红黑树中，这个过程与二叉搜索树是一样的
    private void insert(RBNode<T> node) {
        RBNode<T> current = null; //表示最后node的父节点
        RBNode<T> x = this.root; //用来向下搜索用的

        //1. 找到插入的位置
        while(x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if(cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = current; //找到了位置，将当前current作为node的父节点

        //2. 接下来判断node是插在左子节点还是右子节点
        if(current != null) {
            int cmp = node.key.compareTo(current.key);
            if(cmp < 0)
                current.left = node;
            else
                current.right = node;
        } else {
            this.root = node;
        }

        //3. 将它重新修整为一颗红黑树
        insertFixUp(node);
    }

    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent; //定义父节点和祖父节点

        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while(((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);//获得祖父节点

            //若父节点是祖父节点的左子节点，下面else与其相反
            if(parent == gparent.left) {
                RBNode<T> uncle = gparent.right; //获得叔叔节点

                //case1: 叔叔节点也是红色
                if(uncle != null && isRed(uncle)) {
                    setBlack(parent); //把父节点和叔叔节点涂黑
                    setBlack(uncle);
                    setRed(gparent); //把祖父节点涂红
                    node = gparent; //将位置放到祖父节点处
                    continue; //继续while，重新判断
                }

                //case2: 叔叔节点是黑色，且当前节点是右子节点
                if(node == parent.right) {
                    leftRotate(parent); //从父节点处左旋
                    RBNode<T> tmp = parent; //然后将父节点和自己调换一下，为下面右旋做准备
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色，且当前节点是左子节点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else { //若父节点是祖父节点的右子节点,与上面的完全相反，本质一样的
                RBNode<T> uncle = gparent.left;

                //case1: 叔叔节点也是红色
                if(uncle != null & isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if(node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        //将根节点设置为黑色
        setBlack(this.root);
    }

    /*********************** 删除红黑树中的节点 **********************/
    public void remove(T key) {
        RBNode<T> node;
        if((node = search(root, key)) != null)
            remove(node);
    }

    private void remove(RBNode<T> node) {
        RBNode<T> child, parent;
        boolean color;

        //1. 被删除的节点“左右子节点都不为空”的情况
        if((node.left != null) && (node.right != null)) {
            //先找到被删除节点的后继节点，用它来取代被删除节点的位置
            RBNode<T> replace = node;
            //  1). 获取后继节点
            replace = replace.right;
            while(replace.left != null)
                replace = replace.left;

            //  2). 处理“后继节点”和“被删除节点的父节点”之间的关系
            if(parentOf(node) != null) { //要删除的节点不是根节点
                if(node == parentOf(node).left)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else { //否则
                this.root = replace;
            }

            //  3). 处理“后继节点的子节点”和“被删除节点的子节点”之间的关系
            child = replace.right; //后继节点肯定不存在左子节点！
            parent = parentOf(replace);
            color = colorOf(replace);//保存后继节点的颜色
            if(parent == node) { //后继节点是被删除节点的子节点
                parent = replace;
            } else { //否则
                if(child != null)
                    setParent(child, parent);
                parent.left = child;
                replace.right = node.right;
                setParent(node.right, replace);
            }
            replace.parent = node.parent;
            replace.color = node.color; //保持原来位置的颜色
            replace.left = node.left;
            node.left.parent = replace;

            if(color == BLACK) { //4. 如果移走的后继节点颜色是黑色，重新修整红黑树
                removeFixUp(child, parent);//将后继节点的child和parent传进去
            }
            node = null;
            return;
        }
    }
    //node表示待修正的节点，即后继节点的子节点（因为后继节点被挪到删除节点的位置去了）
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;

        while((node == null || isBlack(node)) && (node != this.root)) {
            if(parent.left == node) { //node是左子节点，下面else与这里的刚好相反
                other = parent.right; //node的兄弟节点
                if(isRed(other)) { //case1: node的兄弟节点other是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                //case2: node的兄弟节点other是黑色的，且other的两个子节点也都是黑色的
                if((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    //case3: node的兄弟节点other是黑色的，且other的左子节点是红色，右子节点是黑色
                    if(other.right == null || isBlack(other.right)) {
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }

                    //case4: node的兄弟节点other是黑色的，且other的右子节点是红色，左子节点任意颜色
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else { //与上面的对称
                other = parent.left;

                if (isRed(other)) {
                    // Case 1: node的兄弟other是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: node的兄弟other是黑色，且other的俩个子节点都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // Case 3: node的兄弟other是黑色的，并且other的左子节点是红色，右子节点为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: node的兄弟other是黑色的；并且other的左子节点是红色的，右子节点任意颜色
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node!=null)
            setBlack(node);
    }

    /****************** 销毁红黑树 *********************/
    public void clear() {
        destroy(root);
        root = null;
    }

    private void destroy(RBNode<T> tree) {
        if(tree == null)
            return;
        if(tree.left != null)
            destroy(tree.left);
        if(tree.right != null)
            destroy(tree.right);
        tree = null;
    }

    /******************* 打印红黑树 *********************/
    public void print() {
        if(root != null) {
            print(root, root.key, 0);
        }
    }
    /*
     * key---节点的键值
     * direction--- 0:表示该节点是根节点
     *              1:表示该节点是它的父节点的左子节点
     *              2:表示该节点是它的父节点的右子节点
     */
    private void print(RBNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(0 == direction)
                System.out.printf("%2d(B) is root\n", tree.key);
            else
                System.out.printf("%2d(%s) is %2d's %6s child\n",
                        tree.key, isRed(tree)?"R":"b", key, direction == 1?"right":"left");
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }
}
