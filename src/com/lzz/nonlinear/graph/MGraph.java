package com.lzz.nonlinear.graph;

import com.lzz.linear.queue.link.LinkQueue;

import java.util.Scanner;
/**
 * 图的四种类型：
 * 无向图，有向图，无向网，有向网
 * Author lzz
 * Date   2018/5/27
 */
enum GraphKind {
    UDG,	//无向图
    DG,		//有向图
    UDN,	//无向网
    DN;		//有向网
}
/**
 * 图：邻接矩阵存储结构
 */
public class MGraph {
    public final static int INFINITY = Integer.MAX_VALUE;	//表示无、有向网中的无穷大

    private GraphKind kind;		//图的种类标志
    private int vexNum;			//顶点数
    private int arcNum;			//边数
    private Object[] vexs;		//顶点数组
    private int[][] arcs;		//邻接矩阵

    public MGraph() {
        this(null, 0, 0, null, null);
    }

    public MGraph(GraphKind kind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) {
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }

    public GraphKind getKind() {
        return kind;
    }

    public int getVexNum() {
        return vexNum;
    }

    public int getArcNum() {
        return arcNum;
    }

    public int[][] getArcs() {
        return arcs;
    }

    public Object[] getVexs() {
        return vexs;
    }

    public Object getVex(int v) {
        if (v <0 || v >= vexNum) {
            throw new RuntimeException("该顶点不存在:" + v);
        }
        return vexs[v];
    }

