package Assignment4C1110;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class QuestionGenerator {
    public static File fileName;
    public static void main(String[] args) {
        var start = System.nanoTime();
        char[] directions = {'N','S','E','W'};
        HashSet<String> landCoordinate = new HashSet<>();
        StringBuilder landCoordinateStr = new StringBuilder();
        StringBuilder mapOutPut = new StringBuilder();
        StringBuilder bottleOutPut = new StringBuilder();
        Scanner input = new Scanner(System.in);

        System.out.println("Give the max map size of your choice");
        int mapSize = input.nextInt();
        input.close();
        int row = threadRand(3,mapSize);
        int col = threadRand(3,mapSize);
        String coordinate =  String.format("%s %s\n",row,col);

        // Calculates how many land and ocean sections there are
        int landSection = Math.min(row,col)/2;

        // Greeting FAKE
        IntStream.range(0,threadRand(1,3))
                .forEach(num -> System.out.println("Generating Output..."));

        // Generates Land coordinates
        for (int i = 0; i < landSection; i++) {
            int xL = threadRand(0,landSection);
            int yL = threadRand(0,landSection);
            landCoordinate.add(String.format("%s,%s ",xL,yL));
        }
        landCoordinate.forEach(landCoordinateStr::append);

        // -> this is calculated here to account for the possible duplicates that have been removed
        int oceanSection = (row*col)-landCoordinate.size();
        // Generates ocean directions
        IntStream.range(0,oceanSection)
                .forEach(num -> {
                    int oceanMod = threadRand(0,10)%directions.length;
                    mapOutPut.append(directions[oceanMod]+" ");
                });
        mapOutPut.append("\n");

        // Generates bottle info
        int bottleNum = threadRand(1,landSection+1);
        bottleOutPut.append(bottleNum+"\n");
        IntStream.range(0,bottleNum) // Creates bottle name and position
                .forEach(bottle -> {
                    int xB = threadRand(0,landSection);
                    int yB = threadRand(0,landSection);
                    bottleOutPut.append(String.format("%s %s %s\n",xB,yB, createBottleName()));
                    bottleOutPut.append(createBottleMessage()+"\n");
                });

        // Writes to the file and finalizes the time taken to complete the generation
        fileName = writeToFile(mapOutPut,bottleOutPut,landCoordinateStr,coordinate);
        System.out.print("File writing done in ");
        System.out.println((System.nanoTime() - start)/1000000000+"ms\n");
    }

    private static File writeToFile(StringBuilder mapOutPut, StringBuilder bottleOutPut, StringBuilder landCoordinate, String coordinate) {
        File fileName;
        FileWriter fileWriter;
        try {
            // Cleans the file for a fresh write
            fileName = new File("./src/MyMTeest/Assignment4-1110.txt");
            fileWriter = new FileWriter(fileName, false);
            fileWriter.append("");

            // Writes new content to the file
            fileWriter = new FileWriter(fileName, true);
            fileWriter.append(coordinate);
            fileWriter.append(landCoordinate);
            fileWriter.append("\n");
            fileWriter.append(mapOutPut);
            fileWriter.append(bottleOutPut);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    private static String createBottleMessage(){
        StringBuilder bottleMessage = new StringBuilder();
        int messageLen = threadRand(2,4);
        IntStream.range(0,messageLen)
                .forEach(num -> {
                    bottleMessage.append(randWords());
                });
        return bottleMessage.toString();
    }

    private static String createBottleName() {
        StringBuilder bottleName = new StringBuilder();
        int nameLen = threadRand(1,2);
        IntStream.range(0,nameLen)
                .forEach(num -> {
                    bottleName.append(randWords());
                });
        return bottleName.toString();
    }

    private static String randWords() {
        StringBuilder finals = new StringBuilder();
        int sentenceLen = threadRand(1,3);
        int wordLen = threadRand(2,6);

        IntStream.range(0,sentenceLen)
                .forEach(num -> {
                    finals.append((char) threadRand('A','Z'));
                    for (int j = 0; j < wordLen; j++) {
                        finals.append((char) threadRand('a','z'));
                    }
                    finals.append(" ");
                });
        return finals.toString();
    }

    private static int threadRand(int boundStart, int boundEnd){
        return ThreadLocalRandom.current().nextInt(boundStart, boundEnd);
    }
}
