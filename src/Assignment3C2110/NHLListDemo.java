package Assignment3C2110;

import java.io.File;
import java.io.FileWriter;
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
        scanner.close();

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("nhlstatsoutput.txt", true);

            fileWriter.append("NHL Results Summary\n");
            fileWriter.append("Players with highest points and their teams:\n");
            fileWriter.append(nhlStats.playerWithMostPoints());
            fileWriter.append("\n");

            fileWriter.append("Most aggressive players, their teams and their positions:\n");
            fileWriter.append(nhlStats.mostAggressivePlayer());
            fileWriter.append("\n");

            fileWriter.append("Most valuable players and their teams:\n");
            fileWriter.append(nhlStats.getMVP());
            fileWriter.append("\n");

            fileWriter.append("Most promising players and their teams:\n");
            fileWriter.append(nhlStats.mostPromisingPlayer());
            fileWriter.append("\n");

            fileWriter.append("Teams that had the most number of penalty minutes:\n");
            fileWriter.append(nhlStats.mostPenaltyMinutes());
            fileWriter.append("\n");

            fileWriter.append("Teams that had the most number of game winning goals:\n");
            fileWriter.append(nhlStats.mostGameWins());
            fileWriter.append("\n");

            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Cant find file");
        }
    }
}
