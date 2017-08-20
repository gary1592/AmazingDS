package binaryTree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BinaryTreeTest {

	BinaryTreeUtils btUtils = new BinaryTreeUtils();

	@Test
	public void test() {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(20);
		TreeNode n3 = new TreeNode(30);
		TreeNode n4 = new TreeNode(40);
		TreeNode n5 = new TreeNode(50);
		TreeNode n6 = new TreeNode(60);
		TreeNode n7 = new TreeNode(70);

		btUtils.insertNode(n3, n1, n2);
		btUtils.insertNode(n6, n4, n5);
		btUtils.insertNode(n7, n3, n6);

		BinaryTree bt = new BinaryTree(n7);

		System.out.println("先序遍历（中－左－右）：");
		btUtils.preOrder(bt.root);
		System.out.println();

		System.out.println("非递归先序遍历：");
		btUtils.iPreOrder(bt.root);
		System.out.println();

		System.out.println("中序遍历（左－中－右）：");
		btUtils.midOrder(bt.root);
		System.out.println();

		System.out.println("非递归中序遍历：");
		btUtils.iMidOrder(bt.root);
		System.out.println();

		System.out.println("后序遍历（左－右－中）：");
		btUtils.postOrder(bt.root);
		System.out.println();

		System.out.println("非递归后序遍历：");
		btUtils.iPostOrder(bt.root);
		System.out.println();

		System.out.println("层次遍历：");
		btUtils.levelOrder(bt.root);
		System.out.println();

		System.out.println("所有路径：");
		List<String> list = new ArrayList<>();
		btUtils.binaryTreePaths(bt.root, list);
		System.out.println(list);

		System.out.println("最大节点：");
		int max = btUtils.maxNode(bt.root).val;
		System.out.println(max);
		System.out.println();
	}

}
