package Assignment1C2110;

import java.util.Optional;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Kicks a ball with a given angle and direction on set pitch
     *
     * @param field         The field that would be played on
     * @param ball          The ball that was to be played
     * @param pixelDistance The distance that the ball would be kicked
     * @param degree        The angle that the ball would be kicked at
     */
    public void kick(Field field, Ball ball, double pixelDistance, double degree) {
        double degreeRad = Math.toRadians(degree);
        double newX = ball.getXpos() + pixelDistance * Math.cos(degreeRad);
        double newY = ball.getYpos() + pixelDistance * Math.sin(degreeRad);

        /*
         If when fist kicked and the ball is still in the pitch, then you stop
          else you resolve the ball's position
         */
        if (field.containsBall(new Ball(newX, newY))) {
            ball.move(newX, newY);
            return;
        }
        System.out.println("Reflected");

        // Determines the coordinate that was out of bounds
        Optional<Character> out = field.determineOutOfBoundCoordinate(newX, newY);

        /*
            If only one coordinate is to be reflected, then it would.
            Else the ball doesn't move from its original position
        */
        if (out.isPresent()) {
            if (out.get() == 'x') {
                ball.move(ball.getXpos(), newY);
            } else {
                ball.move(newX, ball.getYpos());
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
