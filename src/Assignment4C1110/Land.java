package Assignment4C1110;

/**
 * @name CSCI 1110 - Assignment 4
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a blueprint for how a land
 *               tile should look like on the map
 */
public class Land extends Tile
{
    public Land(){super("X","Land");}

    /**
    *   Receives a message from a bottle
    */
    public void receiveMessage()
    {
        System.out.printf(
                "%d: %s at (%d, %d): LANDED!"+"\n"+
                "<<MESSAGE RECEIVED: %s>>\n",
                myBottle.getCount(),
                myBottle,
                getCoordinate()[0],
                getCoordinate()[1],
                myBottle.getMessage()
        );
        String x ="";
        x.length();
    }

    /**
    *   Sets the next position that that section
    *   leads the bottle to
    */
    @Override
    public void setNextPosition(Tile sea) {
        nextPosition = null;
    }
}
