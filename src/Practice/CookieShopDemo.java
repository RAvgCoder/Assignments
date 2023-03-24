package Practice;

import java.util.*;
import java.util.stream.Collectors;

public class CookieShopDemo{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    double price = in.nextDouble();
    CookieShop[] cookieShop = new CookieShop[in.nextInt()];

    for(int i=0; i<cookieShop.length; i++){
      cookieShop[i] = new CookieShop();
    }
    CookieShop.setPrice(price);

    int itr = in.nextInt();


    for (int i=0; i< itr; i++){
      cookieShop[in.nextInt()].sale();
    }

    Arrays.stream(cookieShop).forEach(x -> System.out.printf(
      "Shop: %s Sales: %d Revenue: $%.2f\n",x.toString(),x.getSale(),x.finalSales()
    ));

    
    System.out.printf(
      "Total cookies sold in all shops: %s\n"+
      "Total revenue from all shops: $%.2f\n"+
      "There are %d Cookie shops.\n"+
      "The top shop is : %s",
      Arrays.stream(cookieShop).map(CookieShop::getSale).reduce(0,Integer::sum),
      Arrays.stream(cookieShop).map(CookieShop::finalSales).reduce((double)0,Double::sum),
      cookieShop.length,
      Arrays.stream(cookieShop).sorted(Comparator.comparing(CookieShop::getSale)).toArray()[cookieShop.length]
    );



  }
}