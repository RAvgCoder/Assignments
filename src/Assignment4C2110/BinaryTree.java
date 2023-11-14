package Assignment4C2110;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {
    private T data;
    private BinaryTree<T> parent;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree() {
        parent = left = right = null;
        data = null;
    }

    // this method returns the number of nodes/trees in a BinaryTree
    public static <T> int nodes(BinaryTree<T> t) {
        if (t == null) return 0;
        else return nodes(t.left) + 1 + nodes(t.right);
    }

    // this method returns the height of a BinaryTree
    // (the number of edges separating a root node/tree from its most distant,
    // descendant leaf)
    public static <T> int height(BinaryTree<T> t) {
        if (t == null) return -1;
        else return Math.max(height(t.left), height(t.right)) + 1;
    }

    // this method tests whether a tree is height balanced
    public static <T> boolean heightBalanced(BinaryTree<T> t) {
        if (t == null) return true;
        return (Math.abs(height(t.left) - height(t.right)) <=1)
                && heightBalanced(t.right)
                && heightBalanced(t.left);
    }

    public static <T> void preorder(BinaryTree<T> t) {
        if (t != null) {
            System.out.print(t.getData() + "\t");
            preorder(t.getLeft());
            preorder(t.getRight());
        }
    }

    public static <T> void inorder(BinaryTree<T> t) {
        if (t != null) {
            inorder(t.getLeft());
            System.out.print(t.getData() + "\t");
            inorder(t.getRight());
        }
    }

    public static <T> void postorder(BinaryTree<T> t) {
        if (t != null) {
            postorder(t.getLeft());
            postorder(t.getRight());
            System.out.print(t.getData() + "\t");
        }
    }

    // this method uses a modified BFS to print the data associated with all
    // nodes/trees in level order
    public static <T> void levelOrder(BinaryTree<T> t) {
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(t);
        while (!queue.isEmpty()) {
            BinaryTree<T> curr = queue.remove();
            if (curr != null) {
                System.out.print(curr.getData() + "\t");
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
    }

    public void makeRoot(T data) {
        if (!isEmpty()) {
            System.out.println("Can't make root. Already exists");
        } else
            this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTree<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTree<T> tree) {
        parent = tree;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<T> tree) {
        left = tree;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setRight(BinaryTree<T> tree) {
        right = tree;
    }

    public void attachLeft(BinaryTree<T> tree) {
        if (tree == null) {
        } else if (left != null || tree.getParent() != null) {
            System.out.println("Can't attach");
        } else {
            tree.setParent(this);
            this.setLeft(tree);
        }
    }

    public void attachRight(BinaryTree<T> tree) {
        if (tree == null) {
        } else if (right != null || tree.getParent() != null) {
            System.out.println("Can't attach");
        } else {
            tree.setParent(this);
            this.setRight(tree);
        }
    }

    public BinaryTree<T> detachLeft() {
        if (this.isEmpty())
            return null;
        BinaryTree<T> retLeft = left;
        left = null;
        if (retLeft != null)
            retLeft.setParent(null);
        return retLeft;
    }

    public BinaryTree<T> detachRight() {
        if (this.isEmpty())
            return null;
        BinaryTree<T> retRight = right;
        right = null;
        if (retRight != null)
            retRight.setParent(null);
        return retRight;
    }

    public boolean isEmpty() {
        return data == null;
    }

    public void clear() {
        left = right = parent = null;
        data = null;
    }

    public BinaryTree<T> root() {
        if (parent == null)
            return this;
        else {
            BinaryTree<T> next = parent;
            while (next.getParent() != null)
                next = next.getParent();
            return next;
        }
    }
}
