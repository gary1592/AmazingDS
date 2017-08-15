package binaryTree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeService {

	/**
	 * 增删节点
	 */
	public void insertNode(BinaryTree bt, int data, BinaryTree left, BinaryTree right) {
		TreeNode tn = new TreeNode();
		tn.data = data;
		tn.left = left.root;
		tn.right = right.root;
		bt.root = tn;
	}

	public void deleteNode(BinaryTree bt, int data, BinaryTree left, BinaryTree right) {
		if (bt.root != null) {
			data = bt.root.data;
			left.root = bt.root.left;
			right.root = bt.root.right;
			bt.root = null;
		}
	}

	/**
	 * 先、中、后遍历二叉树
	 */
	public void visit(TreeNode tn) {
		System.out.print(tn.data + " ");
	}

	public void preOrder(TreeNode tn) {
		if (tn != null) {
			visit(tn);
			preOrder(tn.left);
			preOrder(tn.right);
		}
	}

	public void midOrder(TreeNode tn) {
		if (tn != null) {
			midOrder(tn.left);
			visit(tn);
			midOrder(tn.right);
		}
	}

	public void postOrder(TreeNode tn) {
		if (tn != null) {
			postOrder(tn.left);
			postOrder(tn.right);
			visit(tn);
		}
	}

	/**
	 * 层次遍历
	 */
	public void levelOrder(TreeNode tn) {
		Queue<TreeNode> queue = new LinkedBlockingQueue<>();
		queue.add(tn);
		while (!queue.isEmpty()) {
			// 节点出队列
			TreeNode temp = queue.poll();
			// 读取该节点信息
			visit(temp);
			// 左右子节点入队列
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);
		}
	}

	/**
	 * 节点个数（基于后序遍历）
	 */
	public int size(TreeNode tn) {
		if (tn == null)
			return 0;
		else
			return 1 + size(tn.left) + size(tn.right);
	}

	/**
	 * 二叉树高度（基于后序遍历）
	 */
	public int height(TreeNode tn) {
		if (tn == null)
			return 0;
		else
			return 1 + Math.max(height(tn.left), height(tn.right));
	}

	/**
	 * 非递归先、中、后序遍历
	 */
	public void iPreOrder(TreeNode tn) {
		TreeNode p = tn;
		Stack<TreeNode> stack = new Stack<>();
		if (p != null) {
			stack.push(p);
			while (!stack.isEmpty()) {
				p = stack.pop();
				visit(p);
				if (p.right != null)
					stack.push(p.right);
				if (p.left != null)
					stack.push(p.left);
			}
		}
	}

	public void iMidOrder(TreeNode tn) {
		TreeNode p = tn;
		Stack<TreeNode> stack = new Stack<>();
		while (p != null || !stack.isEmpty()) {
			while (p != null) {
				stack.push(p);
				p = p.left;
			}
			if (!stack.isEmpty()) {
				p = stack.pop();
				visit(p);
				p = p.right;
			}
		}
	}

	public void iPostOrder(TreeNode tn) {
		TreeNode p = tn;
		TreeNode rnode = null;
		Stack<TreeNode> stack = new Stack<>();
		while (p != null || !stack.isEmpty()) {
			while (p != null) {
				stack.push(p);
				p = p.left;
			}
			p = stack.pop();
			while (p != null && (p.right == null || p.right == rnode)) {
				visit(p);
				rnode = p;
				if (stack.isEmpty()) {
					return;
				}
				p = stack.pop();
			}
			stack.push(p);
			p = p.right;
		}
	}

}
