package Assignment4C1110;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a blueprint for how an Earth object
 *               should look like and behave.
 */
public class Earth
{
    private Tile[][] map;

    // gives the movement pattern for all possible directions
    private HashMap<String,int[]> direction =  new HashMap<>();

    public Earth(int x, int y)
    {
        map = new Tile[x][y];
        direction.put("N",new int[]{-1,0});
        direction.put("S",new int[]{1,0});
        direction.put("E",new int[]{0,1});
        direction.put("W",new int[]{0,-1});
    }

    public Tile[][] getMap(){return map;}
    public int[] getBounds(){return new int[]{map.length, map[0].length};}

    /**
    * Runs a map stitch connecting all areas of
    * land and sea together by each sea tile holding
    * a reference to the next position it points to0.
    */
    public void runMapStitching()
    {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Tile tile = map[i][j];
                if (tile instanceof Sea){
                    // Finds the next point of the map that the sea should point to
                    int[] nextPoint = calculateDirection((Sea) tile);
                    // Gives the sea that coordinate to follow
                    tile.setNextPosition(
                            (nextPoint == null)
                            ?   null
                            :   map[nextPoint[0]][nextPoint[1]]
                    );
                }
            }
        }
        // Conforms stitch was done correctly
//        runDepthFirstSearch();
    }

    /**
     * Fills some map spots with Land and fills the rest with water
     * @param landAndSeaInput Gets the land inputs
     */
    public void setLandParts(String[][] landAndSeaInput){
        String[] landCoordinate = landAndSeaInput[0];
        Arrays.stream(landCoordinate).forEach(coordinate -> {
            int x = Integer.parseInt(coordinate.split(",")[0]);
            int y = Integer.parseInt(coordinate.split(",")[1]);
            map[x][y] = new Land();
            map[x][y].setCoordinate(x,y);
        });
        // Fills remaining tiles with water
        stats(landAndSeaInput);
        fillWithWater(landAndSeaInput[1]);
    }

    public void stats(String[][] landAndSeaInput){
        int lands = landAndSeaInput[0].length;
        int waters = landAndSeaInput[1].length;
        System.out.println("--".repeat(10)); // -----------------
        System.out.println(Arrays.toString(getBounds()));;
        System.out.println("LandParts: "+lands+" WaterParts: "+waters); // -----------------
        System.out.println((map.length* map[0].length)); // -----------------
        System.out.println((map.length* map[0].length) == (lands+waters));
        System.out.println("--".repeat(10)); // ---------------
    }

    /**
     * Fills the non land spots with water and its respective sea current
     * @param seaCurrent Input for determining sea current
     */
    private void fillWithWater(String[] seaCurrent)
    {
        int k =0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == null) {
                    String curr = seaCurrent[k];
                    map[i][j] = new Sea(curr);
                    map[i][j].setCoordinate(i,j);
                    k++;
                }
            }
        }
        runMapStitching();
    }

    /**
        * Calculates the direction of the map that the sea would
        * pass the bottle to, and if one exists it passes to self.
        * @param mapSection Sea that passes the bottle
        * @return Coordinate of the next section of passing
        */
    private int[] calculateDirection(Sea mapSection)
    {
        int[] currPosition = mapSection.getCoordinate();
        int[] bounds = getBounds();
        int[] moveBy = direction.get(mapSection.getSymbol());

        // Calculates new position
        int x = currPosition[0] + moveBy[0];
        int y = currPosition[1] + moveBy[1];

        // Checks of in bounds else returns to self
        if ((x>=0 && x<bounds[0]) && (y>=0 && y<bounds[1]))
            return new int[]{x,y};
        else
            return null;
    }

     /**
     * Runs a dfs algorithm that confirms the earth stitch
      * was performed correctly
     */
    private void runDepthFirstSearch()
    {
        System.out.println("\n<<- DFS traversal algorithm ->>");
        for (Tile[] tiles : map) {
            for (Tile section : tiles) {
                Tile tile = section;
                Bottle bottle = new Bottle("Temp","This is a dfs bottle", tile);
                while (true) {
                    bottle.setCurrPosition(tile);

                    ////////////////////////////////////////////////////////
                    System.out.print(tile + " -> ");
                    if (tile.hasNextPosition()) break;
                    tile = tile.getNextPosition();

                }
                System.out.println();
            }
        }
        System.out.println("\n".repeat(2));
    }

    /**
    * Returns a string of the map given
    * @return Map as a string
    */
    public String toString()
    {
        StringBuilder grid = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                grid.append(map[i][j].toString()+" ");
            }
            if (i != map.length-1)
                grid.append("\n");
        }
        return grid.toString();
    }
}
