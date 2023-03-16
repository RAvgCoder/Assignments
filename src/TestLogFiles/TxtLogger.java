package TestLogFiles;

import java.awt.geom.IllegalPathStateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author: Egbor Osebhulimen
 * @date: 2023-02-27
 */
public class TxtLogger extends AbstractLogger{
    // Static variable instance of a class
    private static TxtLogger txtLog = null;

    /**
     * Class constructor
     */
    private TxtLogger() {}

    /**
     * Creates a Singleton Class instance of an Object
     * @return Logger - Returns a new Logger if not already instantiated
     */
    public static TxtLogger getInstance(){
        if (txtLog == null){
            return txtLog = new TxtLogger();
        }
        return txtLog;
    }

    /**
     * Creates file writes log data into specified file and generates a summary of the log results
     */
    @Override
    protected void logWriter() {

        String wavyLines = "~~".repeat(16);
        String newLine2x = "\n".repeat(2);

        FileWriter fileWriter;
        if (!super.useDefaultPath)
            fileExtensionValidator(super.fileName);

        File fileName = new File(
            //  Creates a file using default or specified file path
            super.useDefaultPath
            ?   "./src/MyMTeest/"+ super.fileName +".txt"
            :   super.fileName
        );

        try
        {
            if (super.newSession)
            {   //  Creates the file header while also instantiating a new file any time its called
                fileWriter = new FileWriter(fileName, false);
                //  Formats date and time
                final String pattern = "E, MMM dd yyyy HH:mm:ss";
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(pattern);
                String formatted = dateTime.format(dateTimeFormat);
                fileWriter.write(   // Create a header for the file
                        "~ ".repeat(6)+"File Info"+" ~".repeat(6)
                        +"\n"+formatted+"\n"
                        +"~ ".repeat(17)+"\n"
                );
                fileWriter.close();
                return;
            }

            if (super.writing)
            {   // Writes log to the file appending it to the last log
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Start: " + super.startTime + "ms\n");
                fileWriter.write("End: " + super.endTime + "ms\n");
                fileWriter.write("TimeSpent: " + super.timeElapsed + "ms | Iteration: "+ super.itrCount +"\n");
                //Separates logs with "-" & "\n"
                fileWriter.write("+-+".repeat(11)+newLine2x);
                fileWriter.close();
            }

            if (!super.writing)
            {   //Logs final total cumulative stats to the file
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Final File Stats\n");
                fileWriter.write(wavyLines+"\n");
                fileWriter.write("TotalTime: " + super.totalTimeSpent + "ms\n");
                fileWriter.write("TotalIterations: " + super.itrCount + "\n");
                fileWriter.write("AverageTimeSpent: " + super.averageTimeSpent + "ms\n");
                fileWriter.write(wavyLines+newLine2x);
                fileWriter.close();
                resetVariables();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    /**
     * Validates file path to ensure
     * @param fileName File path used
     */
    @Override
    public void fileExtensionValidator(String fileName) {
        String[] fileValidator = fileName.split("/");
        int length = fileValidator.length-1;
        var invalidPathErr = new IllegalPathStateException("Invalid path specified for "+fileName);
        if (fileValidator.length <= 1)
        {   // If invalid src link
            throw  invalidPathErr;
        }else if (fileValidator[length].length()<5 || !fileValidator[length].contains(".txt"))
        {   // Invalid fileExtension ".txt"
            throw invalidPathErr;
        }
    }

    /**
     * Resets all values to their default state
     */
    @Override
    public void resetVariables() {
        txtLog = null;
        super.writing = newSession = true;
        super.fileName = averageTimeSpent = null;
        super.itrCount =0;
        super.totalTimeSpent = startTime = endTime = 0;
        System.out.println("Log Status: Successful");
    }

}
