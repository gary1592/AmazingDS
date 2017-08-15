package binaryTree;

public class BinaryTreeMain {

	public static void main(String[] args) {

		BinaryTreeService btService = new BinaryTreeService();
		BinaryTree bt1 = new BinaryTree();
		BinaryTree bt2 = new BinaryTree();
		BinaryTree bt3 = new BinaryTree();
		BinaryTree bt4 = new BinaryTree();
		BinaryTree bt5 = new BinaryTree();
		BinaryTree bt6 = new BinaryTree();
		BinaryTree bt7 = new BinaryTree();

		btService.insertNode(bt1, 10, bt1, bt1);
		btService.insertNode(bt2, 20, bt2, bt2);
		btService.insertNode(bt3, 30, bt1, bt2);

		btService.insertNode(bt4, 40, bt4, bt4);
		btService.insertNode(bt5, 50, bt5, bt5);
		btService.insertNode(bt6, 60, bt4, bt5);

		btService.insertNode(bt7, 70, bt3, bt6);

		System.out.println("先序遍历（中－左－右）：");
		btService.preOrder(bt7.root);

		System.out.println();
		System.out.println("非递归先序遍历：");
		btService.iPreOrder(bt7.root);

		System.out.println();
		System.out.println("中序遍历（左－中－右）：");
		btService.midOrder(bt7.root);

		System.out.println();
		System.out.println("非递归中序遍历：");
		btService.iMidOrder(bt7.root);

		System.out.println();
		System.out.println("后序遍历（左－右－中）：");
		btService.postOrder(bt7.root);

		System.out.println();
		System.out.println("非递归后序遍历：");
		btService.iPostOrder(bt7.root);

		System.out.println();
		System.out.println("层次遍历：");
		btService.levelOrder(bt7.root);

	}
}
