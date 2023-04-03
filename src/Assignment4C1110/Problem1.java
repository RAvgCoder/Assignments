package Assignment4C1110;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a bottle path tracer whereby when
 *               the bottle is put in the sea it would tell 
 *               its starting position, to the user.
 */
public class Problem1 
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Bottle> bottles = new ArrayList<>();

        int[] mapSize = Arrays.stream(input.nextLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        Earth earth = new Earth(mapSize[0], mapSize[1]);

        // Input for land placement and sea current
        String[][] landAndSeaInput = {
                // Land placement
                input.nextLine().trim().split("\\s+"),
                // Sea current input
                input.nextLine().trim().split("\\s+")
        };

        // Defines what tiles of the map are land and then sets sea current for the rest
        earth.setLandParts(landAndSeaInput);

        createBottle(earth.getMap(), bottles, input);

        System.out.println(earth);
        bottles.forEach(bottle -> System.out.printf(
                    "Bottle named \"%s\" starting at (%d, %d)\n",
                    bottle,
                    bottle.getCoordinate()[0],
                    bottle.getCoordinate()[1]
        ));

    }

    /**
     * Creates different bottles with their names and messages
     * @param map   Map of the earth
     * @param bottles   List that would contain all bottles
     * @param input Scanner for taking input
     */
    private static void createBottle(Tile[][] map, ArrayList<Bottle> bottles, Scanner input)
    {
        int numberOfBottles = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numberOfBottles; i++) {
            // Bottle position
            int x = input.nextInt();
            int y = input.nextInt();

            String name = input.nextLine().trim();
            Bottle newBottle = new Bottle(name, input.nextLine(), map[x][y]);
            newBottle.setCurrPosition(map[x][y]);
            bottles.add(newBottle);
            map[x][y].acceptBottle(newBottle);
        }
    }

}