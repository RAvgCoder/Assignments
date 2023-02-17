package Assignment3C1105;
/*
 * CSCI 1105 - Assignment 3 - Problem 3
 * Author: Egbor Osebhulimen
 * Date: 19-10-22
 * Banner ID: B00928317
 * Description: Game that decides the winner of a dice game by the oe with the highest score. If theres a tie
 * the round doest procede till a winner is declared.
 */
// WRITE YOUR CODE HERE
import java.util.Scanner;

public class Problem3 {
    // WRITE YOUR CODE HERE
    public static void main (String[] args){
        Scanner kb  = new Scanner(System.in);

        // Input for dice
        int player1Roll = kb.nextInt();
        int player2Roll = kb.nextInt();


        //Tie breaker Initializer & other values
        int player1Point = 0;
        int player2Point = 0;

        // Variable for mini-game
        int i = 1;
        int player1Roll10 = 0;
        int j = 1;
        int player2Roll10 = 0;



        // Game logic

        while (player1Point < 10 && player2Point < 10){

            if (player1Roll > player2Roll){
                System.out.println("Player 1 wins the round");
                player1Point++;
            }else if (player2Roll > player1Roll){
                System.out.println("Player 2 wins the round");
                player2Point++;


            }else if (player2Roll == player1Roll){

                // Starts mini tie game

                System.out.println("Round tied, starting the tie-breaker game!");

                player1Roll10 = 0;
                player2Roll10 = 0;

                while (player1Roll10 == player2Roll10){    //Repeats code if each 10 rolls are  equal
                    player1Roll10 = 0;
                    player2Roll10 = 0;
                    i = 1;
                    j = 1;
                    //  Counts the cumilative dice roll for player_1
                    while(i <= 10){

                        int player1RollTie = kb.nextInt();
                        player1Roll10 +=  player1RollTie;

                        if(i == 10){
                            System.out.println("Player 1 tie-breaker total is " + player1Roll10);

                        }

                        i++ ;
                    }

                    //  Counts the cumilative dice roll for player_2

                    while(j <= 10){

                        int player2RollTie = kb.nextInt();
                        player2Roll10 +=  player2RollTie;

                        if(j == 10){
                            System.out.println("Player 2 tie-breaker total is " + player2Roll10);

                        }

                        j++;
                    }


                    // Output for final score of mini tie game
                    if(player1Roll10  > player2Roll10){
                        player1Point += 3;
                        System.out.println("Player 1 wins the tie-breaker!");

                    }else if(player2Roll10  > player1Roll10){
                        player2Point += 3;
                        System.out.println("Player 2 wins the tie-breaker!");
                    }else{
                        System.out.println("Players tied again during the tie breaker! Playing it again!");

                    }
                }



            }

            // Handler for no such exeption error
            if(player1Point < 10 && player2Point < 10){
                player1Roll = kb.nextInt();
                player2Roll = kb.nextInt();
            }


        }


        //Logic for the final winner of the game and its output
        if (player1Point > player2Point){
            System.out.println("Player 1 wins the game!");
        }else{
            System.out.println("Player 2 wins the game!");

        }

    }

}
