package Assignment3C1110;
/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 10-03-2023
 * @bannerID: B00928317
 * @description: Creates a skiRun Ride can be open or closed
 */
public class SkiRun {
    private final String name;
    private final String symbol;
    private final int difficultyLevel;
    private boolean runIsOpen;


    /**
     * Creates a ski object
     * @param name name of the run
     * @param symbol Symbol of the run
     * @param difficultyLevel Difficulty level of the run
     */
    public SkiRun(String name, String symbol, int difficultyLevel)
    {
        this.name = name;
        this.symbol = symbol;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters
    public String getName(){return name;}
    public String getSymbol(){return symbol;}
    public int getDifficultyLevel(){return difficultyLevel;}

    /**
     * Returns the current state, if the run-If Open or Closed
     * @return True If  run is open or False if closed
     */
    public boolean isOpen()
    {
        return runIsOpen;
    }

    /**
     * Opens the run for Skiing
     */
    public void openRun()
    {
        runIsOpen = true;
    }

    /**
     * Closes the run for Skiing
     */
    public void closeRun()
    {
        runIsOpen = false;
    }

}