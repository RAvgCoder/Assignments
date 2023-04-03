package Assignment3C1110;


/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 10-03-2023
 * @bannerID: B00928317
 * @description: Creates a skier with their level and checks if they could
 *               go on a skiRun from their level
 */
public class Skier {

    private final String name;
    private final int skierLevel;

    // Constructor
    public Skier(String name, int skierLevel) {
        this.name = name;
        this.skierLevel = skierLevel;
    }

    // Getters
    public String getName() {return name;}
    public int getSkierLevel() {return skierLevel;}

    /**
     * Checks if a person is eligible for a skiRun
     * @param skiRun SkiRun ride available at a skiHill
     * @return If skier is eligible
     */
    public boolean canSki(SkiRun skiRun) {
        return this.skierLevel >= skiRun.getDifficultyLevel();
    }
}