package Assignment0C1110;
import java.util.*;
/**
 * @name  CSCI 1110 - Assignment 0 - Problem 1
 * @author: Egbor Osebhulimen
 * @date: 14-01-2023
 * @banner ID: B00928317
 * @description: receive a list of menu items, and print out
 * a description of each to the customer
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        int inputCount = Integer.parseInt(kb.nextLine());

        //Collects cashier input and prints to the console the price of each order
        for (int i=0; i<inputCount; i++) {
            String food = kb.nextLine();
            String[] arr = food.split("\\s+");

            //Decides waht to display for the SHU value
            if (Integer.parseInt(arr[1]) <= 10000)
                food = "mild";
            else if (Integer.parseInt(arr[1]) < 1000000)
                food = "hot!";
            else
                food = "DANGER!!!";

            System.out.printf("%s Wings cost $%.2f/$%.2f/$%.2f and have %s Scovilles (%s)\n",arr[0],Double.parseDouble(arr[2]),Double.parseDouble(arr[3]),Double.parseDouble(arr[4]),arr[1],food);
        }
      }
}