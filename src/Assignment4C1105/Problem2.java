package Assignment4C1105;/*
 * CSCI 1105 - Assignment 3 - Problem 2
 * Author: Egbor Osebhulimen
 * Date: 15-11-22
 * Banner ID: B00928317
 * Description: Initializes the grid and sets the logic for placemets
 * of the "1" and "2". It then searches the grid rows and columns for how many
 * times a player shows up, if its equal to 3 then that player has won
 */

import java.util.Scanner;

public class Problem2{
    public static void main(String[] args){

        Scanner kb = new Scanner(System.in);

        // initalizes a 3x3 grid
        int grid[][] = new int[3][3];

        // Global Variables
        int row;
        int col;
        char last = 1;
        String playerInput = "null";
        int play1Count = 0;
        int play2Count = 0;
        int j; // From a for loop [needs to be a Global Variables as its no visible to the outer forloop]
        int gridFlip =0; //Initializes a transpose of the 3x3  grid

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

            for (j= 0; j < 3; j++){

                System.out.print( grid[i][j]+ " ");

            }

            System.out.println();

        }

        //Checking the grid Horizontally and Vertically
        for (int i = 0; i < 3; i++){
            for (j= 0; j < 3; j++){

                //Horizontal check
                if (play1Count !=3 && play2Count!=3 && !(gridFlip == 1)) {

                    // Counts how many times the players name shows up
                    if (grid[i][j] == 1) {

                        play1Count++;

                    } else if (grid[i][j] == 2) {

                        play2Count++;

                    }

                }

                //Vertical check (Done by taking the transpose of the grid)
                if (play1Count !=3 && play2Count!=3 && (gridFlip == 1)) {

                    // Counts how many times the players name shows up
                    if (grid[j][i] == 1) {

                        play1Count++;

                    } else if (grid[j][i] == 2) {

                        play2Count++;

                    }

                }
            }

            //Resets the player count if no one won in that row / coln
            if (play1Count !=3 && play2Count!=3) {

                play1Count = 0;
                play2Count = 0;

            }

            // Initializer for the grid flip
            if (i ==2 && j ==3 && (gridFlip < 1)){

                i =-1;
                j =0;
                gridFlip++; //Creaates a transpose of the 3x3  grid

            }

        }


        // Print out for who won the game
        if (play1Count == 3){

            System.out.println("Game Over - Player 1 Wins");

        }else if (play2Count == 3){

            System.out.println("Game Over - Player 2 Wins");

        }else {

            System.out.println("Game Over - Draw");

        }

    }

}

