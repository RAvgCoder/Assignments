package Assignment3C1110; /**
 * CSCI 1110 (W23)
 * @author Dr. Angela Siegel
 */

import java.util.*;

public class SnowDay2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("What's the name of the ski hill? ");
        String skiHillName = in.nextLine();


        SkiHill skiHill = new SkiHill(skiHillName);

        //System.out.print("How many ski runs from top of hill? ");
        int numberRuns = in.nextInt(); in.nextLine();

        for (int i=1; i<=numberRuns; i++) {
            //System.out.print("Enter run " + i + " info - Level(1-3) Name: ");
            int difficulty = in.nextInt();
            String runName = in.nextLine().trim();
            if (difficulty == 1) {
                skiHill.addSkiRun(new EasyRun(runName));
            }
            else if (difficulty == 2) {
                skiHill.addSkiRun(new MediumRun(runName));
            }
            else {
                skiHill.addSkiRun(new HardRun(runName));
            }
        }

        skiHill.openHill();
        System.out.println(skiHill.getName() + " is now open!");
        System.out.println();

        System.out.println("Ski patrol will make sure that all runs are safe to ski.");

        //System.out.print("Run to check: ");
        String runToClose = in.nextLine();

        while (!runToClose.equals("CHECK COMPLETE")) {

            for (SkiRun run : skiHill.getOpenRuns()) {
                if (run.getName().equals(runToClose)) {
                    System.out.println("CLOSING: " + runToClose);
                    run.closeRun();
                }
            }
            //System.out.print("Run to check: ");
            runToClose = in.nextLine();
        }
        System.out.println("CHECK COMPLETE");

        System.out.println();
        System.out.println("There following " + skiHill.numberOpenRuns() + " runs are open:");
        for (SkiRun run: skiHill.getOpenRuns()) {
            System.out.println(run.getName() + " (" + run.getSymbol() + " - Level " + run.getDifficultyLevel() + ")");
        }

        System.out.println();
        System.out.println("The following " + skiHill.numberClosedRuns() + " runs are closed:");

        for (SkiRun run: skiHill.getClosedRuns()) {
            System.out.println(run.getName() + " (" + run.getSymbol() + ", Level " + run.getDifficultyLevel() + ")");
        }





    }
}

/*
TEST INPUT:
Wentworth Southside
6
2 Beaver
2 Embree
2 Gambol
1 Garden Path
2 Giggletree
1 Horse Pastures
Gambol
Garden Path
CHECK COMPLETE
*/