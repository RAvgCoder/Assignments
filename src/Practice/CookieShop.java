package Practice;

public class CookieShop{
  private String shopId = "C";
  private int Sale;
  private static int allSale;
  private static int shopCont =0;
  private static double price;

  public CookieShop(){
    Sale = 0;
    shopCont++;
    shopId+=shopCont;
  }

  public void sale(){
    Sale++;
    allSale++;
  }

  public int getSale(){
    return Sale;
  }

  public String getShopId(){
    return shopId;
  }

  public static double price(){
    return price;
  }

  public double finalSales(){
    return Sale*price;
  }

  public static void setPrice(double p){
    price = p;
  }

  public static double getAllSale(){
    return allSale;
  }

  public static double getShiopCount(){
    return shopCont;
  }



  public String toString(){
    return shopId ;
  }

}


