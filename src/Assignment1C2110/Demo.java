package Assignment1C2110;

import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        System.out.println("SOCCER GAME SETUP!");
        double xPos = rand(100, 500);
        double yPos = rand(500, 1000);
        Field dalField = new Field(0.0, 0.0, xPos, yPos);
        Ball soccerBall = new Ball(xPos / 2, yPos / 2);
        Player srini = new Player("Srini");
        Player raphael = new Player("Raphael");

        System.out.println("Player1: " + srini + "\nPlayer2: " + raphael + "\n" + dalField + "\n" + soccerBall);
        System.out.println("\n");

        // Starts match simulation
        for (int i = 0; i < 20; i++) {
            double pixelDis = rand(0, (int) Math.abs(xPos - yPos));
            double degree = rand(0, 360);

            // Allows player to play turn by turn for each kick
            Player currPlayer = (i % 2 == 0) ? srini : raphael;
            currPlayer.kick(dalField, soccerBall, pixelDis, degree);

            // Prints round details
            System.out.println("Player " + currPlayer + " kicks the ball for a distance of " + pixelDis + " pixels at " + degree + " degrees");
            System.out.println(soccerBall + "\n");
        }
    }

    public static double rand(int low_bound, int high_bound) {
        return new Random(System.nanoTime())
                .nextDouble(low_bound, high_bound);
    }
}
