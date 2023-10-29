package Assignment3C2110;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NHLListDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        NHLStats nhlStats = new NHLStats();
        try {
            // Read in file name and open file into scanner
            System.out.print("Enter the filename to read from: ");
            name = scanner.next();
            if (!name.endsWith(".txt")) name += ".txt";
            scanner.close();
            scanner = new Scanner(new File(name));
        } catch (IOException ioException) {
            System.out.println("Could not read File ");
            return;
        }

        // Read in input for each player stat
        while (scanner.hasNext()) {
            StringTokenizer tokenizer
                    = new StringTokenizer(scanner.nextLine(), "\t");

            while (tokenizer.hasMoreTokens()) {
                nhlStats.addPlayerRecord(new PlayerRecord(
                        tokenizer.nextToken(),
                        tokenizer.nextToken(),
                        tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                ));
            }
        }

        System.out.println("\nNHL Results Summary");
        System.out.println("Players with highest points and their teams:");
        nhlStats.playerWithMostPoints();
        System.out.println();

        System.out.println("Most aggressive players, their teams and their positions:");
        nhlStats.mostAggressivePlayer();
        System.out.println();

        System.out.println("Most valuable players and their teams:");
        nhlStats.getMVP();
        System.out.println();

        System.out.println("Most promising players and their teams:");
        nhlStats.mostPromisingPlayer();
        System.out.println();

        System.out.println("Teams that had the most number of penalty minutes:");
        nhlStats.mostPenaltyMinutes();
        System.out.println();

        System.out.println("Teams that had the most number of game winning goals:");
        nhlStats.mostGameWins();
        System.out.println();

        System.out.println(nhlStats.toString());
    }
}
