package Assignment4C1110;

/**
 * @name CSCI 1110 - Assignment 4
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a blueprint for how a sea
 *               tile should look like on the map
 */
public class Sea extends Tile
{
    public Sea(String symbol){super(symbol,"Sea");}

    /**
     *   Sinks the bottle if it's stuck
     *   in the ocean
     */
    public void sinkBottle()
    {
        System.out.printf(
                "%s: %s at (%d, %d): <<NOW STUCK IN MID-OCEAN GYRE!>>\n",
                myBottle.getCount(),
                myBottle,
                getCoordinate()[0],
                getCoordinate()[1]
        );
        myBottle = null;
    }

    /**
     * Passes the bottle from one sea to the other
     * @return bottle sailing on the sea
     */
    public void removeBottle(){myBottle = null;}

    /**
     * Sets the next position that that a sea
     * would lead the bottles to.
     */
    @Override
    public void setNextPosition(Tile section)
    {
        nextPosition = section;
    }

}