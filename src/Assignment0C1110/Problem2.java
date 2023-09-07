package Assignment0C1110;
import java.util.*;
/**
 * @name  CSCI 1110 - Assignment 0 - Problem 1
 * @author: Egbor Osebhulimen
 * @date: 14-01-2023
 * @banner ID: B00928317
 * Description: Read and store menu items, and its then given the name
 * of one of the daily recommended menu items, and display its
 * Cauliflower Variant price.
 */
public class Problem2 {
        public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        int inputCount = Integer.parseInt(kb.nextLine()); //Cont of how many menus needed
        Map<String, String[]> menu = new HashMap<>(); //Stores menu

        String[] arr;

        for (int i = 0; i < inputCount; i++) { //Inputs to menu database
            String food = kb.nextLine();
            // System.out.println("");
            arr = food.split("\\s+");
            menu.put(arr[0], new String[]{arr[2], arr[3], arr[4]});
        }
        String fetch = kb.nextLine();

        String prop[] = menu.get(fetch); //Product whose price is meant to be increased by 1.5X
        // System.out.println(Arrays.toString(prop));
        final double increase = 1.5; //Increase price

        System.out.printf("%s Cauliflower Wings cost $%.2f/$%.2f/$%.2f",fetch,Double.parseDouble(prop[0])*increase,Double.parseDouble(prop[1])*increase,Double.parseDouble(prop[2])*increase);

    }

}