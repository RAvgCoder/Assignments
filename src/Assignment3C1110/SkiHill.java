package Assignment3C1110;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 10-03-2023
 * @bannerID: B00928317
 * @description: Keeps track of all runs people can make on the hill.
 *               it can close skiRuns open skiRuns and give a list of all runs
 *               if open or closed.
 */
public class SkiHill {
    private String name;
    private ArrayList<SkiRun> skiRuns = new ArrayList<>();

    /**
     * Constructor that creates a skiHill object
     * @param name
     */
    public SkiHill(String name)
    {
        this.name = name;
    }

    // Getters
    public String getName(){return name;}
    public ArrayList<SkiRun> getSkiRuns(){return skiRuns;}

    /**
     * Adds a skiRun to a list of skiRuns
     * @param skiRun List of all skiRuns
     *               available in the skiHill
     */
    public void addSkiRun(SkiRun skiRun)
    {
        this.skiRuns.add(skiRun);
    }

    /**
     * Finds number of skiRuns available
     * @return Number of available skiRuns
     */
    public int numberSkiRuns()
    {
        return skiRuns.size();
    }

    /**
     * Opens all skiRuns for the Hill
     */
    public void openHill()
    {
        skiRuns.forEach(SkiRun::openRun);
    }

    /**
     * Closes all skiRuns for the Hill
     */
    public void closeHill(){skiRuns.forEach(SkiRun::closeRun);}

    /**
     * Finds number of open runs at the hill
     * @return number of open runs
     */
    public int numberOpenRuns()
    {
        return (int) skiRuns.stream()
                .filter(SkiRun::isOpen)
                .count();
    }

    /**
     * Finds number of open runs at the hill
     * @return number of open runs
     */
    public int numberClosedRuns()
    {
        return (int) skiRuns.stream()
                .filter(skiRun -> !skiRun.isOpen())
                .count();
    }

    /**
     * Finds all runs from the skiHill which are open
     * @return list of skiRuns open
     */
    public ArrayList<SkiRun> getOpenRuns()
    {
        return (ArrayList<SkiRun>) skiRuns.stream()
                .filter(SkiRun::isOpen)
                .collect(Collectors.toList());
    }

    /**
     * Finds all skiRuns that are closed in the skiHill
     * @return list of all closedRuns
     */
    public ArrayList<SkiRun> getClosedRuns()
    {
        return (ArrayList<SkiRun>) skiRuns.stream()
                .filter(skiRun -> !skiRun.isOpen())
                .collect(Collectors.toList());
    }


}