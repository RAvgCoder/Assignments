package Assignment5C1105;
/*
 * CSCI 1105 - Assignment 4 - Problem 3
 * Author: Egbor Osebhulimen
 * Date: 24-11-2022
 * Banner ID: B00928317
 * Description: Creates an 8x8 grid and takes input from the defending player
 * placeing it on the grid. It also compares the input from the attacking player
 * and if played on the same spot gives an X[A hit] and an 0[A miss]. Prints out who the
 * game, and the stats related to the game.
 */
import java.util.HashMap;
import java.util.Scanner;

public class Problem4 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int board[][] = new int[8][8]; //Creates the array for grid

        int inputCount = input.nextInt(); //Tells how many inputs we have

        //Places the defending player on the grid
        for (int i = 0; i < inputCount; i++) {
            String defendingPlayerInput = input.next();
            char row = defendingPlayerInput.charAt(1);
            char col = defendingPlayerInput.charAt(0);

            setTarget(board, col, row); //Collects user input
        }

        //Places the attacking player on the grid
        String attackingPlayerInput ="";
        int attack =0;
        while (!(attackingPlayerInput.equals("-1"))){
            if (attack>0){

                char row = attackingPlayerInput.charAt(1);
                char col = attackingPlayerInput.charAt(0);
                attackGrid(board, col, row);
            }
            attack++;
            attackingPlayerInput = input.next(); //Input for  attacking player
        }

        //Creates the final String grid
        int stringLengthCount = 0; //Counts the length till the end of the loop
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                System.out.print(getGrid(board).charAt(stringLengthCount));
                System.out.print(" ");
                stringLengthCount++;
            }
            System.out.println("");
        }

        //Prints winner to the console
        if (checkAttackerWin(board)){
            System.out.println("Attacking Player Wins");
        }else {
            System.out.println("Defending Player Wins");
        }

        //Prints game statistics to the console
        System.out.println(getStats(board));


    }

    /**
     * Compares the defenders grid to your input and then places a 3 if your
     * input is placed on the grid of the defenders player but a 4 if not
     *
     * @param board Defending players grid
     * @param row Row being played on
     * @param col Column being played on
     */
    public static void attackGrid(int[][] board, char col, char row) {
        //Creates a mapping  for rows and col to the index
        HashMap<Character, Integer> colList = new HashMap<>();
        colList.put('A', 0);
        colList.put('B', 1);
        colList.put('C', 2);
        colList.put('D', 3);
        colList.put('E', 4);
        colList.put('F', 5);
        colList.put('G', 6);
        colList.put('H', 7);

        HashMap<Character, Integer> rowList = new HashMap<>();
        rowList.put('1', 0);
        rowList.put('2', 1);
        rowList.put('3', 2);
        rowList.put('4', 3);
        rowList.put('5', 4);
        rowList.put('6', 5);
        rowList.put('7', 6);
        rowList.put('8', 7);

        //Overwrites the value of the array to reflect hit(3) or miss(4)
        for (int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i==(rowList.get(row)) && j==(colList.get(col))){
                    if(board[i][j] == 1){
                        board[i][j] =3;
                    }else if(board[i][j] == 0){
                        board[i][j] =4;
                    }
                }
            }
        }


    }

    /**
     * Takes in an array, the column and row you want to place your target in.
     *
     * @param board Takes input of array type int
     * @param col Takes input of type char
     * @param row Takes input of type row
     */
    public static void setTarget(int[][] board, char col, char row) {
        //Creates a mapping  for rows and col to the index
        HashMap<Character, Integer> colList = new HashMap<>();
        colList.put('A', 0);
        colList.put('B', 1);
        colList.put('C', 2);
        colList.put('D', 3);
        colList.put('E', 4);
        colList.put('F', 5);
        colList.put('G', 6);
        colList.put('H', 7);

        HashMap<Character, Integer> rowList = new HashMap<>();
        rowList.put('1', 0);
        rowList.put('2', 1);
        rowList.put('3', 2);
        rowList.put('4', 3);
        rowList.put('5', 4);
        rowList.put('6', 5);
        rowList.put('7', 6);
        rowList.put('8', 7);

        board[rowList.get(row)][colList.get(col)] = 1;

    }

    /**
     * Converts the grid to a string from
     *
     * @param board This is a complete grid that was played on
     * @return  The grid in a string form
     */
    public static String getGrid(int[][] board) {
        StringBuilder boardString = new StringBuilder();

        //Prints hit[X] or miss[O] on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && j == 0) {
                    if (board[i][j] == 3) {
                        boardString = new StringBuilder("X");
                    } else if(board[i][j] == 4){
                        boardString = new StringBuilder("O");
                    }else {
                        boardString = new StringBuilder("_");
                    }
                }
                else {
                    if (board[i][j] == 3) {
                        boardString.append("X");
                    } else if(board[i][j] == 4){
                        boardString.append("O");
                    }else {
                        boardString.append("_");
                    }
                }

            }
        }

        return boardString.toString();

    }

    /**
     * Checks if the attacking player hits a defending player
     *
     * @param board A 2D array of type int
     * @return Returns true if the attacking player won and false if
     *          they don't.
     */
    public static boolean checkAttackerWin(int[][] board) {
        int defeatCount =0;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                //Checks the value on the board if its hit
                if (board[i][j] == 1){
                    defeatCount++;
                }
            }
        }
        return defeatCount <= 0;
    }

    /**
     * Prints out the game statistics at the end of the match deciding how
     * many hits, misses and remaining targets there are on the board.
     *
     * @param board A 2D array of type int.
     * @return The statistics of the game.
     */
    public static String getStats(int[][] board){
        int hit =0;
        int miss =0;
        int remainingTargets =0;

        /*Loops through the board and counts how many hits, misses and
        remaining Targets there are*/
        for (int i=0; i<8; i++){
            for (int j =0; j<8; j++){
                if(board[i][j] == 1){
                    remainingTargets++;
                }
                if(board[i][j] == 3){
                    hit++;
                }
                if(board[i][j] == 4){
                    miss++;
                }
            }
        }

        return "Hits : "+ hit +"\nMisses: "+miss+"\nRemaining Targets: "+remainingTargets+"\n";

    }

}
