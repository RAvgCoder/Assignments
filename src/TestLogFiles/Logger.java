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
    private String fileName = null;

    /////////////       For Time calculation in Logger    ////////////
    private int itrCount =0;
    private double totalTimeSpent =0;
    private double timeElapsed =0;
    private String averageTimeSpent;

    /////////////     For Time calculation in chosen file    ////////////
    private double startTime;
    private double endTime;
    private boolean onEvent = false;

    ///////////////////////////////////////////////////////////////////////

    public static Logger LoggerInstance(){
        if (logger == null){
            return  logger = new Logger();
        }
        return logger;
    }

    private Logger() {}


//////////////////////////////////////////////////////////////////////////////
    public void startLog(String fileName){ // Not to be used in a loop
        this.fileName = fileName;
        logWriter(onEvent,true);
        newFile = false;
    }

    public void startTimer(){
        onEvent = true;
        startTime = (System.currentTimeMillis());
    }

    public void endTimer(){
        endTime = (System.currentTimeMillis());
        endTimeCalculation();
        logWriter(onEvent,true);
        this.onEvent = false;
    }

    public void endLog(){ // Not to be used in a loop
        this.averageTimeSpent = String.format("%.3f",totalTimeSpent/itrCount);
        logWriter(onEvent, false);
        newFile = true;
    }

    private void logWriter(boolean onEvent, boolean endLog){
        FileWriter fileWriter;

        File fileName = new File("./src/TestLogFiles/"+ this.fileName +".txt");
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
            if (onEvent) { //  VI
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
        if (!endLog)
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

    ////////////    Extra Stuff Don't touch    ///////////////
    private void endTimeCalculation(){
        timeElapsed = endTime - startTime;
        itrCount++;
        totalTimeSpent +=timeElapsed;
    }

    private void reset(){
        logger = null;
        newFile = true;
        fileName = null;
        itrCount =0;
        totalTimeSpent =0;
        averageTimeSpent="";
        startTime = 0;
        endTime = 0;
        onEvent = false;
        System.out.println("Log Status: Successful");
    }
}