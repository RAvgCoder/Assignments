package TestLogFiles;

/**
 * @author:         Egbor Osebhulimen
 * @date:           2023-02-22
 * @description:    Log class blueprint for how a general log should be calculated.
 *                  Default benchmark times are calculated in "ms".
 */
public abstract class AbstractLogger implements Logger{
    /////////////       For file writing Manipulation    ////////////
    protected boolean newSession = true;
    protected boolean useDefaultPath, writing = true;
    protected String fileName, averageTimeSpent = null;

    /////////////       For Time calculation    ////////////////////
    protected int itrCount =0;
    protected double totalTimeSpent, timeElapsed, startTime, endTime =0;

    /**
     * Creates file writes log data into specified file and generates a summary of the log results
     */
    protected abstract void logWriter();

    /**
     * Used in conjunction with {@link #endLog()}, signify the scope area which tells the
     * class when you want to start and stop reading in files.
     * @param fileNameOrPath  A name if useDefault is set to true, or a path if false
     * @param useDefaultPath    Tells the class to not use the default file path { "./src/TestLogFiles/"+ this.fileName +".txt" }
     */
    public void startLog(String fileNameOrPath, boolean useDefaultPath)
    {   // Not to be used in a loop
        this.fileName = fileNameOrPath;
        this.useDefaultPath = useDefaultPath;
        logWriter();
        newSession = false;
    }

    /**
     * Used in conjunction with {@link #startLog(String, boolean)}, signify the scope area which tells the
     * class when you want to start and stop reading in files.
     */
    public void endLog()
    {   // Not to be used in a loop
        this.averageTimeSpent = String.format("%.3f",totalTimeSpent/itrCount);
        writing = false;
        logWriter();
    }

    /**
     * Used in conjunction with the {@link #endTimer()}, to start and top the time it
     * takes to complete a process.
     */
    public void startTimer()
    {
        startTime = System.currentTimeMillis();
    }

    /**
     * Used in conjunction with the {@link #startTimer()}, to start and top the time it
     * takes to complete a process.
     */
    public void endTimer()
    {
        endTime = System.currentTimeMillis();
        endTimeCalculation();
        logWriter();
    }

    /**
     * Calculates total time spent on a function call
     */
    private void endTimeCalculation()
    {
        timeElapsed = endTime - startTime;
        itrCount++;
        totalTimeSpent +=timeElapsed;
    }

}