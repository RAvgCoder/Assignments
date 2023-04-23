package Assignment4C1110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @name CSCI 1110 - Assignment 4
 * @author: Egbor Osebhulimen
 * @date: 29-03-2023
 * @bannerID: B00928317
 * @description: This is a bottle path tracer whereby when given any amount of
 *               bottles and a map with sea directions it can path trace the bottle
 *               in the sea documenting it down till it gets to the Land. It alsp wold
 *               have the capbility to sink the bottle if it gets stuck in the ocean.
 */
public class GeneratorProb
{
    static int objectsCreated =0;
    public static void main(String[] args) {
        var start = System.nanoTime();
        QuestionGenerator.main(null);
        Scanner input = null;
        try {
            input = new Scanner(QuestionGenerator.fileName);
//            input = new Scanner(new File("C:\\Users\\egbor\\Pictures\\Ass4DeathScript.txt"));
            Scanner print = new Scanner(QuestionGenerator.fileName);
            while (print.hasNext()){
                System.out.println(print.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Bottle> bottles = new LinkedList<>();
        int[] mapSize = Arrays.stream(input.nextLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        // Creates the earth and map size for the journey
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
        landAndSeaInput = null;

        // Creates all the bottles that you want to send on the journey
        createBottle(earth.getMap(), bottles, input);

//         System.out.println(earth);

        // Prints out the bottles names and starting locations
        bottles.forEach(bottle -> System.out.printf(
                    "%s: Starting at (%d, %d)\n",
                    bottle,
                    bottle.getCoordinate()[0],
                    bottle.getCoordinate()[1]
        ));

        // Makes all bottles travel to their destination
        while (bottles.size() != 0){
            bottles = bottles.stream()
                    .filter(Bottle::travelling)
                    .collect(Collectors.toList());
        }

        input.close();

        System.out.println(
                "\n".repeat(2)+"Program finished in "
                +(double)(System.nanoTime()-start)/1000000000 +"sec"
        );
        System.out.println("Bottle objects created >>: "+objectsCreated);
    }

    /**
     * Creates different bottles with their names and messages
     * @param map   Map of the earth
     * @param bottles   List that would contain all bottles
     * @param input Scanner for taking input
     */
    private static void createBottle(Tile[][] map, List<Bottle> bottles, Scanner input)
    {
        int numberOfBottles = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numberOfBottles; i++) {
            objectsCreated++;
            // Bottle position
            int x = input.nextInt();
            int y = input.nextInt();

            String name = input.nextLine().trim();
            Bottle newBottle = new Bottle(name, input.nextLine(), map[x][y]);
            newBottle.setCurrPosition(map[x][y]);
            bottles.add(newBottle);
        }
    }
}