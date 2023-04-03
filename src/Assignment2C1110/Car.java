package Assignment2C1110;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @name CSCI 1110 - Assignment 2
 * @author: Egbor Osebhulimen
 * @date: 12-02-2023
 * @bannerID: B00928317
 * @description: Creates a car which can hold properties from a carModel.
 */

public class Car{
    // Instance variables
    private CarModel model = null;
    private final int plateNum;
    private double usableFuel;
    private List<Integer> allTrips = new ArrayList<>();

    /**
     * Creates a new car sharing its models property
     * @param model The model of the car you want to create
     * @param plateNum The plate number of the car you want to create
     */
    public Car(CarModel model, int plateNum){
        this.model = model;
        this.plateNum = plateNum;
        this.usableFuel = model.tankCapacity();
    }

    //Getters
    public int getPlateNum() {
        return plateNum;
    }
    public CarModel getModel() {
        return model;
    }
    public double getUsableFuel() {
        return usableFuel;
    }
    public List<Integer> getAllTrips(){return allTrips;}

    //Setters
    public void setUsableFuel(double usableFuel) {
        this.usableFuel = usableFuel;
    }
    public void setAllTrips(int distance){
        allTrips.add(distance);
    }

    /**
     * Calcualtes and reduces fuel when on a trip
     * @param distance Distance to be driven.
     */
    public void tripFuelLoss(double distance){
        // Calculates usable fuel available and then updates the fuel now usable by a car
        double fuelUsed = (distance/100.0)*model.fuelEconomy();
        setUsableFuel(usableFuel-fuelUsed);
    }

    public List<Integer> getFilteredTrips(int distance){
        return allTrips.stream()
                .filter(tripLength -> tripLength >= distance)
                .collect(Collectors.toList());
    }
}
