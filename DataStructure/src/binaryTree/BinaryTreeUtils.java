package binaryTree;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeUtils {

	/**
	 * 插入节点
	 * 
	 * @param bt
	 * @param val
	 * @param left
	 * @param right
	 * @return
	 */
	public void insertNode(TreeNode root, TreeNode left, TreeNode right) {
		root.left = left;
		root.right = right;
	}

	/**
	 * 删除节点
	 * 
	 * @param bt
	 * @param val
	 * @param left
	 * @param right
	 */
	public void deleteNode(BinaryTree bt, int val, BinaryTree left, BinaryTree right) {
		if (bt.root != null) {
			val = bt.root.val;
			left.root = bt.root.left;
			right.root = bt.root.right;
			bt.root = null;
		}
	}

	/**
	 * 输出节点
	 * 
	 * @param tn
	 */
	public void visit(TreeNode tn) {
		System.out.print(tn.val + " ");
		// System.out.println(tn.lc + "," + tn.rc);
	}

	/**
	 * 先序遍历
	 * 
	 * @param tn
	 */
	public void preOrder(TreeNode tn) {
		if (tn != null) {
			visit(tn);
			preOrder(tn.left);
			preOrder(tn.right);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param tn
	 */
	public void midOrder(TreeNode tn) {
		if (tn != null) {
			midOrder(tn.left);
			visit(tn);
			midOrder(tn.right);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param tn
	 */
	public void postOrder(TreeNode tn) {
		if (tn != null) {
			postOrder(tn.left);
			postOrder(tn.right);
			visit(tn);
		}
	}

	/**
	 * 层次遍历
	 * 
	 * @param tn
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
	 * 计算节点个数（基于后序遍历）
	 * 
	 * @param tn
	 * @return
	 */
	public int size(TreeNode tn) {
		if (tn == null)
			return 0;
		else
			return 1 + size(tn.left) + size(tn.right);
	}

	/**
	 * 二叉树高度（基于后序遍历）
	 * 
	 * @param tn
	 * @return
	 */
	public int height(TreeNode tn) {
		if (tn == null)
			return 0;
		else
			return 1 + Math.max(height(tn.left), height(tn.right));
	}

	/**
	 * 非递归先序遍历
	 * 
	 * @param tn
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

	/**
	 * 非递归中序遍历
	 * 
	 * @param tn
	 */
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

	/**
	 * 非递归后续遍历
	 * 
	 * @param tn
	 */
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

	/**
	 * 获取所有的路径
	 * 
	 * @param root
	 * @param list
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root, List<String> list) {
		if (root == null) {
			return list;
		}
		StringBuilder builder = new StringBuilder();
		getPath(root, builder, list);
		return list;
	}

	/**
	 * 基于先序遍历，递归获取每一条路径
	 * 
	 * @param root
	 * @param builder
	 * @param list
	 */
	public void getPath(TreeNode root, StringBuilder builder, List<String> list) {
		if (root.left == null && root.right == null) {
			builder.append(root.val);
			list.add(builder.toString());
		}

		if (root.left != null) {
			StringBuilder leftbuilder = new StringBuilder(builder);
			leftbuilder.append(root.val + "->");
			getPath(root.left, leftbuilder, list);
		}

		if (root.right != null) {
			StringBuilder rightbuilder = new StringBuilder(builder);
			rightbuilder.append(root.val + "->");
			getPath(root.right, rightbuilder, list);
		}
	}

	/**
	 * 计算最大点
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode maxNode(TreeNode root) {
		if (root == null)
			return null;

		return getMaxNode(root);
	}

	/**
	 * 基于后序遍历，递归比较
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode getMaxNode(TreeNode root) {
		if (root == null)
			return new TreeNode(Integer.MIN_VALUE);

		TreeNode left = getMaxNode(root.left);
		TreeNode right = getMaxNode(root.right);

		if (root.val >= right.val && root.val >= left.val)
			return root;
		if (left.val >= right.val && left.val >= root.val)
			return left;
		return right;
	}

	/**
	 * 克隆二叉树
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode cloneTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		return copyTree(root);
	}

	/**
	 * 基于先序遍历，递归拷贝
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode copyTree(TreeNode root) {
		TreeNode node = new TreeNode(root.val);
		if (root.left != null) {
			node.left = copyTree(root.left);
		}
		if (root.right != null) {
			node.right = copyTree(root.right);
		}
		return node;
	}

}
