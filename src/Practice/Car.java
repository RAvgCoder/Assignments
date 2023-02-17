package Practice;

import java.util.*;

public class Car {
  // Instance variables
  private CarModel model = null;
  private int plateNum;
  private double usableFuel;
  private List<Integer> allTrips = new ArrayList<>();

  //Constructor
  public Car(CarModel model, int plateNum){
    this.model = model;
    this.plateNum = plateNum;
    this.usableFuel = model.getTankCapacity();
  }

  //Getters
  public int getPlateNum() {return plateNum;}
  public CarModel getModel() {return model;}
  public double getUsableFuel() {return usableFuel;}
  public List<Integer> getAllTrips(){return allTrips;}

  //Setters
  public void setModel(CarModel model) {this.model = model;}
  public void setPlateNum(int plateNum) {this.plateNum = plateNum;}
  public void setUsableFuel(double usableFuel) {this.usableFuel = usableFuel;}

  /**
    * Adds to list of trips
    * @param distance
    */
  public void addTrip(int distance){
    allTrips.add(distance);
  }

  /**
    * Calcualtes and reduces fuel when on a trip
    * @param distance distance go to be traveled
    */
  public void tripFuelLoss(double distance){
    double fuelEconomy = model.getFuelEconomy();
    // Calculates usable fuel available
    double fuelUsed = (distance/100.0)*fuelEconomy;
    setUsableFuel(usableFuel-fuelUsed);
  }
}