    //选择创建哪种类型的图的方法
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("选择图的类型(UDG, DG, UDN, DN)：");
        GraphKind kind = GraphKind.valueOf(sc.next());
        switch (kind) {
            case UDG:
                createUDG();
                break;
            case DG:
                createDG();
                break;
            case UDN:
                createUDN();
                break;
            case DN:
                createDN();
                break;
        }
    }

    //1.无向图的创建算法
    private void createUDG() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入无向图的顶点数、边数：");
        vexNum = sc.nextInt();
        arcNum = sc.nextInt();

        vexs = new Object[vexNum];			//创建顶点数组
        System.out.println("请分别输入图的" + vexNum + "个顶点：");
        for (int v = 0; v < vexNum; v++) {
            vexs[v] = sc.next();			//初始化顶点数组
        }
        arcs = new int[vexNum][vexNum];		//创建邻接矩阵
        for (int v = 0; v < vexNum; v++) {
            for (int u = 0; u < vexNum; u++) {
                arcs[v][u] = 0;				//初始化邻接矩阵的值
            }
        }

        System.out.println("请输入" + arcNum + "个边,每个边的两个顶点：");
        for (int k = 0; k < arcNum; k++) {
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = arcs[u][v] = 1;	//矩阵对称赋值为1
        }
    }

    //2.有向图的创建算法
    private void createDG() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入有向图的顶点数、边数：");
        vexNum = sc.nextInt();
        arcNum = sc.nextInt();

        vexs = new Object[vexNum];			//创建顶点数组
        System.out.println("请分别输入图的" + vexNum + "个顶点：");
        for (int v = 0; v < vexNum; v++) {
            vexs[v] = sc.next();			//初始化顶点数组
        }
        arcs = new int[vexNum][vexNum];		//创建邻接矩阵
        for (int v = 0; v < vexNum; v++) {
            for (int u = 0; u < vexNum; u++) {
                arcs[v][u] = 0;				//初始化邻接矩阵的值
            }
        }

        System.out.println("请输入" + arcNum + "个边,每个边的两个顶点：");
        for (int k = 0; k < arcNum; k++) {
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = 1;					//矩阵不对称赋值为1
        }
    }

    //3.无向网的创建算法
    private void createUDN() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入无向网的顶点数、边数：");
        vexNum = sc.nextInt();
        arcNum = sc.nextInt();

        vexs = new Object[vexNum];			//创建顶点数组
        System.out.println("请分别输入图的" + vexNum + "个顶点：");
        for (int v = 0; v < vexNum; v++) {
            vexs[v] = sc.next();			//初始化顶点数组
        }
        arcs = new int[vexNum][vexNum];		//创建二维邻接矩阵
        for (int v = 0; v < vexNum; v++) {
            for (int u = 0; u < vexNum; u++) {
                arcs[v][u] = INFINITY;		//初始化邻接矩阵的值
            }
        }

        System.out.println("请输入" + arcNum + "个边,每个边的两个顶点及权值：");
        for (int k = 0; k < arcNum; k++) {
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = arcs[u][v] = sc.nextInt();	//矩阵对称赋值为权值
        }
    }

    //4.有向网的创建算法
    private void createDN() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入有向网的顶点数、边数：");
        vexNum = sc.nextInt();
        arcNum = sc.nextInt();

        vexs = new Object[vexNum];			//创建顶点数组
        System.out.println("请分别输入图的" + vexNum + "个顶点：");
        for (int v = 0; v < vexNum; v++) {
            vexs[v] = sc.next();			//初始化顶点数组
        }
        arcs = new int[vexNum][vexNum];		//创建二维邻接矩阵
        for (int v = 0; v < vexNum; v++) {
            for (int u = 0; u < vexNum; u++) {
                arcs[v][u] = INFINITY;		//初始化邻接矩阵的值
            }
        }

        System.out.println("请输入" + arcNum + "个边,每个边的两个顶点及权值：");
        for (int k = 0; k < arcNum; k++) {
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = sc.nextInt();		//矩阵不对称赋值为权值
        }
    }

    //顶点定位算法
    public int locateVex(Object obj) {
        for (int v = 0; v < vexNum; v++) {
            if (vexs[v].equals(obj)) {
                return v;
            }
        }
        return -1;
    }

    //查找第一个邻接点
    //0<=v<vexNum
    public int firstAdjVex(int v) {
        if (v < 0 || v >= vexNum) {
            throw new RuntimeException("该顶点不存在:" + v);
        }
        for (int j = 0; j < vexNum; j++) { //遍历第v行的元素查找
            if (arcs[v][j] != 0 && arcs[v][j] < INFINITY) {
                return j;
            }
        }
        return -1;
    }

    //查找下一个邻接点
    //查找v的一个邻接点w，之后的下一个邻接点
    public int nextAdjVex(int v, int w) {
        if (v < 0 || v >= vexNum) {
            throw new RuntimeException("该顶点不存在:" + v);
        }
        if (w < 0 || w >= vexNum) {
            throw new RuntimeException("该顶点不存在:" + w);
        }
        for (int j = w+1; j < vexNum; j++) { //遍历第v行的元素查找
            if (arcs[v][j] != 0 && arcs[v][j] < INFINITY) {
                return j;
            }
        }
        return -1;
    }

    private static boolean[] visited;			//顶点数组访问标志位
    /**
     * 广度优先遍历算法BFS
     * 类似于数的层次遍历
     */
    public static void BFSTraverse(MGraph G) {
        visited = new boolean[G.getVexNum()];
        for (int v = 0; v < G.getVexNum(); v++) {
            visited[v] = false;					//初始化false，表示未访问
        }
        for (int v = 0; v < G.getVexNum(); v++) {
            BFS(G, v);
        }
    }
    private static void BFS(MGraph G, int v) {
        if (!visited[v]) {						//顶点v未被访问时
            visited[v] = true;
            System.out.print(G.getVex(v).toString() + " ");
            LinkQueue Q = new LinkQueue();
            Q.insert(v);
            while (!Q.isEmpty()) {
                int u = (Integer) Q.poll();		//队头元素出队
                for (int w = G.firstAdjVex(u); w >= 0; w = G.nextAdjVex(u, w)) {
                    if (!visited[w]) {
                        visited[w] = true;
                        System.out.print(G.getVex(w).toString() + " ");
                        Q.insert(w);
                    }
                }
            }
        }
    }

    /**
     * 深度优先遍历算法DFS
     * 类似于数的先根遍历
     */
    public static void DFSTraverse(MGraph G) {
        visited = new boolean[G.getVexNum()];
        for (int v = 0; v < G.getVexNum(); v++) {
            visited[v] = false;					//初始化false，表示未访问
        }
        for (int v = 0; v < G.getVexNum(); v++) {
            DFS(G, v);
        }
    }
    private static void DFS(MGraph G, int v) {
        if (!visited[v]) {						//顶点v未被访问时
            visited[v] = true;
            System.out.print(G.getVex(v).toString() + " ");
            for (int w = G.firstAdjVex(v); w >= 0; w = G.nextAdjVex(v, w)) {
                DFS(G, w);
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        MGraph mg = new MGraph();
        mg.createGraph();

        for (int i = 0; i < 5; i++) {
            System.out.print("广度优先遍历:");
            MGraph.BFSTraverse(mg);
            System.out.println();
        }
        for (int i = 0; i < 5; i++) {
            System.out.print("深度优先遍历:");
            MGraph.DFSTraverse(mg);
            System.out.println();
        }
    }
}
