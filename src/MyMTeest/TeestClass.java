package MyMTeest;
import TestLogFiles.TxtLogger;

public class TeestClass {
    static TxtLogger txtLog = TxtLogger.getInstance();
    public static void main(String[] args) {
        String x = "listContatins";
        txtLog.startLog(x,true);
        countto1000();
        txtLog.endLog();
    }

    private static void countto1000() {
        for (int i = 0; i < 100; i++) {
            txtLog.startTimer();
            System.out.println(i);
            txtLog.endTimer();
        }
    }

}

