package TeestLogFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File fileName;
    private FileWriter fileWriter;
    private boolean newFile = true;
    private Object object = null;
    private String name = null;
    private boolean onEvent = false;
    private boolean startLog = false;
    private int count =0;
    private double totoalTimeSpent =0;
    private double timeElapsed =0;
    private String averageTimeSpent;
    private double startTime;
    private double endTime;
    private boolean action = false;


    public Logger(){}
    public void running(Object object, String name, boolean onEvent, double[] time){
        this.object = object;
        this.startTime = time[0];
        this.endTime = time[1];
        this.timeElapsed = time[2];
        this.name = name;
        logMain(onEvent,true);
        newFile = false;
    }

    public void endLog(double totoalTimeSpent, int count){
        this.totoalTimeSpent = totoalTimeSpent;
        this.count = count;
        this.averageTimeSpent = String.format("%.3f",totoalTimeSpent/count);
        logMain(onEvent, false);
        newFile = true;
    }

    private void logMain(boolean onEvent, boolean startLog){
        if (object == null)
            return;

        fileName = new File("./src/TeestLogFiles/"+name+".txt");
        try {
            if (newFile){ //    Creates the file header while also instantiating a new file any time its called
                fileWriter = new FileWriter(fileName, false);
                String pattern = "MM-dd-yyyy";
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
                String formated = dateTime.format(dateTimeFormat);
                fileWriter.write("~ ".repeat(6)+"File Info"+" ~".repeat(6)
                        +"\n"+formated+"\n"
                        +"--".repeat(16)+"\n");
                fileWriter.close();
            }
            if (onEvent) {
                fileWriter = new FileWriter(fileName, true);
                fileWriter.write("Start: " + startTime + "ms\n");
                fileWriter.write("End: " + endTime + "ms\n");
                fileWriter.write("TimeSpent: " + timeElapsed + "ms\n");
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
                fileWriter.write("TotalTime: " + totoalTimeSpent + "ms\n");
                fileWriter.write("TotalIterations: " + count + "\n");
                fileWriter.write("AverageTimeSpent: " + averageTimeSpent + "ms\n");
                fileWriter.write("--".repeat(16)+"\n".repeat(2));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
