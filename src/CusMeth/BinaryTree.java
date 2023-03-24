package CusMeth;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BinaryTree {
    private Node head;

    public class Node
    {
        public Integer data;
        public Node left = null;
        public Node right = null;

    }

    public BinaryTree()
    {
        head = null;
    }

    public void add(Integer data)
    {
        Node elem = new Node();
        elem.data = data;

        if (head == null){
            head = elem;
        }else {
            Node pointer = head;
            while (pointer != null){
                if (pointer.left==null) {
                    pointer.left= elem;
                    return;
                }else if(pointer.right==null){
                    pointer.right = elem;
                    return;
                }

            }
        }
    }
}
