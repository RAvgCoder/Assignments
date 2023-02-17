package Assignment1C1105;
/**
 * Assignment 1, Problem 2 Solution Code
 * @author Eric Poitras
 */

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

        // This section of the code is used to collect user input
        String lastName =  kb.next();

        String firstName =  kb.next();

        String fullName = firstName + " " + lastName; //Stores the name used to output user name

        long barcode = kb.nextLong();

        double itemCost = kb.nextDouble();

        double amountPayed = kb.nextDouble();

        //Numbers used to spit the barcode into product and company 
        final int modDivisor = 10000;
        final int intDevisor = 100000;

        //Splits the barcode into product and company
        long product = barcode%modDivisor;
        long company = barcode/intDevisor;

        //This section is used to output user information to the console
        System.out.println("Customer (Last, First): " + fullName );
        System.out.println("Item Number(Product, Company): " + product + " " +company);
        System.out.printf("Item Cost:%.2f", itemCost);
        System.out.println("");
        System.out.printf("Cash Amount:%.2f", amountPayed );

    }
}