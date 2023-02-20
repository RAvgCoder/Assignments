package TestLogFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger logger = null;
    //  TODO document your code
    /////////////       For file writing Manipulation    ////////////
    private boolean newFile = true;
    private String nameFile = null;

    /////////////       For Time calculation in Logger    ////////////
    private int itrCount =0;
    private double totalTimeSpent =0;
    private double timeElapsed =0;
    private String averageTimeSpent;

    /////////////     For Time calculation in chosen file    ////////////
    private double startTime;
    private double endTime;
    private boolean onEvent = false;
    public void startTimer(){
        onEvent = true;
        startTime = (System.currentTimeMillis());
    }
    public void endTimer(){
        endTime = (System.currentTimeMillis());
        timeElapsed = endTime - startTime;
        itrCount++;
        totalTimeSpent +=timeElapsed;
    }
    ///////////////////////////////////////////////////////////////////////

    public static Logger LoggerInstance(){
        if (logger == null){
            System.out.println("Log Status: Failed");
            throw new IllegalCallerException("Can not create a log without specifying a file name.\n" +
                    "Create like this Logger.LoggerInstance(\"________\")");
        }
        return logger;
    }
    public static Logger LoggerInstance(String nameFile){
        if (logger == null){
            return logger = new Logger(nameFile);
        }
        return logger;
    }

    private Logger() {}
    private Logger(String nameFile) {
        this.nameFile = nameFile;
    }

    public void running(){
        logMain(onEvent,true);
        newFile = false;
        this.onEvent = false;
    }

    public void endLog(){
        this.averageTimeSpent = String.format("%.3f",totalTimeSpent/itrCount);
        logMain(onEvent, false);
        newFile = true;
    }

    private void logMain(boolean onEvent, boolean startLog){
        FileWriter fileWriter;

        File fileName = new File("./src/TestLogFiles/"+ nameFile +".txt");
        try {
            if (newFile){ //    Creates the file header while also instantiating a new file any time its called
                fileWriter = new FileWriter(fileName, false);
                final String pattern = "E, MMM dd yyyy HH:mm:ss";
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(pattern);
                String formatted = dateTime.format(dateTimeFormat);
                fileWriter.write(
                        "~ ".repeat(6)+"File Info"+" ~".repeat(6)
                        +"\n"+formatted+"\n"
                        +"--".repeat(16)+"\n"
                );
                fileWriter.close();
            }
            if (onEvent) {
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Start: " + startTime + "ms\n");
                fileWriter.write("End: " + endTime + "ms\n");
                fileWriter.write("TimeSpent: " + timeElapsed + "ms | Iteration: "+ itrCount +"\n");
                fileWriter.write("--".repeat(16)+"\n".repeat(2));
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!startLog)
        {
            try {
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Final File Stats");
                fileWriter.write("\n"+"--".repeat(16)+"\n".repeat(2));
                fileWriter.write("TotalTime: " + totalTimeSpent + "ms\n");
                fileWriter.write("TotalIterations: " + itrCount + "\n");
                fileWriter.write("AverageTimeSpent: " + averageTimeSpent + "ms\n");
                fileWriter.write("--".repeat(16)+"\n".repeat(2));
                fileWriter.close();
                reset();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void reset(){
        logger = null;
        newFile = true;
        nameFile = null;
        itrCount =0;
        totalTimeSpent =0;
        timeElapsed =0;
        averageTimeSpent="";
        startTime = 0;
        endTime = 0;
        onEvent = false;
        System.out.println("Log Status: Successful");
    }
}
