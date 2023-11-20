package Assignment5C2110;

import java.text.DecimalFormat;

public class Pair implements Comparable<Pair> {
    // declare all
//required fields
    private final char value;
    private final double prob;

    public Pair(char value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    //constructor
//getters
//setters
//toString
/*
The compareTo method overrides the compareTo method
of the Comparable interface.
*/
    @Override
    public int compareTo(Pair p) {
        return Double.compare(this.getProb(), p.getProb());
    }

    public double getProb() {
        return prob;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "value=" + value +
                ", prob=" + prob +
                '}';
    }

    public double add(Pair data) {
        if (data == null) return prob;
        else return Double
                .parseDouble(new DecimalFormat("#.########")
                        .format(prob + data.prob));
    }
}
