package Practice;

public class Main {
    public static void main(String[] args) {
        // 62 16 15 N 8 4 7 N 8 4
        // Constructing a binary tree
        Node root = new Node(62);
        root.left = new Node(16);
        root.right = new Node(15);
        root.left.right = new Node(8);
        root.left.right.left = new Node(8);
        root.left.right.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(7);
        // Check if the binary tree is a sum tree
        if (isSumTree(root)) {
            System.out.println("The given tree is a sum tree.");
        } else {
            System.out.println("The given tree is not a sum tree.");
        }
    }


    public static boolean isSumTree(Node node) {
        return _isSumTree(node, new Integer[1]);
    }

    public static boolean _isSumTree(Node node, Integer[] sum) {
        if (node.left != null && node.right != null) {
            Integer[] l = new Integer[1];
            Integer[] r = new Integer[1];
            if (_isSumTree(node.left, l) && _isSumTree(node.right, r)
                    && (node.data == node.right.data + node.left.data)) {
                if (l[0] == null || r[0] == null) return false;
                sum[0] = l[0] + r[0];
                return true;
            }
            return false;
        }

        sum[0] = node.data;

        return true;
    }


}

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}
