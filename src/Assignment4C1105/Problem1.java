package Assignment4C1105;
/*
 * CSCI 1105 - Assignment 4 - Problem 1
 * Author: Egbor Osebhulimen
 * Date: 24-11-2022
 * Banner ID: B00928317
 * Description: Creates an 8x8 grid and sets the value of who
 * ever plays to T
 */

import java.util.Scanner;

public class Problem1{

    public static void main(String[] args){

        Scanner kb = new Scanner(System.in);

        // initalizes a 3x3 grid
        int[][] grid = new int[3][3];

        // Global Variables
        int row;
        int col;
        char last = 1;
        String playerInput = "null";

        // Handler to ensure it doesnt loop at sentinal value
        while ( !(playerInput.equals("Player1 -1")) && !(playerInput.equals("Player2 -1")) ){

            // Handler to ensure it doesnt print at sentinal value
            if ( !(playerInput.equals("Player1 -1")) && !(playerInput.equals("Player2 -1")) ) {

                playerInput = kb.nextLine(); //Recieves user input Eg: Player1 1 or Player2 1

            }

            last = playerInput.charAt(playerInput.length() - 1);  // extract players Index

            // Handler to sentinal value doesnt perform any operation on the grid
            if ( !(playerInput.equals("Player1 -1")) && !(playerInput.equals("Player2 -1")) ) {

                String playerName = playerInput.substring(0, 7);  // extract player name
                int playerValue = Character.getNumericValue(last); //Convert the last value to an int

                //Logic to know which row/coln you will play in
                if (playerValue % 3 != 0) {

                    col = (playerValue % 3) - 1;
                    row = (playerValue / 3);

                } else {

                    col = 2;
                    row = (playerValue / 3) - 1;

                }

                // Desides which player is placed in the gird
                if (playerName.equalsIgnoreCase("Player1")) {

                    grid[row][col] = 1;

                } else {

                    grid[row][col] = 2;

                }

            }

        }

        // Prints out the final grid
        for (int i = 0; i < 3; i++){

            for (int j= 0; j < 3; j++){

                System.out.print( grid[i][j]+ " ");

            }

            System.out.println();

        }

    }

}

