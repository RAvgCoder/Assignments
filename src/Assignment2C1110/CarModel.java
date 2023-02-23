package Assignment2C1110;

/**
 * @param modelName Instance variables
 * @name CSCI 1110 - Assignment 2
 * @author: Egbor Osebhulimen
 * @date: 12-02-2023
 * @bannerID: B00928317
 * @description: Creates a car model with its properties. This is ca then be used
 * to create a car.
 */
public record CarModel(String modelName, double fuelEconomy, double tankCapacity) {
    /**
     * Creates a car model with its properties
     *
     * @param modelName    The name of the model you create
     * @param fuelEconomy  The fuel economy you want the model to have
     * @param tankCapacity The fuel tank capacity you want a car to have
     */
    public CarModel {
    }
}
