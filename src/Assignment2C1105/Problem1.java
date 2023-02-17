package Assignment2C1105;

import java.util.Scanner;

public class Problem1 {
     public static void main (String[] args){
        //Collects input form the user
        Scanner kb =  new Scanner(System.in);
        double amountPayed = kb.nextDouble();
        double itemCost = kb.nextDouble();

        //Calculates change returned to the user
        double change = amountPayed - itemCost;
        double absChange = Math.abs(change);   //Converts to the absolute value


        // Check if change is negative or positive for a response
        if (change < 0){
            System.out.println("The amount paid is: " + amountPayed + " dollars");
            System.out.println("The item cost is: " + itemCost + " dollars");
            System.out.printf("The amount due is: %.2f dollars", absChange);
        }
        else {
            System.out.println("The amount paid is: " + amountPayed + " dollars");
            System.out.println("The item cost is: " + itemCost + " dollars");
            System.out.printf("The amount of change to return is: %.2f dollars", absChange);
        }

    }
}
