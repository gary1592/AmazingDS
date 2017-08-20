package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class HuffmanTree {

	BinaryTreeUtils btUtils = new BinaryTreeUtils();

	List<TreeNode> leafs = new ArrayList<TreeNode>();

	/**
	 * 构建哈夫曼树 O(n^2)
	 * 
	 * @param w
	 * @param n
	 * @return
	 */
	public BinaryTree HFMTree(int[] w, int n) {
		TreeNode[] tn = new TreeNode[n];
		int i, k, s1, s2;
		// 初始化每一个节点
		for (i = 0; i < n; i++) {
			tn[i] = new TreeNode(w[i]);
		}
		Object[] min;
		for (k = n; k > 1; k--) {// 每次循环，节点个数减一
			min = findTwoMin(tn, k);
			s1 = (int) min[0];
			s2 = (int) min[1];
			// 生成新节点
			TreeNode temp = new TreeNode(tn[s1].val + tn[s2].val);
			btUtils.insertNode(temp, tn[s1], tn[s2]);
			tn[s1].parent = temp;
			tn[s2].parent = temp;
			// 最小点取代新节点
			tn[s1] = temp;
			// 指定编码
			tn[s1].lc = 0;
			tn[s1].rc = 1;
			// 次小点取代最后一个节点
			tn[s2] = tn[k - 1];
		}

		BinaryTree bt = new BinaryTree(tn[0]);
		return bt;
	}

	/**
	 * 找两个最小值的下标 O(n)
	 * 
	 * @param ht
	 * @param k
	 * @return
	 */
	public Object[] findTwoMin(TreeNode[] ht, int k) {
		int i;
		Map<Integer, Integer> map = new TreeMap<>();
		for (i = 0; i < k; i++) {
			map.put(ht[i].val, i);// 二叉搜索树的插入 O(log(n))
		}
		// 输出排好序的新节点
		System.out.println(map);// 中序遍历 O(n)

		Object[] min = new Integer[map.size()];
		min = map.values().toArray();
		return min;
	}

	/**
	 * 哈夫曼编码 O(n)
	 * 
	 * @param leaf
	 */
	public void HFMCode(TreeNode leaf) {
		TreeNode tmp = leaf;
		StringBuilder sb = new StringBuilder();
		// 叶子-->根
		while (leaf.parent != null) {
			if (leaf.parent.left == leaf) {
				sb.append(leaf.parent.lc);
			}
			if (leaf.parent.right == leaf) {
				sb.append(leaf.parent.rc);
			}
			leaf = leaf.parent;
		}
		// 根-->叶子
		sb.reverse();
		System.out.println(tmp.val + ":" + sb);
	}

	/**
	 * 获取所有叶子节点 O(n)
	 * 
	 * @param tn
	 */
	public void getLeafs(TreeNode tn) {
		if (tn.left != null)
			getLeafs(tn.left);
		if (tn.right != null)
			getLeafs(tn.right);

		if (tn.left == null && tn.right == null) {
			leafs.add(tn);
		}
	}

	@Test
	public void test() {
		int[] list = { 9, 11, 13, 3, 5, 12 };

		System.out.println("构造哈夫曼树：");
		BinaryTree bt = HFMTree(list, list.length);

		System.out.println("哈夫曼树层次遍历：");
		btUtils.levelOrder(bt.root);
		System.out.println();

		getLeafs(bt.root);
		System.out.println("所有叶子节点为：");
		for (TreeNode tn : leafs) {
			System.out.print(tn.val + " ");
		}
		System.out.println();

		System.out.println("哈夫曼编码：");
		for (int i = 0; i < list.length; i++) {
			HFMCode(leafs.get(i));
		}

	}
}
