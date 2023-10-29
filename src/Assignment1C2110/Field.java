package Assignment1C2110;

import java.util.Optional;

public class Field {
    private final double width;
    private final double length;
    private final double xPos;
    private final double yPos;

    public Field(double xPos, double yPos, double length, double width) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.length = length;
        this.width = width;
    }

    public double getyPos() {
        return yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return String.format("[%.2f,%.2f]%.2f,%.2f", xPos, yPos, length, width);
    }

    /**
     * Checks if a ball is contained inside the field
     * @param ball Ball that's to be checked if it's in the field
     * @return Ture if the ball is in the field
     */
    public boolean containsBall(Ball ball) {
        return this.xPos <= ball.getXpos() &&                   // Top border
               this.yPos <= ball.getYpos() &&                   // Left border
               (this.xPos + this.length) >= ball.getXpos() &&   // Right border
               (this.yPos + this.width) >= ball.getYpos();      // Bottom border
    }

    /**
     * Determines which coordinate is out of bounds
     * @param newX  The xPos to be checked
     * @param newY  The yPos to be checked
     * @return  Returns nothing of both were out of bounds else it returns
     * the character of the coordinate that was out of bound.
     */
    public Optional<Character> determineOutOfBoundCoordinate(double newX, double newY) {
        char isAllOut = '\0';
        int count = 0;
        // Check x bounds
        if (!((xPos <= newX) && (xPos + length >= newX))) {
            count++;
            isAllOut = 'x';
        }
        // Check y bounds
        if (!((yPos <= newY) && (yPos + width >= newY))) {
            count++;
            isAllOut = 'y';
        }
        // If count == 2 then both x&y are out
        if (count == 2) return Optional.empty();
        else if (isAllOut == 'x') return Optional.of('x');
        else {
            return Optional.of('y');
        }
    }
}
