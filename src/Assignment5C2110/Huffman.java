package Assignment5C2110;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {
    // Array to store the Huffman encodings for each letter
    private final String[] encodings = new String[26];
    // The root of the Huffman tree
    BinaryTree<Pair> huffmanTree = null;

    /**
     * Constructor that takes an array of Pairs (letter and its frequency)
     *
     * @param huffmanCodes Array of letters and their probabilities
     */
    public Huffman(Pair[] huffmanCodes) {
        // Priority queue to store the nodes of the Huffman tree
        PriorityQueue<BinaryTree<Pair>> nodes
                = new PriorityQueue<>(Comparator.comparing(BinaryTree::getData));

        buildAnimation("Creating Nodes");
        // For each Pair in the input array, create a new node and add it to the queue
        for (Pair p : huffmanCodes) {
            BinaryTree<Pair> node = new BinaryTree<>();
            node.makeRoot(p);
            nodes.add(node);
        }
        // Build the Huffman tree from the nodes in the queue
        buildTree(nodes);
    }

    /**
     * Method to build the Huffman tree from a priority queue of nodes
     *
     * @param nodeQueue queue containing the nodes to be built to a tree
     */
    private void buildTree(final PriorityQueue<BinaryTree<Pair>> nodeQueue) {
        buildAnimation("Building the tree");
        // While there is more than one node in the queue
        while (nodeQueue.size() > 1) {
            // Create a new parent node
            BinaryTree<Pair> parent = new BinaryTree<>();
            // Remove the two nodes with the smallest frequencies from the queue
            BinaryTree<Pair> childRight = nodeQueue.remove();
            BinaryTree<Pair> childLeft = nodeQueue.remove();
            // Make the new parent node the root of a subtree with the two removed nodes as children
            parent.makeRoot(
                    new Pair('\0', childRight.getData().add(childLeft.getData())));
            parent.setLeft(childLeft);
            parent.setRight(childRight);
            // Add the new parent node back to the queue
            nodeQueue.add(parent);
        }

        // The remaining node in the queue is the root of the Huffman tree
        BinaryTree<Pair> parent = nodeQueue.peek();
        if (parent != null) {
            if (parent.getData().getValue() != '\0') {
                BinaryTree<Pair> newParent = new BinaryTree<>();
                newParent.makeRoot(new Pair('\0', parent.getData().getProb()));
                newParent.attachLeft(parent);
                parent = newParent;
            }
        }

        // Store the root of the Huffman tree
        huffmanTree = parent;
        assert huffmanTree != null;
        // Generate the Huffman encodings for each letter
        buildAnimation("Generating Encodings");
        generateEncodings(huffmanTree, new StringBuilder());
        System.out.println("All Done :)");
    }

    /**
     * Recursive method to generate the Huffman encodings for each letter
     *
     * @param currentLevel Current Level of the tree
     * @param prefix       All encodings generated till this point
     */
    private void generateEncodings(BinaryTree<Pair> currentLevel, StringBuilder prefix) {
        if (currentLevel.isLeaf())
            // If the current node is a leaf, store its Huffman encoding
            encodings[currentLevel.getData().getValue() - 'A'] = prefix.toString();
        else {
            // If the current node is not a leaf, recursively generate the Huffman encodings for its children
            generateEncodings(currentLevel.getLeft(), prefix.append("0"));
            prefix.deleteCharAt(prefix.length() - 1);
            generateEncodings(currentLevel.getRight(), prefix.append("1"));
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Encodes a message based on the tree
     *
     * @param text Text to be encoded
     * @return The encoded text
     */
    public String encode(String text) {
        String[] word = text.split("");
        if (Arrays.stream(word)
                .anyMatch(chr -> {
                    char c = chr.charAt(0);
                    return (Character.isLowerCase(c) || !Character.isAlphabetic(c))
                            && !Character.isWhitespace(c);
                })) {
            throw new IllegalArgumentException("Cannot pass text with which contains not all Caps or white space");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : word) {
            if (!Character.isWhitespace(s.charAt(0)))
                stringBuilder.append(encodings[s.charAt(0) - 'A']);
            else stringBuilder.append(s.charAt(0));
        }
        return stringBuilder.toString();
    }

    /**
     * Decodes and encoded message
     *
     * @param encodedMessage The message to be decoded
     * @return The decoded message
     */
    public String decode(String encodedMessage) {
        StringBuilder message = new StringBuilder();
        // Start at the root of the Huffman tree
        BinaryTree<Pair> currLevel = huffmanTree;
        for (int decoderPtr = 0; decoderPtr != encodedMessage.length() + 1; decoderPtr++) {
            if (currLevel == null) {
                System.out.println("Decoding failed");
                return null;
            }
            if (currLevel.isLeaf()) {
                message.append(currLevel.getData().getValue());
                currLevel = huffmanTree;
                // If the end of the encoded string has been reached, break the loop
                if (decoderPtr-- == encodedMessage.length()) break;
            } else {
                // If the end of the encoded string has been reached, break the loop
                if (decoderPtr == encodedMessage.length()) break;
                if (Character.isWhitespace(encodedMessage.charAt(decoderPtr))) {
                    message.append(" ");
                } else if (encodedMessage.charAt(decoderPtr) == '0') {
                    currLevel = currLevel.getLeft();
                } else {
                    currLevel = currLevel.getRight();
                }
            }
        }
        return message.toString();
    }

    /**
     * Plays an animation for steps taken in creating the huffman tree
     * @param stageName Name of the stage being executed
     */
    private void buildAnimation(String stageName) {
        try {
            System.out.print(stageName);
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error playing animation");
        }
    }
}