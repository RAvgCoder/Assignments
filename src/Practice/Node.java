package Practice;

//class Node
public class Node{
  //attributes
  private String carrier;
  private String infected;
  private Node next;
  private static int count =0;

  //constructor
  public Node(String carrier, String infected, Node next){
      this.carrier = carrier;
      this.infected = infected;
      this.next = next;
      count++;
  }
   //get and set methods
   public String getCarrier(){ return carrier;}
   public int getCount(){return count;}
   public String getInfected(){ return infected;}
   public Node getNext(){ return next;}

   public void setCarrier(String c){ carrier = c;}
   public void setInfected(String i){ infected = i;}
   public void setNext(Node n){ next = n; }

   //toString
   public String toString(){
      return "[" + carrier + ", " + infected  + "]--> ";
  }
}

