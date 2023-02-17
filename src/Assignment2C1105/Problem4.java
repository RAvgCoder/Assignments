package Assignment2C1105;

import java.util.Scanner;

public class Problem4 {

    public static void main (String[] args){
        //Collects input form the user
        Scanner kb =  new Scanner(System.in);
        double cashRegBalance = kb.nextDouble();
        double amountPayed = kb.nextDouble();
        double itemCost = kb.nextDouble();
        String tellerCode = kb.next();


        //Calculates change returned to the user
        double change = amountPayed - itemCost;
        double absChange = Math.abs(change);   //Converts to the absolute value


        // Check if change is negative or positive for a response
        if ("c".equals(tellerCode)){
            System.out.println("Transaction Cannot Be Completed");
            System.out.println("Transaction was cancelled");
        }
        else if (cashRegBalance < change){
            System.out.println("Transaction Cannot Be Completed");
            System.out.println("Insufficient amount in cash register");
        }
        else if (change < 0){
            System.out.println("Transaction is Incomplete");
            System.out.println("The amount paid is: " + amountPayed + " dollars");
            System.out.println("The item cost is: " + itemCost + " dollars");
            System.out.printf("The amount due is: %.2f dollars", absChange);
        }
        else {
            System.out.println("Transaction is Complete");
            System.out.println("The amount paid is: " + amountPayed + " dollars");
            System.out.println("The item cost is: " + itemCost + " dollars");
            System.out.printf("The amount of change to return is: %.2f dollars", absChange);
        }
    }

}
