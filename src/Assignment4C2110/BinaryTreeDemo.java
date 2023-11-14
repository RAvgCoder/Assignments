package Assignment4C2110;

public class BinaryTreeDemo {
	public static void main(String[] args) {

		// Test1
		System.out.println("-------------------[TEST 1]-------------------");
		BinaryTree<String> A = new BinaryTree<>();
		BinaryTree<String> B = new BinaryTree<>();
		BinaryTree<String> C = new BinaryTree<>();
		BinaryTree<String> D = new BinaryTree<>();
		BinaryTree<String> E = new BinaryTree<>();
		BinaryTree<String> F = new BinaryTree<>();
		makeRoot(A, B, C, D, E, F);

		A.attachLeft(B);
		A.attachRight(C);
		B.attachLeft(D);
		B.attachRight(E);
		D.attachLeft(F);

		printTest(A);

		// Test2
		System.out.println("-------------------[TEST 2]-------------------");
		A = new BinaryTree<>();
		B = new BinaryTree<>();
		C = new BinaryTree<>();
		D = new BinaryTree<>();
		E = new BinaryTree<>();
		F = new BinaryTree<>();

		makeRoot(A, B, C, D, E, F);

		A.attachLeft(B);
		A.attachRight(C);
		B.attachLeft(E);
		B.attachRight(D);
		C.attachLeft(F);

		printTest(A);

		// Test3
		System.out.println("-------------------[TEST 3]-------------------");
		A = new BinaryTree<>();
		B = new BinaryTree<>();
		C = new BinaryTree<>();
		D = new BinaryTree<>();
		E = new BinaryTree<>();
		F = new BinaryTree<>();

		makeRoot(A, B, C, D, E, F);

		A.attachLeft(B);
		B.attachRight(C);
		C.attachLeft(D);
		D.attachRight(E);
		E.attachLeft(F);

		printTest(A);

		// Test4
		System.out.println("-------------------[TEST 4]-------------------");
		A = new BinaryTree<>();
		B = new BinaryTree<>();
		C = new BinaryTree<>();
		D = new BinaryTree<>();
		E = new BinaryTree<>();
		F = new BinaryTree<>();

		makeRoot(A, B, C, D, E, F);

		A.attachLeft(B);
		A.attachRight(C);
		C.attachLeft(D);
		B.attachRight(E);
		E.attachLeft(F);

		printTest(A);
	}

	private static void makeRoot(BinaryTree<String> A,
								 BinaryTree<String> B,
								 BinaryTree<String> C,
								 BinaryTree<String> D,
								 BinaryTree<String> E,
								 BinaryTree<String> F) {
		A.makeRoot("A");
		B.makeRoot("B");
		C.makeRoot("C");
		D.makeRoot("D");
		E.makeRoot("E");
		F.makeRoot("F");
	}

	private static void printTest(BinaryTree<String> root) {
		// test statements
		System.out.printf("Height of the tree is: %d\n", BinaryTree.height(root));

		System.out.printf("Number of nodes in the tree is: %d\n", BinaryTree.nodes(root));

		System.out.println();

		System.out.print("Inorder:\t");
		BinaryTree.inorder(root);
		System.out.println();

		System.out.print("Preorder:\t");
		BinaryTree.preorder(root);
		System.out.println();

		System.out.print("Postorder:\t");
		BinaryTree.postorder(root);
		System.out.println();

		System.out.print("Level order:\t");
		BinaryTree.levelOrder(root);
		System.out.println();
		System.out.println();

		System.out.printf("And is it height balanced... %s\n", BinaryTree.heightBalanced(root) ? "Yes!" : "No.");

		System.out.println();
	}


}
