package Assignment1C1105; /**
 * Assignment 1, Problem 3 Solution Code
 * @author Eric Poitras
 */

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

        // This section of the code is used to collect user input
        String firstName = kb.next();
        String lastName = kb.next();
        long barcode = kb.nextLong();
        double cost = kb.nextDouble();
        double cash = kb.nextDouble();

        //Code used to spit the barcode into product and company 
        final int modDevisor = 10000;
        final int intDevisor = 100000;
        long product = barcode%modDevisor;
        long company = barcode/intDevisor;

        //Cost used in calculating the salesTax
        double taxPercentage = 1.15;
        double salesTax = cost*taxPercentage;

        //Calculating the customer change 
        double change = cash-salesTax;

        //This section is used to output suer information to the console
        System.out.println("Customer(Last, First): "+ lastName + " " + firstName);
        System.out.println("Item Number(Product, Company): " + product + " " + company);
        System.out.printf("Item Cost(+ Sales Tax): %.2f ", salesTax );
        System.out.println("");
        System.out.printf("Change: %.2f", change);

    }
}