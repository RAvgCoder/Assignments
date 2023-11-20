package Assignment5C2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Read in probabilities for creating the tree
        Huffman huffman;
        try (Scanner input = new Scanner(
                new File("LettersProbability.txt"))) {
            ArrayList<Pair> huffmanNodes = new ArrayList<>();
            while (input.hasNextLine()) {
                huffmanNodes.add(new Pair(input.next().charAt(0), input.nextDouble()));
            }
            huffman = new Huffman(huffmanNodes.toArray(Pair[]::new));
            BinaryTree.inorder(huffman.getHuffmanTree());
            System.out.println();
            System.out.println(Arrays.toString(huffman.getEncodings()));
            for (int i = 'A'; i <= 'Z'; i++) {
                if (huffman.getEncodings()[i - 'A'] != null)
                    System.out.printf("%c: %s\n",i,huffman.getEncodings()[i - 'A']);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("StringBuilder message = new StringBuilder();\n" +
                "        BinaryTree<Pair> currLevel = huffmanTree;".toUpperCase());
        // Read in words for encoding
        try(Scanner input = new Scanner(System.in)) {
            String text;
            System.out.println("Enter a line of text (uppercase letters only):");
            text = input.nextLine();
            System.out.println(huffman.decode(huffman.encode(text)));
        }
    }
}
