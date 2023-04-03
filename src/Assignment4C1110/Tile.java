package Assignment4C1110;

/**
 * @name CSCI 1110 - Assignment 4
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a blueprint for how a section
 *               in the map should generally look like
 */
public abstract class Tile
{
    private final String symbol;
    private final String type;
    private int[] coordinate;
    protected Tile nextPosition;
    protected Bottle myBottle;

    public Tile(String symbol, String type){
        this.symbol = symbol;
        this.type = type;
    }

    /**
     * Sets a bottle to that particular section
     * of the sea
     * @param myBottle Bottle entering that part
     *                 of the ocean
     */
    public void acceptBottle(Bottle myBottle){
        this.myBottle = myBottle;
    }

    /**
     * Returns the symbol for the direction of the sea
     * and if land gives "X"
     * @return Direction
     */
    public String getSymbol(){return symbol;}

    /**
     * Type of section it is - Land or Sea
     * @return Type of section
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the coordinate this particular section
     * on the map
     * @param x x-asis
     * @param y y-asis
     */
    public void setCoordinate(int x, int y){
        coordinate = new int[]{x,y};
    }

    /**
     * Gets the coordinate this particular section
     * @return Co-ordinate points on the map
     */
    public int[] getCoordinate(){return coordinate;}

    /**
     * Returns the next position that that section
     * leads the bottle to
     * @return A section this one leads the bottle to
     */
    public Tile getNextPosition(){return nextPosition;}

    /**
    *  Returns true or false if the bottle could still
    *  travel.
    *  @return True if bottle can travel or false
    *          if not
    */
    public boolean hasNextPosition(){
        return nextPosition == null || myBottle.isStuck();
    }

    public Bottle getMyBottle(){return myBottle;}

    /**
     * Sets the next position that that section
     * leads the bottle to
     */
    public abstract void setNextPosition(Tile sea);

    /**
    *   Returns String contain the symbol of the
    *   current tile.
    *   @return String representing the symbol
    */
    public String toString(){return symbol;}

}
