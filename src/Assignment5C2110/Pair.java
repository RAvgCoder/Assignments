package Assignment5C2110;

import java.text.DecimalFormat;

public class Pair implements Comparable<Pair> {

    private final char value;
    private final double prob;

    public Pair(char value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    /*
    The compareTo method overrides the compareTo method
    of the Comparable interface.
    */
    public double add(Pair data) {
        if (data == null) return prob;
        else return Double
                    .parseDouble(new DecimalFormat("#.########")
                            .format(prob + data.prob));
    }

    public double getProb() {
        return prob;
    }

    public char getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair p) {
        return Double.compare(this.getProb(), p.getProb());
    }

    @Override
    public String toString() {
        return "Pair{ " +
                "value=" + value +
                ", prob=" + prob +
                " }";
    }
}
