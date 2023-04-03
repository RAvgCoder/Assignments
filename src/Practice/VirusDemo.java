package Practice;

import java.util.*;
public class VirusDemo{
   public static void main (String [] args){
      Scanner input = new Scanner(System.in);
      LinkedList infectedList = new LinkedList();
      infectedList.addLast(createNode(input));
      String infecter = input.next();
      Node head = infectedList.getFirst();
      System.out.println("Number of Pairs: "+infectedList.size());
      System.out.println(infectedList);
      System.out.println("Carriers: "+ findCarrier(head));
      System.out.println("Infected: "+ findInfected(head));
      System.out.println("Immune: "+ findImmune(head));
      System.out.println(infecter+" spread to: "+ findMyInfected(head,infecter));

   }

   private static String findMyInfected(Node head, String infecter) {
      StringBuilder finals = new StringBuilder();
      HashSet<String> names  = new HashSet<>();
      Node curr = head;
      while (curr != null){
         if (curr.getCarrier().equals(infecter)){
            if (!names.contains(curr.getInfected()))
               finals.append(curr.getInfected()).append(" ");
            names.add(curr.getInfected());
         }
         curr = curr.getNext();
      }
      return finals.toString();
   }

   private static String findImmune(Node head)
   {
      StringBuilder finals = new StringBuilder();
      List<String> infected = new ArrayList<>(Arrays.asList(findInfected(head).split("\\s+")));
      Set<String> carrier = new HashSet<>(List.of(findCarrier(head).split("\\s+")));
      for(String i : infected){
         if (!carrier.contains(i))
            finals.append(i).append(" ");
      }
      return finals.toString();
   }

   private static String findInfected(Node head) {
      StringBuilder finals = new StringBuilder();
      HashSet<String> names = new HashSet<>();
      Node curr = head;
      while (curr != null){
         if (!names.contains(curr.getInfected()))
            finals.append(curr.getInfected()).append(" ");
         names.add(curr.getInfected());
         curr = curr.getNext();
      }
      return finals.toString();
   }

   public static String findCarrier(Node head) {
      StringBuilder finals = new StringBuilder();
      HashSet<String> names = new HashSet<>();
      Node curr = head;
      while (curr != null) {
         if (!names.contains(curr.getCarrier()))
            finals.append(curr.getCarrier()).append(" ");
         names.add(curr.getCarrier());
         curr = curr.getNext();
      }
      return finals.toString();
   }

   public static Node createNode(Scanner input)
   {
      String carrier = input.next();
      if(carrier.equals("*"))
         return null;
      return new Node(carrier,input.next(),createNode(input));
   }
}
      
      
      