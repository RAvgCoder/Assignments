package Assignment6C2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


/**
 * Represents a node in a graph structure using a Java record.
 * Each node has a name and a list of edges connecting it to other nodes.
 *
 * @param name   The name or identifier of the node.
 * @param edges  A list of nodes that this node is connected to.
 */
record Node(String name, ArrayList<Node> edges) {
    /**
     * Custom equals method for Node.
     * Compares the name of the current node with the given object.
     *
     * @param str The string to compare for equality
     * @return True if the name of the current node equals the provided name, false otherwise.
     */
    public boolean equals(String str) {
        return name.equals(str);
    }
}


/**
 * This class represents a topological sorting algorithm implemented using depth-first search (DFS).
 * It takes an input file containing nodes and edges information and outputs the sorted order.
 */
public class Exercise2 {
    static HashSet<Node> visitedNodes = new HashSet<>();

    static Stack<Node> answer = new Stack<>();


    public static void main(String[] args) {
        // Define a variable `fileName` and assign it to "TopSort.txt"
        String fileName = "TopSort.txt";

        // as argument and store the returned ArrayList of Nodes in the variable `nodes`
        ArrayList<Node> nodes = readNodes(fileName);

        // Iterate over each Node present in the `nodes` ArrayList and run the method `topSort` on it.
        for (Node n : nodes) {
            topSort(n);
        }

        // Iterate over the number of elements present in the global variable `answer`
        // and print tab space with counting number
        System.out.print("topnum:");
        for (int i = 0; i < answer.size(); i++) {
            System.out.print("\t"+(i+1));
        }
        System.out.println();
        System.out.print("      ");
        // While the `answer` stack or list isn't empty, pop an element and print its name, separated by a tab space.
        while (!answer.isEmpty()) {
            System.out.print("\t"+answer.pop().name());
        }
    }

    /**
     * Performs a topological sorting of the graph starting from the given current node.
     *
     * @param currNode the current node to start the topological sorting from
     */
    private static void topSort(Node currNode) {
        // If the current node has already been visited, return to avoid infinite recursion.
        if (visitedNodes.contains(currNode)) return;

        // Mark the current node as visited.
        visitedNodes.add(currNode);

        // Iterate through the edges of the current node and perform topological sort on them.
        for (Node n : currNode.edges())
            topSort(n);

        // Add the current node to the answer list after processing its edges (post-order traversal).
        answer.add(currNode);
    }


    /**
     * Reads nodes from a file and returns an ArrayList of all the nodes.
     *
     * @param fileName the name of the file to read nodes from
     * @return an ArrayList of all the nodes read from the file
     */
    private static ArrayList<Node> readNodes(String fileName) {
        // Create a new file with the given filename
        File file = new File(fileName);

        // Define a new array list to hold all nodes
        ArrayList<Node> allNodes = new ArrayList<>();

        // Try block for handling potential FileNotFoundException
        try (Scanner fileInp = new Scanner(file)) {

            // Declare and initialize an integer count with the first integer in the defined file
            int count = fileInp.nextInt();

            // While loop that continues until we have read count numbers of nodes
            while (count-- != 0) {

                // Read the next node and edge strings from our file
                String nodeStr = fileInp.next();
                String edgeStr = fileInp.next();

                Node node = null;
                Node edge = null;

                // Iterating over each Node in allNodes list
                for (Node n : allNodes) {
                    // If existing node equals to our read string, assign it to node and edge slots
                    if (n.equals(nodeStr)) {
                        node = n;
                    }
                    if (n.equals(edgeStr)) {
                        edge = n;
                    }

                    // If both node and edge values found, break out of the loop
                    if (node != null && edge != null) break;
                }

                // If the node doesn't exist, create a new node using nodeStr and add it to allNodes list
                if (node == null) {
                    node = new Node(nodeStr, new ArrayList<>());
                    allNodes.add(node);
                }

                // If the edge doesn't exist, create a new Node using edgeStr and add it to the allNodes list.
                if (edge == null) {
                    edge = new Node(edgeStr, new ArrayList<>());
                    allNodes.add(edge);
                }

                // After we have our node, we add edge to its edge list
                node.edges().add(edge);
            }

        } catch (FileNotFoundException f) {
            // Catch block to handle FileNotFoundException and print useful information
            System.out.println("Could not open file in : " + file.getAbsolutePath());
            f.printStackTrace();
        }
        return allNodes;
    }


}
