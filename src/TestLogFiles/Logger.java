package TestLogFiles;

import java.awt.geom.IllegalPathStateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author:         Egbor Osebhulimen
 * @date:           2023-02-22
 * @description:    Log created to benchmark time taken for processes to
 *                  complete in "ms". Uses a singleton class
 */
public class Logger {
    private static Logger logger = null;
    //  TODO document your code
    /////////////       For file writing Manipulation    ////////////
    private boolean newFile, useDefaultPath,  writing = true;
    private String fileName, averageTimeSpent = null;

    /////////////       For Time calculation    ////////////////////
    private int itrCount =0;
    private double totalTimeSpent, timeElapsed, startTime, endTime =0;

    /**
     * Creates a Singleton Class instance of an Object
     * @return Logger - Returns a new Logger if not already instantiated
     */
    public static Logger getInstance(){
        if (logger == null){
            return  logger = new Logger();
        }
        return logger;
    }

    /**
     * Class constructor
     */
    private Logger() {}

    /**
     * Used in conjunction with {@link #endLog()}, signify the scope area which tells the
     * class when you want to start and stop reading in files.
     * @param fileNameOrPath  A name if useDefault is set to true, or a path if false
     * @param useDefaultPath    Tells the class to not use the default file path { "./src/TestLogFiles/"+ this.fileName +".txt" }
     */
    public void startLog(String fileNameOrPath, boolean useDefaultPath){ // Not to be used in a loop
        this.fileName = fileNameOrPath;
        this.useDefaultPath = useDefaultPath;
        logWriter();
        newFile = false;
    }

    /**
     * Used in conjunction with {@link #startLog(String, boolean)}, signify the scope area which tells the
     * class when you want to start and stop reading in files.
     */
    public void endLog(){ // Not to be used in a loop
        this.averageTimeSpent = String.format("%.3f",totalTimeSpent/itrCount);
        writing = false;
        logWriter();
    }

    /**
     * Used in conjunction with the {@link #endTimer()}, to start and top the time it
     * takes to complete a process.
     */
    public void startTimer(){
        startTime = (System.currentTimeMillis());
    }

    /**
     * Used in conjunction with the {@link #startTimer()}, to start and top the time it
     * takes to complete a process.
     */
    public void endTimer(){
        endTime = (System.currentTimeMillis());
        endTimeCalculation();
        logWriter();
    }

    /**
     * Creates file writes log data into specified file and generates a summary of the log results
     */
    private void logWriter(){
        FileWriter fileWriter;

        File fileName = new File(
        //  Creates a file using default or specified file path
                this.useDefaultPath
                ?   "./src/TestLogFiles/"+ this.fileName +".txt"
                :   this.fileName        // Tests for file validity
        );
        try {
            if (this.newFile)
            {   //  Creates the file header while also instantiating a new file any time its called
                srcFileValidator(this.fileName);
                fileWriter = new FileWriter(fileName, false);
                //  Formats date and time
                final String pattern = "E, MMM dd yyyy HH:mm:ss";
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(pattern);
                String formatted = dateTime.format(dateTimeFormat);
                fileWriter.write(   // Create a header for the file
                        "~ ".repeat(6)+"File Info"+" ~".repeat(6)
                        +"\n"+formatted+"\n"
                        +"--".repeat(16)+"\n"
                );
                fileWriter.close();
                return;
            }
            if (writing)
            {   // Writes log to the file appending it to the last log
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Start: " + startTime + "ms\n");
                fileWriter.write("End: " + endTime + "ms\n");
                fileWriter.write("TimeSpent: " + timeElapsed + "ms | Iteration: "+ itrCount +"\n");
                //Separates logs with "-" & "\n"
                fileWriter.write("--".repeat(16)+"\n".repeat(2));
                fileWriter.close();
            }
            if (!writing)
            {   //Logs final total cumulative stats to the file
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Final File Stats");
                fileWriter.write("\n"+"--".repeat(16)+"\n".repeat(2));
                fileWriter.write("TotalTime: " + totalTimeSpent + "ms\n");
                fileWriter.write("TotalIterations: " + itrCount + "\n");
                fileWriter.write("AverageTimeSpent: " + averageTimeSpent + "ms\n");
                fileWriter.write("--".repeat(16)+"\n".repeat(2));
                fileWriter.close();
                reset();

            }
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    ////////////    Extra Helper methods Stuff Don't touch    ///////////////

    /**
     * Validates if the file extension is a .txt format and if a file name is given
     * @param fileName Name provide by the user
     */
    private void srcFileValidator(String fileName){
        String[] fileValidator = fileName.split("/");
        int length = fileValidator.length-1;
        var invalidPathErr = new IllegalPathStateException("Invalid path specified for "+fileName);
        if (fileValidator.length <= 1) { // If invalid src link
            throw  invalidPathErr;
        }else if (fileValidator[length].length()<5 || !fileValidator[length].contains(".txt")){ // Invalid fileName/Link {.txt}
            throw invalidPathErr;
        }
    }

    /**
     * Calculates total time spent on a function call
     */
    private void endTimeCalculation(){
        timeElapsed = endTime - startTime;
        itrCount++;
        totalTimeSpent +=timeElapsed;
    }

    /**
     * Resets all values to their default state
     */
    private void reset(){
        logger = null;
        writing = newFile = true;
        fileName = averageTimeSpent = null;
        itrCount =0;
        totalTimeSpent = startTime = endTime = 0;
        System.out.println("Log Status: Successful");
    }

}