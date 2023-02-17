package Assignment3C1105;/*
 * CSCI 1105 - Assignment 3 - Problem 1
 * Author: Egbor Osebhulimen
 * Date: 19-10-22
 * Banner ID: B00928317
 * Description: Game that decides the winner of a dice game by the oe with the highest score.
 */
// WRITE YOUR CODE HERE

import java.util.Scanner;


public class Problem1 {
    public static void main (String[] args){
        // Initializes var
        int player1Point = 0;
        int player2Point = 0;

        // This takes input  from the user
        Scanner kb = new Scanner(System.in);
        int player1Roll;
        int player2Roll;

        // Logic to decide who has the highest points
        while(player1Point<5 && player2Point<5){
            player1Roll = kb.nextInt();
            player2Roll =  kb.nextInt();


            if(player1Roll>player2Roll){
                System.out.println("Player 1 wins the round");
                player1Point++;
            }

            else if(player1Roll<player2Roll){
                System.out.println("Player 2 wins the round");
                player2Point++;
            }

            else{
                System.out.println("Round tied, no points given");
            }

        }

        // Final output decision
        if(player1Point > player2Point){
            System.out.println("Player 1 wins the game!");
        }else{
            System.out.println("Player 2 wins the game!");
        }


    }
}
