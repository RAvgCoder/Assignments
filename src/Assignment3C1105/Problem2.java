package Assignment3C1105;/*
 * CSCI 1105 - Assignment 3 - Problem 2
 * Author: Egbor Osebhulimen
 * Date: 19-10-22
 * Banner ID: B00928317
 * Description: Game that decides the winner of a dice game by the oe with the highest score. If theres a tie
 * the round is left null and non recieve a point, and the round is continued.
 *
 */
// WRITE YOUR CODE HERE

import java.util.Scanner;
public class Problem2 {
    // WRITE YOUR CODE HERE
    public static void  main(String[] args){

        // Variable initialisers
        int player1Points = 0;
        int player2Points  = 0;
        int roundNum;

        // Player inputs
        Scanner kb =  new Scanner(System.in);
        int amountOfRounds = kb.nextInt();
        int player1Roll = kb.nextInt();
        int player2Roll = kb.nextInt();

        // Loops amount of round specified by user
        for(int roundName = 1; roundName <= amountOfRounds; roundName++ ){
            System.out.println("Round #" + roundName);

            if  (player1Roll > player2Roll){
                System.out.println("Player 1 wins the round");
                player1Points++;
            }else if (player2Roll > player1Roll){
                System.out.println("Player 2 wins the round");
                player2Points++;
            }else{
                System.out.println("Round tied, no points given");
            }

            player1Roll = kb.nextInt();
            player2Roll = kb.nextInt();

            if ((amountOfRounds == roundName) && !(player1Points == player2Points)){
                if (player1Points > player2Points){
                    System.out.println("Player 1 wins the game!");
                }else{
                    System.out.println("Player 2 wins the game!");

                }

                // Game Logic that decides the winner during the tie breaker
            } else if ((amountOfRounds == roundName) && (player1Points == player2Points)){

                System.out.println("Both players ended up with the same score");
                System.out.println("Tie-breaker game started!");


                while ((player1Roll == player2Roll)){

                    player1Roll = kb.nextInt();
                    player2Roll = kb.nextInt();

                }

                //Game output to the console
                if(player1Roll > player2Roll){
                    System.out.println("Player 1 wins the tie-breaker!");
                    System.out.println("Player 1 wins the game!");
                }else if(player1Roll < player2Roll){
                    System.out.println("Player 2 wins the tie-breaker!");
                    System.out.println("Player 2 wins the game!");
                }

            }

        }



    }
}
