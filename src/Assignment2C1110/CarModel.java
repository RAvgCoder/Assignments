package Assignment2C1110;
/**
 * @name CSCI 1110 - Assignment 2
 * @author: Egbor Osebhulimen
 * @date: 12-02-2023
 * @bannerID: B00928317
 * @description: Creates a car model with its properties. This is ca then be used
 * to create a car.
 */
public class CarModel {
    // Instance variables
    private final String modelName;
    private final double fuelEconomy;
    private final double tankCapacity;

    /**
     * Creates a car model with its properties
     * @param modelName The name of the model you create
     * @param fuelEconomy The fuel economy you want the model to have
     * @param tankCapacity The fuel tank capacity you want a car to have
     */
    public CarModel(String modelName, double fuelEconomy, double tankCapacity){
        this.modelName = modelName;
        this.fuelEconomy = fuelEconomy;
        this.tankCapacity = tankCapacity;
    }

    //  Getters
    public String getModelName() {
        return modelName;
    }
    public double getFuelEconomy() {
        return fuelEconomy;
    }
    public double getTankCapacity() {
        return tankCapacity;
    }
}
