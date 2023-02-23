package MyMTeest;
import TestLogFiles.Logger;

public class TeestClass {
    static Logger  logger  = Logger.getInstance();
    public static void main(String[] args) {
            logger.startLog("./src/TestLogFiles/listContains.txt",false);
            countto1000();
            logger.endLog();
    }

    private static void countto1000() {
        for (int i = 0; i < 1000; i++) {
            logger.startTimer();
            System.out.println(i);
            logger.endTimer();
        }
    }

}

