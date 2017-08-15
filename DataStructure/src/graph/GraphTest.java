package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test() {
		Graph graph = new Graph(12, true);
		for (int i = 0; i < 12; i++) {
			graph.initVertex(i);
		}
		graph.insertNode(0, 10);
		graph.insertNode(0, 11);
		graph.insertNode(0, 1);

		graph.insertNode(1, 2);
		graph.insertNode(1, 5);
		graph.insertNode(2, 3);

		graph.insertNode(5, 4);
		graph.insertNode(6, 7);
		graph.insertNode(9, 8);
		graph.insertNode(10, 9);
		graph.insertNode(10, 6);

		graph.getAdjTable();

		graph.DFSTraversal(graph);
		System.out.println("DFS遍历：");
		System.out.println(graph.dfslist);

		// graph.BFSTraversal(graph);
		// System.out.println("BFS遍历：");
		// System.out.println(graph.bfslist);
	}

	@Test
	public void testTopoSort() {
		Graph graph = new Graph(9, true);
		for (int i = 0; i < 9; i++) {
			graph.initVertex(i);
		}

		graph.insertNode(0, 2);
		graph.insertNode(0, 7);
		graph.insertNode(1, 2);
		graph.insertNode(1, 3);
		graph.insertNode(1, 4);
		graph.insertNode(2, 3);
		graph.insertNode(3, 5);
		graph.insertNode(3, 6);
		graph.insertNode(4, 5);
		graph.insertNode(7, 8);
		graph.insertNode(8, 6);

		graph.getAdjTable();

		int[] order = new int[9];
		graph.TopoSort(graph, order);

	}

	@Test
	public void testCriticalPath() {
		Graph graph = new Graph(9, 11, true);
		for (int i = 0; i < 9; i++) {
			graph.initVertex(i);
		}

		graph.insertNode(0, 3, 5);
		graph.insertNode(0, 2, 4);
		graph.insertNode(0, 1, 6);
		graph.insertNode(3, 5, 2);
		graph.insertNode(2, 4, 1);
		graph.insertNode(1, 4, 1);
		graph.insertNode(5, 7, 4);
		graph.insertNode(4, 7, 8);
		graph.insertNode(4, 6, 9);
		graph.insertNode(7, 8, 4);
		graph.insertNode(6, 8, 2);

		graph.getAdjTable();

		graph.getEdgeWeight();

		// 先拓扑排序
		int[] order = new int[9];
		graph.TopoSort(graph, order);

		// 后计算关键路径
		int[] earliest = new int[9], latest = new int[9];
		graph.Earliest(graph, order, earliest);
		graph.Latest(graph, order, earliest, latest);
		graph.CriticalPath(graph, earliest, latest);
	}

	@Test
	public void testPrim() {
		Graph graph = new Graph(6, false);// 无向图
		for (int i = 0; i < 6; i++) {
			graph.initVertex(i);
		}

		graph.insertNode(0, 1, 6);
		graph.insertNode(0, 2, 1);
		graph.insertNode(0, 3, 5);
		graph.insertNode(1, 0, 6);
		graph.insertNode(1, 2, 5);
		graph.insertNode(1, 4, 3);
		graph.insertNode(2, 0, 1);
		graph.insertNode(2, 1, 5);
		graph.insertNode(2, 3, 5);
		graph.insertNode(2, 4, 6);
		graph.insertNode(2, 5, 4);
		graph.insertNode(3, 0, 5);
		graph.insertNode(3, 2, 5);
		graph.insertNode(3, 5, 2);
		graph.insertNode(4, 1, 3);
		graph.insertNode(4, 2, 6);
		graph.insertNode(4, 5, 6);
		graph.insertNode(5, 4, 6);
		graph.insertNode(5, 2, 4);
		graph.insertNode(5, 3, 2);

		graph.getAdjTable();

		int[] nearest = new int[6];
		int[] lowcost = new int[6];
		graph.Prim(graph, 0, nearest, lowcost);
		System.out.println("最小代价生成树：");
		for (int i = 0; i < 6; i++) {
			System.out.println("(" + nearest[i] + "," + i + "," + lowcost[i] + ")");
		}
		System.out.println();
	}

	@Test
	public void testKruskal() {
		Comparator<EdgeNode> order = new Comparator<EdgeNode>() {
			@Override
			public int compare(EdgeNode o1, EdgeNode o2) {
				return o1.weight - o2.weight;
			}
		};
		PriorityQueue<EdgeNode> queue = new PriorityQueue<>(order);

		queue.add(new EdgeNode(0, 2, 1));
		queue.add(new EdgeNode(0, 1, 6));
		queue.add(new EdgeNode(0, 3, 5));
		queue.add(new EdgeNode(1, 2, 5));
		queue.add(new EdgeNode(1, 4, 3));
		queue.add(new EdgeNode(2, 3, 5));
		queue.add(new EdgeNode(2, 4, 6));
		queue.add(new EdgeNode(2, 5, 4));
		queue.add(new EdgeNode(3, 5, 2));
		queue.add(new EdgeNode(4, 5, 6));

		Graph g = new Graph();
		g.Kruskal(queue, 6);
	}
}
