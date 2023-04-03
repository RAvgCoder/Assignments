package Assignment4C1110;

import java.util.HashSet;
/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a blueprint for how a Bottle object
 *               should look and behave like.
 */
public class Bottle
{
    private final String name;
    private Tile currPosition;
    private final String message;
    private boolean isStuck = false;
    private HashSet<Tile> visitedTile = new HashSet<>();
    private int tripCount =0;
    public Bottle(String name, String message, Tile currPosition){
        this.name = name;
        this.currPosition = currPosition;
        this.message = message;
    }

    // Getters
    public int[] getCoordinate(){return currPosition.getCoordinate();}
    public String getMessage(){return message;}
    public int getCount(){return tripCount;}
    public Tile getCurrPosition(){return currPosition;}

    // Setters
    public void setCurrPosition(Tile currPosition){
        this.currPosition= currPosition;
        int size = visitedTile.size();
        visitedTile.add(currPosition);
        isStuck = size == visitedTile.size();
    }

    public boolean isStuck(){return isStuck;}

    public void incrementCounter(){this.tripCount++;}

    /**
     * Checks if bottle is eligible for traveling and
     * @return True if it was able to travel one step on the sea,
     *         or false if stuck on the ocean or now gotten to Land.
     */
    public boolean travelling()
    {
        Tile currPosition = getCurrPosition();
        if(!ifBottleCanTravel())
            return false;

        // Prints the current location of the bottle
        System.out.printf(
                "%s: %s at (%d, %d): In ocean, current taking it %s.\n",
                getCount(),
                toString(),
                getCoordinate()[0],
                getCoordinate()[1],
                currPosition.getSymbol()
        );
        // Passes the bottle to the next part of the sea
        setCurrPosition(currPosition.getNextPosition());
        ((Sea)currPosition).removeBottle();
        currPosition.getNextPosition()
                .acceptBottle(
                        this
                );
        incrementCounter();
        return true;
    }

    /**
     * Checks if the bottle is stuck or has gotten to its destination
     * @return True if it can travel and false if it cant.
     */
    private boolean ifBottleCanTravel()
    {
        if (currPosition.hasNextPosition()) {
            // If the current position is a land it shouldn't be able to travel
            if (currPosition.getType().equals("Sea")) {
                ((Sea) currPosition).sinkBottle();
            } else { // or if stuck at sea would sink
                ((Land) currPosition).receiveMessage();
            }
            return false;
        }
        return true;
    }

    public String toString(){return name;}
}
