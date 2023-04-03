package CusMeth;

public class LinkedListM<T> {
    private T t;
    private Node tail = null;
    private Node head = null;

    public

    class Node{
        public T data = null;
        public Node next = null;
        public Node prev = null;
    }

    public LinkedListM(T data)
    {
        addFirst(data);
    }

    public void addFirst(T data)
    {
        Node node = new Node();
        node.data  = data;
        if (head==null){
            head = node;
            tail = node;
        }else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public void add(T data)
    {
        if (head==null){
            addFirst(data);
        }else {
            Node endNode = new Node();
            endNode.data = data;
            tail.next = endNode;
            endNode.prev = tail;
            tail = endNode;
        }
    }

    public T removeFirst(){
        if (head==null) return null;
        else {
            T data = head.data;
            head = head.next;
            head.prev = null;
            return data;
        }
    }
    public T removeLast(){
        if(tail==null) return null;
        else {
            T data = tail.data;
            tail = tail.prev;
            tail.next = null;
            return data;
        }
    }

    public T getLast()
    {
        return getExtreme(tail);
    }

    public T getFirst()
    {
        return getExtreme(head);
    }

    public void reverse()
    {
        Node curr = head;
        Node rev = null;
        while (curr!=null){
            Node movTo = curr.next;
            curr.next = rev;
            rev = curr;
            curr = movTo;
            rev.prev = curr;
        }
        tail = head;
        head = rev;
    }

    private T getExtreme(Node node)
    {
        if (node==null) return null;
        return node.data;
    }

    public String toString()
    {
        StringBuilder finals= new StringBuilder();
        Node curr = head;
        if (head==null) return "Empty List";
        while (curr!=null){
            finals.append(curr.data+" -> ");
            curr = curr.next;
        }
        return finals.toString();
    }

}