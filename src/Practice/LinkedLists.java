package Practice;

public class LinkedLists {
  private Node head;
  private int count=0;

  //constructor
  public LinkedLists()
  {
      head = null;
  }

  public int size() {return count;}
  public Node getFirst() {return head;}

  public void addLast(Node node)
  {
    head = node;
    count = (head==null)? 0 : head.getCount();
  }

  public String toString()
  {
    Node curr = head;
    String finals = "";
    while(curr != null){
      finals += curr.toString();
      curr = curr.getNext();
    }
    return finals;
  }
}

