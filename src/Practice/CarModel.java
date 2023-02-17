package Practice;

public class CarModel {
    // Instance variables
    private String modelName;
    private double fuelEconomy;
    private double tankCapacity;

    //Constructor
    public CarModel(String modelName, double fuelEconomy, double tankCapacity){
      this.modelName = modelName;
      this.fuelEconomy = fuelEconomy;
      this.tankCapacity = tankCapacity;
    }

    //  Getters
    public String getModelName() {return modelName;}
    public double getFuelEconomy() {return fuelEconomy;}
    public double getTankCapacity() {return tankCapacity;}

    // Setters
    public void setFuelEconomy(double fuelEconomy) {this.fuelEconomy = fuelEconomy;}
    public void setTankCapacity(double tankCapacity) {this.tankCapacity = tankCapacity;}
    public void setModelName(String modelName) {this.modelName = modelName;}
}
