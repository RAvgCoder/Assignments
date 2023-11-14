package Assignment4C2110;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reads in a line of input, storing them into a list of binary trees
        List<BinaryTree<String>> arrayNodes = Arrays.stream(scanner.nextLine().split(" "))
                .map(word -> {
                    // Converting each word to a binary tree node
                    BinaryTree<String> b_tree = new BinaryTree<>();
                    b_tree.makeRoot(word);
                    return b_tree;
                })
                .toList();

        scanner.close();

        // Make binary Tree connecting each node to one another
        int parentPtr = 0;
        int childPtr = 1;
        while (arrayNodes.size() > childPtr) {
            BinaryTree<String> parent = arrayNodes.get(parentPtr);
            BinaryTree<String> child = arrayNodes.get(childPtr);

            if ((childPtr % 2 == 1)) parent.setLeft(child);
            else parent.setRight(child);

            child.setParent(parent);
            if (childPtr++ % 2 == 0) parentPtr++;
        }
        // Establishes the head of the tree
        BinaryTree<String> root = (arrayNodes.isEmpty()) ? null : arrayNodes.get(0);

        printTest(root);
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
