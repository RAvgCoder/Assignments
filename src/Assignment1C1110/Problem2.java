package Assignment1C1110;
import java.util.*;
/**
 * @name CSCI 1110 - Assignment 1 - Problem 1
 * @author: Egbor Osebhulimen
 * @date: 27-01-2023
 * @bannerID: B00928317
 * @description:  a salary calculation application where we are to calculate
 * an employee’s salary in total where Normal working hour = 𝑡
 * Wages per hour of normal work 𝑑
 * Extra wages per hour of extra work 𝐷
 * Actual working hour 𝑇
 */
public class Problem2 {
  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);

    System.out.println("Please input the number of employees: ");
    int input = kb.nextInt();

    //Array containing all salary information
    String [][] salary = new String[input][4];
    int totalAmountToPay = 0;

    System.out.println("Please input the data of each employee:");
    for (int i = 0; i < salary.length; i++){
      for (int j = 0; j < salary[i].length; j++) {
          salary[i][j] = kb.next();
      }
      //Calculates salary for each staff
      totalAmountToPay+=calculate(salary[i]);
    }

    StringBuilder totals = new StringBuilder(totalAmountToPay + " Dollars");
    StringBuilder stars = new StringBuilder();

    //Creates number of stars needed
    for (int i = 0; i < digits(totals); i++)
        stars.append("*");

    System.out.println(stars);
    System.out.println(totals);
    System.out.println(stars);
  }

  /**
    *Calculates the length of givien String StringBuilder
    * @param str Given StringBuilder
    * @return Integer length of StringBuilder
    */
  public static int digits(StringBuilder str){
    return str.length();
  }

  /**
    * Calculates salary for each staff
    * @param salary Array containing staff work hour contract and logged our
    * @return An integer of final salary to be paid
    */
  public static int calculate(String[] salary){
    int total =0;
    int tempY = 0;

    //Map containing current employee data
    HashMap<String,Integer> record = new HashMap<>();

    //inputs into map
    for (String x: salary){

      String xArr[] = x.split("=");

      if(xArr.length > 1)
        record.put(xArr[0],Integer.parseInt(xArr[1]));
    }

    int overTimePay =0;
    if (record.containsKey("T") && record.containsKey("t")){

      //Checks if worked overtime +ve[Yes] -ve[No]
      overTimePay = record.get("T") - record.get("t");

      //Gets normal work time
      if (overTimePay > 0)
        tempY = record.get("T") - overTimePay;
      else
        tempY = record.get("T");
    }

    //Calculates normal wages
    if (record.containsKey("d")){
      total += record.get("d") * tempY;
    }

    //Bonus pay if applicable
    if(record.containsKey("D")){
      if (overTimePay > 0)
        total += overTimePay * record.get("D");
    }

    return total;
  }
}
