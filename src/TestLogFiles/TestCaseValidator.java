package TestLogFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCaseValidator {
    public static void main(String[] args) {
        String[] inputAndExpected = new String[2];
        Scanner input = new Scanner(System.in);
        System.out.println("Use quick check");
        boolean quick = input.next().toUpperCase().charAt(0) == 'Y';
        if (quick){
            quickText(inputAndExpected);
        }else {
            givenExpected(inputAndExpected); // Teacher
            givenOutput(inputAndExpected); //Stud
        }
        String finals = testFIle(inputAndExpected[0],inputAndExpected[1]);
        System.out.print(finals);
        input.close();
    }

    /**
     * Used to skip dialogue and uses the default path names ".\\src\\TestLogFiles\\"+location[i]+".txt" to
     * store the files being inputted into
     * @param inputAndExpected Array for soring the read in information for input and expected
     */
    private static void quickText(String[] inputAndExpected) {
        String[] location = {"Input","Expected"};
        for (int i = 0; i < 2; i++) {
            File fileName = new File(
                ".\\src\\TestLogFiles\\"+location[i]+".txt"
            );
            StringBuilder fileOutput = new StringBuilder();
            try {
                Scanner file = new Scanner(fileName);
                while (file.hasNext()){
                    fileOutput.append(file.nextLine()).append("\n");
                }
                inputAndExpected[i] = fileOutput.toString();
            } catch (FileNotFoundException e) {
                System.out.println("<<(>> FILE NOT FOUND! <<)>>\n");
                e.printStackTrace();
            }
        }
    }

    /**
     * Loops until you successfully read in output from the user when giving the outPut location
     * @param inputAndExpected Array for soring the read in information for input&&Expected and expected
     */
    private static void givenOutput(String[] inputAndExpected) {
        System.out.println("Give file location for input:");
        while (userWantsToUseFile(inputAndExpected,0,"Input")){
                System.out.println("Input file path not correct try again");
        }
    }

    /**
     * Loops until you successfully read in expected from the user when giving the outPut location
     * @param inputAndExpected Array for soring the read in information for input&&Expected and expected
     */
    private static void givenExpected(String[] inputAndExpected) {
        System.out.println("Give file location for Expected:");
        while (userWantsToUseFile(inputAndExpected,1,"Expected")){
                System.out.println("Input file path not correct try again");
        }
    }

    /**
     * Read in outPut expected from the file from the path the user specified or a default path
     * ".\\src\\TestLogFiles\\"+location[i]+".txt"
     * @param inputAndExpected Array for soring the read in information for input&&Expected and expected
     * @param index Determines if you want to put in input[0] or Expected[1]
     * @param defaultLocation false if you want to specify your own path
     * @return true if the read was successful, or you are satisfied with your input
     */
    private static boolean userWantsToUseFile(String[] inputAndExpected, int index, String defaultLocation) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input file path Or use default: {Y(FilePath)/N(UseDefault)} ");
        String fileNameStr ="";
        boolean useDefault = in.nextLine().toUpperCase().charAt(0)!='Y';
        if (!useDefault){
            System.out.println("Now input the file path");
            fileNameStr = in.nextLine();
        }
        File fileName = new File(
              //  Creates a file using default or specified file path
            (useDefault)
//                ?   "C:\\Users\\egbor\\Videos\\com.dalAssignments\\src\\TestLogFiles\\"+defaultLocation+".txt"
                ?   ".\\src\\TestLogFiles\\"+defaultLocation+".txt"
                :   fileNameStr
        );
        StringBuilder fileOutput = new StringBuilder();
        try {
            Scanner file = new Scanner(fileName);
            while (file.hasNext()){
                fileOutput.append(file.nextLine()).append("\n");
            }
            inputAndExpected[index] = fileOutput.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n");
            return true;
        }
        System.out.println("Are you happy with the selection ?");
        return in.nextLine().toUpperCase().charAt(0) != 'Y';
    }

    /**
     * Tests the file to check for non-matches on a line
     * @param input The input string from the user
     * @param expected The correct input you want to validate against
     * @return String containing the result of the run
     */
    private static String testFIle(String input, String expected) {
        String finals ="";
        if (input.length()!=expected.length()) {
            finals += "Not the same length not all lines might have been searched \n"+
                        "-".repeat(30)+"\n";
        }
        Scanner inputReader = new Scanner(input);
        Scanner expectedReader = new Scanner(expected);
        int lineNum=0;
        while (inputReader.hasNext() && expectedReader.hasNext()){
            lineNum++;
            String outStr = inputReader.nextLine();
            String inStr = expectedReader.nextLine();
            if (!outStr.equals(inStr)){
                finals += String.format(
                        "Line Not correct at num %d\n"+
                                "User output is:\n%s\n"+
                                "Expected is:\n%s\n"+
                                "-".repeat(30)+"\n",
                        lineNum,
                        outStr,
                        inStr
                );
            }
        }
        finals += findExtra(inputReader,expectedReader,++lineNum);
        return (finals.equals("")) ? "Good Job Test Passed" : finals;
    }

    /**
     * Find Extra Lines if any and shows them to as lines that are longer than the other
     * stating who they were from
     * @param inputReader Reading from the input that contains possible lines not read
     * @param expectedReader Reading from the expected that contains possible lines not read
     * @param lineNum LineNumber the possible extraLines would start from
     * @return The extra lines if found
     */
    private static String  findExtra(Scanner inputReader, Scanner expectedReader, int lineNum) {
        StringBuilder extra = new StringBuilder();
        boolean output = false;
        while (inputReader.hasNext()){
            extra.append(inputReader.nextLine()).append("\n");
            output = true;
        }
        while (expectedReader.hasNext()){
            extra.append(expectedReader.nextLine()).append("\n");
        }
        if (!extra.toString().equals("")){
            if(output)
                extra.insert(0, "Extra Lines Detected from User on line number: " + lineNum + "\n");
            else
                extra.insert(0, "Extra Lines Detected from Expected on line number: " + lineNum + "\n");

            return extra.toString(); // If extra found return info
        }
        return ""; // If no extra found return nothing
    }


}