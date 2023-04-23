//package Practice;
//
//import Practice.Student;
//
//import java.util.*;
//
//public class Teesst {
//  public static void main(String [] args) {
//    Scanner in = new Scanner(System.in);
//    Queue<Student> arriving = new LinkedList<>();
//    Queue<Student> waiting = new LinkedList<>();
//
//    int time = 0;
//    int consult  = -1;
//    Student currenntStud = null;
//
//    int numOfStud = in.nextInt();in.nextLine();
//
//    for(int i=0; i<numOfStud; i++){
//      arriving.add(new Student(in.nextLine()));
//    }
//
//    while(!arriving.isEmpty() || !waiting.isEmpty() || consult >= time)
//    {
//      Student arrivingStud = arriving.peek();
//      if(arrivingStud!=null && arrivingStud.getArrivalTime() == time){
//        System.out.println(arrivingStud.getArrivalTime()+": "+arrivingStud+" arrives.");
//        waiting.add(arrivingStud);
//        arriving.remove();
//      }
//
//
//
//
//      if(consult == time){
//        System.out.println(time+": "+currenntStud+" leaves.");
//      }
//
//      if(consult <= time && !waiting.isEmpty()){
//        currenntStud = waiting.remove();
//        consult = time+currenntStud.getDuration();
//        System.out.println(time+": "+currenntStud+" sees professor.");
//      }
//
//      int finalTime = time;
//      waiting.removeIf(student -> student.getOutOfTime()== finalTime);
//
////      checkOutOfTime(waiting,time);
//
//
//      time+=1;
//    }
//
//    test:{
//      import java.util.Scanner;
//import java.util.*;
//
//public class Problem2 {
//    public static void main(String [] args) {
//        Scanner in = new Scanner(System.in);
//        Queue<Shopper> checkoutLine= new LinkedList<>();
//        Queue<Shopper> shoppers= new LinkedList<>();
//        Shopper shopperAtCheckout = null;
//        int timeCheckoutStarted = -1;
//        int time = 0;
//
//        int numShoper = in.nextInt();in.nextLine();
//
//        for(int i=0; i< numShoper; i++)
//        {
//            shoppers.add(new Shopper(in.nextLine()));
//        }
//
//
//        while(!shoppers.isEmpty() || !checkoutLine.isEmpty() || shopperAtCheckout!=null)
//        {
//            //1. SHOPPER ARRIVES
//            Shopper  nextShopper = shoppers.peek();
//
//            if (nextShopper != null && nextShopper.getArrivalTime() == time)
//            {
//                System.out.printf("%d: Cart #%d joins line.\n",time,nextShopper.getCartNUm());
//                checkoutLine.add(nextShopper); //////.
//                shoppers.remove();
//            }
//
//            //2. SHOPPER MOVES TO CHECKOUT
//            if (shopperAtCheckout == null && !checkoutLine.isEmpty())
//            {
//                shopperAtCheckout = checkoutLine.peek();
//                timeCheckoutStarted = time;
//                System.out.printf("%d: Cart #%d begins checkout.\n",time,shopperAtCheckout.getCartNUm());
//                checkoutLine.remove();
//            }
//
//             //3. SHOPPER PAYS
//            if (shopperAtCheckout != null && timeCheckoutStarted + shopperAtCheckout.getnumItem() <= time )
//            {
//                System.out.printf("%d: Cart #%d pays.\n",time,shopperAtCheckout.getCartNUm());
//                shopperAtCheckout = null;
//                timeCheckoutStarted = -1;
//            }
//
//            time++;
//        }
//
//
//
//    }
//}
//    }
//  }
//
//  public static void checkOutOfTime(Queue<Student> waiting, int time)
//  {
//    var itr = waiting.iterator();
//    while(itr.hasNext()){
//      Student s = itr.next();
//      if(s.getOutOfTime()==time){
//        System.out.println(time+": "+s+" leaves due to lack of time.");
//        itr.remove();
//      }
//    }
//  }
//}
