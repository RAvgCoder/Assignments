package Assignment1C1105;
/**
 * Assignment 1, Problem 1 Solution Code
 * @author Eric Poitras
 */
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

        // This section of the code is used to collect user input
        String lastName =  kb.next();
        String firstName =  kb.next();
        String fullNameGreeting = firstName + " " + lastName; //Stores the name used to output user name
        long barcode = kb.nextLong();
        double itemCost = kb.nextDouble();
        double amountPayed = kb.nextDouble();


        //This section is used to output user information to the console
        System.out.println("Customer (Last, First): " + fullNameGreeting );
        System.out.println("Item Number: " + barcode);
        System.out.printf("Item Cost:%.2f", itemCost);
        System.out.println("");
        System.out.printf("Cash Amount:%.2f", amountPayed );



    }
}
