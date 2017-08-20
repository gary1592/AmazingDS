package binaryTree;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public int val;

	public int lc = -1;// left code = 0
	public int rc = -1;// right code = 1
	public TreeNode parent;

	public TreeNode() {

	}

	public TreeNode(Integer val) {
		this.val = val;
	}
}
