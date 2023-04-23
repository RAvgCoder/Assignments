package MyMTeest;
import TestLogFiles.TxtLogger;

import java.util.Arrays;

public class TeestClass {
    static TxtLogger txtLog = TxtLogger.getInstance();
    public static void main(String[] args) {
        System.out.println(Arrays.toString("lis tConta:tins".split("[: ]")));

    }

    private static void countto1000() {
        for (int i = 0; i < 100; i++) {
            txtLog.startTimer();
            System.out.println(i);
            txtLog.endTimer();
        }
    }

}

