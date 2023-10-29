package Assignment2C2110;

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int inputN = inp.nextInt();
        // Read in user input till they give zero
        while (inputN != 0) {
            // for all the inputs given, find the collatz sequence
            getExecutionTimeForNCollatz(inputN);
            inputN = inp.nextInt();
        }
    }

    /**
     * Gets an execution time for finding the longest sequence n for a given sequence
     *
     * @param inputN The number for the collatz sequence we want
     */
    private static void getExecutionTimeForNCollatz(long inputN) {
        long executiontime, end, start = System.currentTimeMillis();
        long[] res = getMaxSeqLen(inputN);
        end = System.currentTimeMillis();
        executiontime = end - start;
        System.out.printf("%d %d %d %d\n", inputN, res[1], res[0], executiontime);
    }

    /**
     * Gets the max sequence from 1 to n - the given sequence for a collatz sequence
     *
     * @param inputN Max collatz to be calculated to
     * @return An array that has the number with the max sequence and its length
     */
    private static long[] getMaxSeqLen(long inputN) {
        long num = 0;
        long maxSeqLen = 0;

        for (long i = 1; i <= inputN; i++) {
            long currLen = generateSeqLen(i);
            if (currLen > maxSeqLen) {
                num = i;
                maxSeqLen = currLen;
            }
        }

        return new long[]{num, maxSeqLen};
    }

    /**
     * Find the length of a collatz sequence for a number
     *
     * @param inputN The num you want to find the length of a sequence
     * @return the length of the sequence
     */
    public static long generateSeqLen(long inputN) {
        long count = 1;
        while (inputN != 1) {
            if (inputN % 2 == 0) {
                inputN /= 2;
            } else {
                inputN = (3 * inputN) + 1;
            }
            count++;
        }
        return count;
    }
}
