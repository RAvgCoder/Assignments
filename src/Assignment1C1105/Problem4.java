package Assignment1C1105; /**
 * Assignment 1, Problem 4 Solution Code
 * @author Eric Poitras
 */

import java.util.Scanner;
import static java.lang.Math.abs;

public class Problem4 {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

        //Takes Input from the user
        String firstName = kb.next();
        String lastName = kb.next();
        long barcode = kb.nextLong();
        double cost = kb.nextDouble();
        double cash = kb.nextDouble();

        //For splitting barcode into product and company
        final int modDevisor = 10000;
        final int intDevisor = 100000;

        long product = barcode%modDevisor;
        long company = barcode/intDevisor;

        //For calculating Sales Tax
        double  taxRate = (100/100.0) + (15/100.0);
        double salesTax = taxRate*cost;

        //Calculating the customer change also returning its absolute value 
        double change =  cash - salesTax;
        double absChange = abs(change);

        //Checks if the balance is enough to continue with the transaction
        boolean balance = change < 0;

        //This section is used to output suer information to the console
        System.out.println("Customer(Last, First): " + lastName + firstName);
        System.out.println("Item Number(Product, Company): " + product + " "+ company);
        System.out.printf("Item Cost(+ Sales Tax): %.2f", salesTax);
        System.out.println("");
        System.out.printf("Change: %.2f", absChange);
        System.out.println("");
        System.out.println("Negative Balance(True/False): " + balance);

    }
}