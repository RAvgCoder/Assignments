package Assignment1C2110;

public class Ball {
    private double xPos;
    private double yPos;

    public Ball(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXpos() {
        return xPos;
    }

    public double getYpos() {
        return yPos;
    }

    @Override
    public String toString() {
        return String.format("Ball is at (%.14f,%.1f)", xPos, yPos);
    }

    /**
     * Moves the ball to a new position
     * @param newX  The new xPos to be moved to
     * @param newY  The new yPos to be moved to
     */
    public void move(double newX, double newY) {
        this.xPos = newX;
        this.yPos = newY;
    }

}
