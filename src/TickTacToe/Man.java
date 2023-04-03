package TickTacToe;

import java.util.Scanner;

public class Man {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board(3);  // Initializes a new board
        Player player1 = null;  // Initializes player1
        Player player2 = null;  // Initializes player2
        Player[] player = new Player[]{player1, player2}; // Stores players

        System.out.println("Play with an Ai {Y} / {N}");
        boolean useAi = in.next().equalsIgnoreCase("Y");    // Checks if uses want to play against Ai
        playerSetup(player, useAi); // SetUp players

        // Announces player
        for (int i = 0; i < 2; i++) {
            System.out.printf("Player %d is %s\n",i+1,player[i].getEmoticon());
        }

        ///////////////////////Prints moves///////////////////////////// -> to be removed
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(j+" "+(char)(i+'A'));
            }
        }
        ///////////////////////////////////////////////////////////////////////

        do{
            board.bordReset(3);
            placePlayer(board,player,in);
            System.out.println("\nDo u want to play again? Y/N");
        }while (in.next().equalsIgnoreCase("Y"));

        System.out.println("Thanks for playing have a great day 😎");
        in.close();
    }

    /**
     * Places player on a board
     * @param player Player to placed on the board
     * @param useAi Instruction if player 2 would be AI or not
     */
    public static void playerSetup(Player[] player, boolean useAi)
    {
        Scanner input = new Scanner(System.in);
        while (true) {
            if (player[0] == null){    // Creates player 1
                System.out.println("Player 1 pick your avatar: {X} / {O}");
                String playerInput = input.next();  //
                if (playerInput.equalsIgnoreCase("X") || playerInput.equalsIgnoreCase("O")) {
                    player[0] = new Player(playerInput,false);
                }
                if (player[0]==null){
                    System.out.println("Symbol incorrect try again 😞");
                }
            }else{   // Creates player 2
                player[1] = new Player(    // Creates player 2 of type AI or human
                        player[0].toString().equals("X")
                        ?   "O"
                        :   "X",
                        useAi
                );
                return;
            }
        }
    }

    public static void placePlayer(Board board, Player[] player, Scanner in)
    {
        int count=0;
        while(!board.noSpaceOnBoard()) {
            clearScreen();
            count = count%2; // Decides which player gets to play
            System.out.println(player[count].getEmoticon() + " its your turn");
            System.out.println("Input x & y coordinate {eg 0 A}");

            // x axis && y axis placement
            int x = in.nextInt();
            int y = in.next().toUpperCase().charAt(0) - 'A';
            String invalidInput = "Invalid input for " + x + " " + (char)(y+'A');

            // Validation of Input
            if (x > 2 || x < 0 || y > 3 || y < 0){  // Checks if it's not in the bounds of the board
                System.out.println(invalidInput+"\n Play out of bounds try again");
            }else{
                if (!board.placePlayer(player[count], x, y)){
                    System.out.println(invalidInput+"\n Player already on that position try again");
                }else{
                    System.out.println("Successfully placed at " + x + "-" + (char)(y+'A')+ "\n");
                    if (board.isWinner()){
                        System.out.println(board);
                        System.out.println(player[count].getEmoticon()+" has won the game 🥳");
                        board.showScoreBoard();
                        return;
                    }
                    System.out.println(board);
                    count++; // Picks the next player
                }
            }
        }
        // If no winner
        System.out.println("Game ends in a tie, men y'all are too good for this game 😒");
    }

    /**
     * Clears the screen for the users to play again
     * @author: https://stackoverflow.com/questions/2979383/how-to-clear-the-console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
