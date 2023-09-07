package Assignment0C1110;
import java.util.*;
/**
 * @name  CSCI 1110 - Assignment 0 - Problem 1
 * @author: Egbor Osebhulimen
 * @date: 14-01-2023
 * @banner ID: B00928317
 * Description: This is an ordering system as well as the price
 * adjustment system. A list of menu items is received, and sored.
 * Orders will continue to arrive until the "ORDERS COMPLETE" is activated, and
 * then the average SHU of all the orders, as well as the total income are
 * going to output but if the customer tries to order a menu item that is not
 * currently offered, “This item is not on the menu.”, is outputted. Price
 * changes are also given and would be determined by percentages stated
 */
public class Problem3 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Map<String, String[]> menus = new HashMap<>();
        double[] totalOrder = new double[3]; //Stores [Income,SHU total,wing total-pieces]

        int inputCount = Integer.parseInt(kb.nextLine()); //Amount of menus entered

        for (int i = 0; i < inputCount; i++) { //Collect menus items
            createMenu(kb, menus);
        }

        while (kb.hasNext()){ //Collects users orders
            processOrders(kb,menus,totalOrder);
        }
    }

    /**
     * Processes input from cashier on weather to "order food" or "Change the price of a menu".
     * @param kb         Scanner for UserInput
     * @param menus      HashMap containing menus currently available
     * @param totalOrder Array that stores [Income,SHU total,wing total-pieces]
     */
    private static void processOrders(Scanner kb, Map<String, String[]> menus, double[] totalOrder) {
        String OrderInstructions = OrderInstructions = kb.nextLine();
        String[] orderArr = OrderInstructions.split("\\s+");
        //Decides based on the input whether to give final order-price change price of goods or add to order
        if (orderArr[0].equals("ORDERS")){ //For ORDER COMPLETE
            System.out.printf("The income was $%.2f and the order averaged %.2f Scovilles per a wing served\n",
                    totalOrder[0],(totalOrder[1]/(totalOrder[2]==0 ? 1: totalOrder[2]))
            );
            totalOrder[0] = 0;
            totalOrder[1] = 0;
            totalOrder[2] = 0;
        }
        else if (!Character.isDigit(orderArr[0].charAt(0))) //For price Increase
            increasePrice(menus,orderArr);
        else { //Orders food
            if (menus.get(orderArr[2]) != null)
                orderTotalAndScovilles(totalOrder, menus, orderArr);
            else
                System.out.println(
                    //Condition ?{if} :{else}
                    menus.get(orderArr[2]) != null
                            ? orderArr[0] + " order - " + orderArr[1] + " " + orderArr[2] + " Wings"
                            : "This item is not on the menu."
                );
        }
    }

//            System.out.println(Arrays.toString(totalOrder));
    /**
     * Calculates Price SHU total and how amount of wings ordered
     * @param totalOrder    Array containing [Income,SHU total,wing total-pieces]
     * @param menus         HashMap containing menu
     * @param menuArr       Contains order from the customer to be processed
     */
    private static void orderTotalAndScovilles(double[] totalOrder, Map<String, String[]> menus, String[] menuArr) {
        if (!menuArr[0].equals("ORDERS")&&Character.isDigit(menuArr[0].charAt(0))) {
            //Income calculation;
            double tempS = Integer.parseInt(menuArr[1]);
            String[] tempA = menus.get(menuArr[2]);
            switch (String.valueOf(tempS)) {
                case "6.0" -> tempS = Double.parseDouble(tempA[1]);
                case "12.0" -> tempS = Double.parseDouble(tempA[2]);
                case "18.0" -> tempS = Double.parseDouble(tempA[3]);
            }
            //Calculates price for the order
            tempS *= Integer.parseInt(menuArr[0]);
            totalOrder[0] += tempS;
            //Counts to be divided as avg
            tempS = Integer.parseInt(menuArr[0])*Integer.parseInt(menuArr[1]);
            totalOrder[2] += tempS;
            //SHU calculations
            totalOrder[1] += (tempS*Integer.parseInt(tempA[0]));
        }
    }

    /**
     * Increases food price by designated percentage
     * @param menus         HashMap containing menus currently available
     * @param priceRaise    Array containing instructions on what percentage to raise our prices to
     */
    private static void increasePrice(Map<String, String[]> menus, String[] priceRaise) {
        String newPrice[] = menus.get(priceRaise[0]);
        //newPrice[{Price of 6},{Price of 12},{Price of 18},]
        newPrice[1] = String.valueOf(Double.parseDouble(newPrice[1]) * Double.parseDouble(priceRaise[1]) / 100);
        newPrice[2] = String.valueOf(Double.parseDouble(newPrice[2]) * Double.parseDouble(priceRaise[1]) / 100);
        newPrice[3] = String.valueOf(Double.parseDouble(newPrice[3]) * Double.parseDouble(priceRaise[1]) / 100);

        System.out.printf(
            "Price for " + priceRaise[0] + " are now $%.2f/$%.2f/$%.2f\n",
            Double.parseDouble(newPrice[1]), Double.parseDouble(newPrice[2]), Double.parseDouble(newPrice[3])
        );
    }

    /**
     * Creates menu database that would be chosen from by the users.
     * @param kb    Scanner for input
     * @param menus HashMap for storing menu
     */
    private static void createMenu(Scanner kb, Map<String, String[]> menus) {
            String food = food = kb.nextLine();
            String[] menuArr = food.split("\\s+");
            //Puts food into the menu
            menus.put(menuArr[0], new String[]{menuArr[1],menuArr[2], menuArr[3], menuArr[4]});
    }

}