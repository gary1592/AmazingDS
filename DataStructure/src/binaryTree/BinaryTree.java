package binaryTree;

public class BinaryTree {

	public TreeNode root;

	public BinaryTree() {

	}

	public BinaryTree(TreeNode root) {
		this.root = root;
		this.root.left = root.left;
		this.root.right = root.right;
	}

	public BinaryTree(int val) {
		this.root = new TreeNode(val);
		this.root.left = null;
		this.root.right = null;
	}

}
