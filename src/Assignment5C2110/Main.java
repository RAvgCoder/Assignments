package Assignment5C2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declare a Huffman object
        Huffman huffman;
        String fileName;

        // Input file name
        Scanner input = new Scanner(System.in);
        System.out.print("Huffman Coding\n" +
                "Enter the name of the file with letters and probability: ");
         fileName = input.next();

        // Open the file "TopSort.txt"
        try (Scanner fileReader = new Scanner(new File(fileName)))
        {
            // Create an ArrayList to store the nodes for the Huffman tree
            ArrayList<Pair> huffmanNodes = new ArrayList<>();
            // Read each line from the file
            while (fileReader.hasNextLine()) {
                // Add a new Pair object to the ArrayList for each line in the file
                huffmanNodes.add(new Pair(fileReader.next().charAt(0), fileReader.nextDouble()));
            }
            // Create a new Huffman tree using the nodes from the ArrayList
            huffman = new Huffman(huffmanNodes.toArray(Pair[]::new));
        } catch (FileNotFoundException e) {
            // If the file is not found, throw a RuntimeException
            throw new RuntimeException(e);
        }

        input = new Scanner(System.in);
        // Read in words for encoding
        String text;
        // Print a header for the output
        System.out.println("Huffman messages encoded and decoded".toUpperCase());
        // Prompt the user to enter a line of text
        System.out.print("Enter a line of text (uppercase letters only): ");
        // Read the user's input
        text = input.nextLine();
        // Print the encoded line
        System.out.print("Here’s the encoded line: ");
        System.out.println(huffman.encode(text));
        // Print the decoded line
        System.out.print("The decoded line is: ");
        System.out.println(huffman.decode(huffman.encode(text)));
        input.close();
    }
}
