package Practice;

import MyMethods.RalphsList;

import java.util.*;

public class  Main {
  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    List<CarModel> carModels = new ArrayList<>();
    List<Car> cars = new ArrayList<>();
    while (true){
      String[] input = kb.nextLine().split("\\s+");
      // Guards for extra spaces
      if (input[0].equals("")) {
        List<String> list = new ArrayList<>(Arrays.asList(input));
        list.remove(0);
        input = list.toArray(String[]::new);
      }
      String instructions = input[0];
      if (instructions.equals("FINISH")) //Ends the program
        break;
      else if (instructions.equals("MODEL")) //Creates different car models
        createCarModel(carModels,input);
      else if(instructions.equals("CAR")) //Creates cars from car models
        createCar(cars,carModels,input);
      else if (instructions.equals("TRIP")) //Checks if car is able to complete its trip
        tripCheck(cars, input[1], input[2]);
      else if (instructions.equals("REFILL")) //Refills car
        refillCar(cars,input[1]);
      else if (instructions.equals("LONGTRIPS")) //Finds number of trips longer than given
        findNumOfTrips(cars,input[1],input[2]);
    }
  }

  /**
    * Finds number of trips for given trips
    * @param cars List of cars
    * @param plateNum PlateNumber for checks
    * @param tripLength Trip length being searched for
    */
  private static void findNumOfTrips(List<Car> cars, String plateNum, String tripLength) {
    List<Integer> trips; //List of trips for a particular vehicle
    int count =0; // Counts the list of cars seen that match tripLength
    for(Car car: cars)
      if (car.getPlateNum()==Integer.parseInt(plateNum)) { //Checks each car for plateNum
        trips = car.getAllTrips();
        for (Integer trip : trips) // Searches trip to find matching requirements
          if (trip >= Integer.parseInt(tripLength))
            count++;
      }
    System.out.printf("#%s made %d trips longer than %s%n",plateNum,count,tripLength);
  }

  /**
    * Creates a blueprint for a carModel
    * @param carModels List of all carModels
    * @param input Data for creating the car model
    */
  private static void createCarModel(List<CarModel> carModels, String[] input) {
    // Creates a car model with its properties
    carModels.add(new CarModel(input[1], Double.parseDouble(input[2]), Double.parseDouble(input[3])));
  }

  /**
    * Checks if trip is sufficient with the distance chosen
    * @param cars List of all cars
    * @param plateNum Plate Number for car chosen
    * @param distance Distance you want traveled
    */
  private static void tripCheck(List<Car> cars, String plateNum, String distance) {
      boolean found = false;
      for(Car car: cars){
        if (car.getPlateNum() == Integer.parseInt(plateNum)) {
          found = true;
          car.tripFuelLoss(Double.parseDouble(distance));
          //Adds the distance traveled to the cars record if its able to complete it
          if (car.getUsableFuel() > 0)
            car.addTrip(Integer.parseInt(distance));

          // Prints fuel statistics
          System.out.println(car.getUsableFuel() < 0
                  ? "Not enough fuel for #" + car.getPlateNum()
                  : "Trip completed successfully for #" + car.getPlateNum()
          );
        }
      }
      if (!found)
        System.out.println("Not enough fuel for #"+plateNum);
  }

  /**
    * Refills the tank of a given car
    * @param cars List of cars available
    * @param plateNum Plate Number of car wanting to be refilled
    */
  private static void refillCar(List<Car> cars, String plateNum) {
    for (Car car: cars)
      if (car.getPlateNum()==Integer.parseInt(plateNum))
        car.setUsableFuel(car.getModel().getTankCapacity()); //Sets fuel back to car model original size
  }

  /**
    * Creates a car to be driven
    * @param cars List of all cars
    * @param carModel List of all car models
    * @param input Input for creating a Car
    */
  public static void createCar(List<Car> cars, List<CarModel> carModel, String[] input){
    for(CarModel carModels: carModel)
      if (carModels.getModelName().equals(input[1])){
        cars.add(new Car(carModels,Integer.parseInt(input[2]))); // Creates car object
        break;
      }
  }
}

