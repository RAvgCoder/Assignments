package Assignment3C1110;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @name CSCI 1110 - Assignment 3
 * @author: Egbor Osebhulimen
 * @date: 10-03-2023
 * @bannerID: B00928317
 * @description: Creates a SkiHill and fill in the runs that could
 *               be taking on the hill. It then welcomes users and recommends
 *               skiRuns based on their difficulty level
 */
public class SnowDay3 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        // Creates a new SkiHill
        SkiHill skiHill = new SkiHill(input.nextLine());
        System.out.printf("Welcome to %s!\n",skiHill.getName());

        addSkiRunsToHill(skiHill,input);

        welcomeSkiers(skiHill.getSkiRuns(),input);

        input.close();
    }

    /**
     * Welcomes Skiers to the skiHill and recommends the
     * rides they can go on based on their experience
     *
     * @param skiRuns list of available skiRuns
     * @param input
     */
    public static void welcomeSkiers(ArrayList<SkiRun> skiRuns, Scanner input)
    {
        int numOfSkiers = input.nextInt(); // Number of skiers coming to the skiHill
        for (int i = 0; i < numOfSkiers; i++) {
            int difficulty = input.nextInt();
            String name = input.nextLine().trim();
            Skier skier = new Skier(name,difficulty); // Creates a skier

            System.out.println("Welcome "+skier.getName());
            System.out.println("Your ski level is: "+skier.getSkierLevel());
            System.out.println("For your level, check out these runs:");

            int index=1;
            for (SkiRun skiRun: skiRuns)
                if (skier.canSki(skiRun)){
                    System.out.printf(
                        "%d. %s (%s - Level %d)\n",index,skiRun.getName(),skiRun.getSymbol(),skiRun.getDifficultyLevel()
                    );
                    index++;
                }

            System.out.println();
        }
    }

    /**
     * Adds a ski run to the list of available ski runs
     * offered at the ski hill
     *
     * @param skiHill The skiHill you want to add runs to
     * @param input
     */
    public static void addSkiRunsToHill(SkiHill skiHill, Scanner input)
    {
        int numOfSkiRuns = input.nextInt(); // Numbers of SkiRuns you want added

        for (int i = 0; i < numOfSkiRuns; i++) {
            int difficulty  = input.nextInt();
            String runName = input.nextLine().trim();
            switch (difficulty){ // Chooses a skiRun you want created from its difficulty
                case 1:
                    skiHill.addSkiRun(new EasyRun(runName));
                    break;
                case 2:
                    skiHill.addSkiRun(new MediumRun(runName));
                    break;
                case 3:
                    skiHill.addSkiRun(new HardRun(runName));
                    break;
            }
        }
    }
}
