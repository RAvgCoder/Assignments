package Assignment1C1110;
import java.util.*;
/**
 * @name CSCI 1110 - Assignment 1 - Problem 1
 * @author: Egbor Osebhulimen
 * @date: 27-01-2023
 * @banner  ID: B00928317
 * @description: A total payment system for salaries of
 * employees in the company
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        //Number of employees to calculate
        System.out.println("Please input the number of employees:");
        int input = kb.nextInt();
        int totalAmountToPay = 0;

        int [][] salary = new int[input][4];

        System.out.println("Please input the data of each employee:");
        for (int i=0; i<salary.length; i++){
            for (int j = 0; j < salary[i].length; j++) {
                    salary[i][j] = kb.nextInt();
            }
            //Calculates salary for each staff
            totalAmountToPay+=calculate(salary[i]);
        }
        System.out.println(totalAmountToPay);
    }

    /**
     * Calculates salary for each staff
     * @param salary Array containing staff work hour contract and logged our
     * @return An integer of final salary to be paid
     */
    public static int calculate(int salary[]){
        int totalPay = 0;
        int tempY = 0;

        //Checks if worked overtime +ve[Yes] -ve[No]
        int overTimePay = salary[3]-salary[0];

        //Gets noraml work time
        if (overTimePay>0)
            tempY = salary[3]-overTimePay;
        else
            tempY = salary[3];

        //Calculates normal wages
        totalPay += salary[1]*tempY;

        //Bonus pay if applicable
        if (overTimePay>0)
            totalPay += overTimePay*salary[2];

        return totalPay;
    }

}
