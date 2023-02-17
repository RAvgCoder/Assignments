package Assignment3C1105;/*
 * CSCI 1105 - Assignment 3 - Problem 4
 * Author: Egbor Osebhulimen
 * Date: 19-10-22
 * Banner ID: B00928317
 * Description: The code takes an input from the user to calculate the average of numbers.
 */

import java.util.Scanner;
public class Problem4{
    public static void main(String[] args) {

        // Variable initialization and inputs
        Scanner input = new Scanner(System.in);
        int inputCount = input.nextInt();
        double inputSum = 0;

        // Loop to sum inputed numbers together 
        for (int i = 1; i <= inputCount; i++) {
            double nextValue = input.nextInt();
            inputSum += nextValue;
        }

        double averageValue = inputSum / inputCount;
        System.out.printf("Average value: %.2f", averageValue);
    }
}
// WRITE YOUR CODE HERE