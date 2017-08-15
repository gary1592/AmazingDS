package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {

	int nodeNum, initNum = 0, edgeNum;
	Node[] vertex;// 顶点
	boolean type;// false:无向图 true：有向图
	boolean[] visited;// 节点是否已遍历
	boolean[] mark;// 节点是否选入生成树
	int[] inDegree;// 入度
	int[] a;// 边的权值

	List<Integer> dfslist = new ArrayList<>();
	List<Integer> bfslist = new ArrayList<>();

	public Graph() {

	}

	/**
	 * Constructor
	 * 
	 * @param nodeNum
	 * @param type
	 */
	public Graph(int nodeNum, boolean type) {
		this.nodeNum = nodeNum;
		this.type = type;
		vertex = new Node[nodeNum];
		visited = new boolean[nodeNum];
		mark = new boolean[nodeNum];
		inDegree = new int[nodeNum];
	}

	/**
	 * Constructor
	 * 
	 * @param nodeNum
	 * @param edgeNum
	 * @param type
	 */
	public Graph(int nodeNum, int edgeNum, boolean type) {
		this.nodeNum = nodeNum;
		this.edgeNum = edgeNum;
		this.type = type;
		vertex = new Node[nodeNum];
		visited = new boolean[nodeNum];
		a = new int[edgeNum];
		inDegree = new int[nodeNum];
	}

	/**
	 * 初始化节点，存入顶点数组
	 * 
	 * @param id
	 */
	public void initVertex(int id) {
		Node node = new Node();
		node.id = id;
		node.next = null;
		vertex[initNum++] = node;
	}

	/**
	 * 初始化节点，存入顶点数组
	 * 
	 * @param id
	 * @param weight
	 */
	public void initVertex(int id, int weight) {
		Node node = new Node();
		node.id = id;
		node.weight = weight;
		node.next = null;
		vertex[initNum++] = node;
	}

	/**
	 * 插入节点
	 * 
	 * @param start
	 * @param end
	 */
	public void insertNode(int start, int end) {
		int pos = getVertexPos(start);
		int endpos = getVertexPos(end);
		if (pos != -1) {
			Node node = new Node();
			node.id = end;
			node.next = vertex[pos].next;
			vertex[pos].next = node;
			// 邻接点的入度加一
			inDegree[endpos]++;
		}
	}

	/**
	 * 插入节点
	 * 
	 * @param start
	 * @param end
	 * @param weight
	 */
	public void insertNode(int start, int end, int weight) {
		int pos = getVertexPos(start);
		int endpos = getVertexPos(end);
		if (pos != -1) {
			Node node = new Node();
			node.id = end;
			node.weight = weight;
			node.next = vertex[pos].next;
			vertex[pos].next = node;
			inDegree[endpos]++;
		}
	}

	/**
	 * 获取节点在顶点数组中的下标
	 * 
	 * @param vertexId
	 * @return
	 */
	public int getVertexPos(int vertexId) {
		for (int i = 0; i < nodeNum; i++)
			if (vertex[i].id == vertexId)
				return i;
		return -1;
	}

	/**
	 * 打印邻接表
	 */
	public void getAdjTable() {
		System.out.println("邻接表：");
		for (int i = 0; i < nodeNum; i++) {
			System.out.print(vertex[i].id + " ");
			Node node = vertex[i];
			while (node.next != null) {
				node = node.next;
				if (node.weight != 0) {
					System.out.print(node.id + "-" + node.weight + " ");
				} else {
					System.out.print(node.id + " ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 打印边的权值
	 */
	public void getEdgeWeight() {
		int k = 0;
		for (int i = 0; i < nodeNum; i++) {
			Node node = vertex[i];
			while (node.next != null) {
				node = node.next;
				if (node.weight != 0)
					a[k] = node.weight;
				k++;
			}
		}

		System.out.println("边的权值：");
		for (int i = 0; i < edgeNum; i++)
			System.out.print("a" + i + ":" + a[i] + " ");
		System.out.println();
	}

	/**
	 * 深度优先遍历 O(n+e)
	 * 
	 * @param g
	 */
	public void DFSTraversal(Graph g) {
		for (int i = 0; i < nodeNum; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < nodeNum; i++) {
			if (!visited[i])
				DFS(g, i, visited);
		}
	}

	public void DFS(Graph g, int i, boolean[] visited) {
		visited[i] = true;
		dfslist.add(vertex[i].id);
		Node eNode = vertex[i].next;
		while (eNode != null) {
			int pos = getVertexPos(eNode.id);
			if (!visited[pos])
				DFS(g, pos, visited);
			eNode = eNode.next;
		}
	}

	/**
	 * 广度优先遍历 O(n+e)
	 * 
	 * @param g
	 */
	public void BFSTraversal(Graph g) {
		for (int i = 0; i < nodeNum; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < nodeNum; i++) {
			if (!visited[i])
				BFS(g, i, visited);
		}
	}

	public void BFS(Graph g, int i, boolean[] visited) {
		Queue<Node> queue = new LinkedList<>();
		visited[i] = true;
		bfslist.add(vertex[i].id);
		queue.add(vertex[i]);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			while (node.next != null) {
				int p = getVertexPos(node.next.id);
				visited[p] = true;
				bfslist.add(node.next.id);
				node = vertex[p].next;
				queue.add(node);
			}
		}
	}

	/**
	 * 拓扑排序 O(n+e)
	 * 
	 * @param graph
	 * @param order
	 */
	public void TopoSort(Graph graph, int[] order) {
		System.out.println("初始入度：");
		for (int i = 0; i < nodeNum; i++)
			System.out.print(inDegree[i] + " ");
		System.out.println();

		Queue<Node> queue = new LinkedList<>();

		for (int i = 0; i < nodeNum; i++) {
			if (inDegree[i] == 0) {
				// 入度为零的节点入队列
				queue.offer(vertex[i]);
			}
		}

		int count = 0, nextPos;
		for (int i = 0; i < nodeNum; i++) {
			if (!queue.isEmpty()) {
				Node node = queue.poll();
				order[count++] = node.id;
				// 将该节点的所有邻接点的入度减一
				while (node.next != null) {
					node = node.next;
					nextPos = getVertexPos(node.id);
					inDegree[nextPos]--;
					if (inDegree[nextPos] == 0)
						queue.offer(vertex[nextPos]);
				}
			}
		}

		System.out.println("拓扑排序：");
		for (int i = 0; i < nodeNum; i++) {
			System.out.print(order[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 关键路径
	 * 
	 * @param graph
	 * @param earliest
	 * @param latest
	 */
	public void CriticalPath(Graph graph, int[] earliest, int[] latest) {
		int[] early = new int[edgeNum], late = new int[edgeNum];
		early[0] = earliest[0];
		early[1] = earliest[0];
		early[2] = earliest[0];
		early[3] = earliest[1];
		early[4] = earliest[2];
		early[5] = earliest[3];
		early[6] = earliest[4];
		early[7] = earliest[4];
		early[8] = earliest[5];
		early[9] = earliest[6];
		early[10] = earliest[7];

		late[0] = latest[1] - a[0];
		late[1] = latest[2] - a[1];
		late[2] = latest[3] - a[2];
		late[3] = latest[4] - a[3];
		late[4] = latest[4] - a[4];
		late[5] = latest[5] - a[5];
		late[6] = latest[6] - a[6];
		late[7] = latest[7] - a[7];
		late[8] = latest[7] - a[8];
		late[9] = latest[8] - a[9];
		late[10] = latest[8] - a[10];

		System.out.println("early:");
		for (int a : early)
			System.out.print(a + " ");
		System.out.println();

		System.out.println("late:");
		for (int a : late)
			System.out.print(a + " ");
		System.out.println();

		System.out.println("关键路径：");
		for (int i = 0; i < edgeNum; i++) {
			if (early[i] == late[i]) {
				System.out.print("a" + i + " ");
			}
		}
		System.out.println();
	}

	/**
	 * 事件可能的最早发生时间
	 * 
	 * @param g
	 * @param order
	 * @param earliest
	 */
	public void Earliest(Graph g, int[] order, int[] earliest) {
		int i, k;
		for (i = 0; i < nodeNum; i++) {
			earliest[i] = 0;
		}
		for (i = 0; i < nodeNum; i++) {
			k = order[i];
			Node node = vertex[getVertexPos(k)].next;
			while (node != null) {
				if (earliest[node.id] < earliest[k] + node.weight)
					earliest[node.id] = earliest[k] + node.weight;
				node = node.next;
			}
		}

		System.out.println("earliest:");
		for (int a : earliest)
			System.out.print(a + " ");
		System.out.println();
	}

	/**
	 * 事件允许的最晚发生时间
	 * 
	 * @param g
	 * @param order
	 * @param earliest
	 * @param latest
	 */
	public void Latest(Graph g, int[] order, int[] earliest, int[] latest) {
		int i, j, k;
		for (i = 0; i < nodeNum; i++) {
			latest[i] = earliest[nodeNum - 1];
		}
		for (i = nodeNum - 2; i > -1; i--) {
			j = order[i];
			Node node = vertex[getVertexPos(j)].next;
			while (node != null) {
				k = node.id;
				if (latest[j] > latest[k] - node.weight)
					latest[j] = latest[k] - node.weight;
				node = node.next;
			}
		}

		System.out.println("latest:");
		for (int a : latest)
			System.out.print(a + " ");
		System.out.println();
	}

	/**
	 * 最小代价生成树 O(n2)
	 * 
	 * @param graph
	 * @param k:起点
	 * @param nearest:保存最小权值边的起点
	 * @param lowcost:保存最小权值
	 */
	public void Prim(Graph graph, int k, int[] nearest, int[] lowcost) {
		// 初始化
		for (int i = 0; i < nodeNum; i++) {
			nearest[i] = -1;
			lowcost[i] = Integer.MAX_VALUE;
			mark[i] = false;// 标志当前点是否已经选到生成树上
		}

		// 将源点k加入生成树
		lowcost[k] = 0;
		nearest[k] = k;
		mark[k] = true;

		for (int i = 0; i < nodeNum; i++) {
			// 找出最小邻接点
			for (Node node = vertex[k].next; node != null; node = node.next) {
				if (!mark[node.id] && lowcost[node.id] > node.weight) {
					lowcost[node.id] = node.weight;
					nearest[node.id] = k;// nearest[end] = start
				}
			}

			int min = Integer.MAX_VALUE;
			for (int j = 0; j < nodeNum; j++) {
				if (!mark[j] && lowcost[j] < min) {
					min = lowcost[j];
					k = j;// 重新定义起点为该最小邻接点
				}
			}
			mark[k] = true;// 将该最小邻接点加入生成树
		}
	}

	/**
	 * 最小代价生成树 O(elbe)
	 * 
	 * @param queue
	 * @param n
	 */
	public void Kruskal(PriorityQueue<EdgeNode> queue, int n) {
		int k = 0, u, v;
		UFset ufset = new UFset();
		ufset.createUFset(n);
		System.out.println("最小代价生成树：");
		while (k < n - 1 && !queue.isEmpty()) {
			EdgeNode eNode = queue.poll();
			u = ufset.Find(eNode.u);
			v = ufset.Find(eNode.v);
			if (u != v) {
				ufset.Union(u, v);
				k++;
				System.out.println(eNode.u + "," + eNode.v + "," + eNode.weight);
			}
		}
		if (k < n - 2)
			System.out.println("This graph is not connected!");
	}
}
