package search;

import org.junit.Test;

import binaryTree.BinaryTree;
import binaryTree.BinaryTreeUtils;
import binaryTree.TreeNode;

public class TreeSearch {

	/**
	 * 插入一个节点 O(log(n))
	 * 
	 * @param bt
	 * @param x
	 */
	public void insert(BinaryTree bt, int x) {
		TreeNode p = bt.root;
		TreeNode pre = new TreeNode();// 记录当前节点的父节点
		// 先找到插入位置 O(log(n))
		while (p != null) {
			pre = p;
			if (x > p.val)
				p = p.right;
			else if (x < p.val)
				p = p.left;
			else {
				System.out.println("Duplicate!");
			}
		}
		// 插入新节点 O(1)
		TreeNode node = new TreeNode(x);
		if (bt.root != null) {
			if (x > pre.val)
				pre.right = node;
			else
				pre.left = node;
		} else {
			bt.root = node;
		}
	}

	/**
	 * 删除节点 O(log(n))
	 * 
	 * @param bt
	 * @param x
	 */
	public void delete(BinaryTree bt, int x) {
		TreeNode p = bt.root;
		TreeNode pre = new TreeNode();
		TreeNode s = new TreeNode();
		TreeNode r = new TreeNode();
		TreeNode c;
		// 寻找待删节点 O(log(n))
		while (p != null && p.val != x) {
			pre = p;
			if (x > p.val)
				p = p.right;
			else
				p = p.left;
		}
		if (p == null) {
			System.out.println("No element is found!");
			return;
		}
		// 若该节点左右子树非空,找到该节点中序遍历下的后继节点，置换该节点
		if (p.left != null && p.right != null) {
			System.out.println("Element is " + p.val);
			s = p.right;
			r = p;
			while (s.left != null) {
				r = s;
				s = s.left;
			}
			p.val = s.val;
			p = s;
			pre = r;
		}
		// 若该节点只有一棵子树
		if (p.left != null)
			c = p.left;
		else
			c = p.right;

		if (p == bt.root)
			bt.root = c;
		else if (p == pre.left)
			pre.left = c;
		else
			pre.right = c;

		// 删除节点须置空
		p = null;
	}

	/**
	 * 搜索节点 O(log(n))
	 * 
	 * @param bt
	 * @param x
	 * @return
	 */
	public TreeNode binaryTreeSearch(BinaryTree bt, int x) {
		TreeNode p = bt.root;
		while (p != null) {
			if (x > p.val)
				p = p.right;
			else if (x < p.val)
				p = p.left;
			else {
				return p;
			}
		}
		return null;
	}

	@Test
	public void test() {
		BinaryTree bt = new BinaryTree();
		insert(bt, 28);
		insert(bt, 21);
		insert(bt, 25);
		insert(bt, 36);
		insert(bt, 33);
		insert(bt, 43);

		delete(bt, 28);
		// System.out.println(binaryTreeSearch(bt, 43));

		BinaryTreeUtils btUtils = new BinaryTreeUtils();
		btUtils.midOrder(bt.root);
	}
}
