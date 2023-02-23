package Assignment2C1110;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @name:         CSCI 1110 - Assignment 2
 * @author:       Egbor Osebhulimen
 * @date:         12-02-2023
 * @bannerID:     B00928317
 * @description:  This is a car rental company that can check if current fuel level
 * for a given car is sufficient for the customer to drive to the destination
 * it can also keep track of the cars in the case they needed to be refilled.
 * It can also get the number of times a car has traveled a certain distance
 * successfully for all trips traveled by that car.
 */
public class Main {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    List<CarModel> carModels = new ArrayList<>();
    List<Car> cars = new ArrayList<>();
    while (true){
      String[] input = kb.nextLine().trim().split("\\s+");
      String instructions = input[0];
      if (instructions.equals("FINISH")) //Ends the program
        break;
      else if (instructions.equals("MODEL")) //Creates different car models
        createCarModel(carModels,input);
      else if(instructions.equals("CAR")) //Creates cars from car models
        createCar(cars,carModels,input);
      else if (instructions.equals("TRIP")) //Checks if car is able to complete its trip
        tripCheck(cars, input[1], input[2]);
      else if (instructions.equals("REFILL")) //Refills the car
        refillCar(cars,input[1]);
      else if (instructions.equals("LONGTRIPS")) //Finds number of trips longer than given
        findNumOfTrips(cars,input[1],input[2]);
    }
    kb.close();
  }

  /**
    * Finds number of trips for given trips
    * @param cars List of cars
    * @param plateNum PlateNumber for the car
    * @param tripLength Trip length being searched for
    */
  private static void findNumOfTrips(List<Car> cars, String plateNum, String tripLength) {
    cars.stream() // Filters and finds cars that matches plateNumber
          .filter(car -> car.getPlateNum()== Integer.parseInt(plateNum))
          .forEach(car -> {
            //Counts how many of the trips are longer than given length
            int tripCount = (int) car.getAllTrips().stream()
                  .filter(trip -> trip>=Integer.parseInt(tripLength))
                  .count();
            System.out.printf("#%s made %d trips longer than %s%n",plateNum,tripCount,tripLength);
          });
  }

  /**
    * Creates a blueprint for a carModel
    * @param carModels List of all carModels
    * @param input Data for creating the car model
    */
  private static void createCarModel(List<CarModel> carModels, String[] input) {
    // Creates a car model and stores it in list of all models
    carModels.add(new CarModel(input[1], Double.parseDouble(input[2]), Double.parseDouble(input[3])));
  }

  /**
    * Checks if trip is sufficient with the distance given
    * @param cars List of all cars
    * @param plateNum Plate Number for car chosen
    * @param distance Distance you want traveled
    */
  private static void tripCheck(List<Car> cars, String plateNum, String distance) {
    boolean found = false;
    for (Car car: cars)
      if(car.getPlateNum()==Integer.parseInt(plateNum)){
        found = true;

        // Reduces fuel for a car going on a trip
        car.tripFuelLoss(Double.parseDouble(distance));

        //Adds the distance traveled to the cars record if its able to complete it
        if (car.getUsableFuel()>0)
          car.setAllTrips(Integer.parseInt(distance));

        // Prints fuel statistics depending on if a car has fuel or not
        System.out.println(
          car.getUsableFuel()<0
            ? "Not enough fuel for #"+car.getPlateNum()
            : "Trip completed successfully for #"+car.getPlateNum()
        );
      }
    // Handles plateNum that doesn't exist
    if (!found)
      System.out.println("Not enough fuel for #"+plateNum);
  }

  /**
    * Refills the tank of a given car
    * @param cars List of cars available
    * @param plateNum Plate Number of car wanting to be refilled
    */
  private static void refillCar(List<Car> cars, String plateNum) {
    cars.stream() // Searches all cars for given car and fill back fuel
          .filter(car -> car.getPlateNum()==Integer.parseInt(plateNum))
          .forEach(car -> car.setUsableFuel(car.getModel().tankCapacity()));
  }

  /**
    * Creates a car to be driven
    * @param cars List of all cars
    * @param carModels List of all car models
    * @param input Input for creating a Car
    */
  public static void createCar(List<Car> cars, List<CarModel> carModels, String[] input){
    carModels.stream()
          //Searches through all car models, finds and creates a car with specs given
          .filter(carModelCurrent -> carModelCurrent.modelName().equals(input[1]))
          .forEach(carModel -> cars.add(new Car(carModel,Integer.parseInt(input[2]))));
  }
}

