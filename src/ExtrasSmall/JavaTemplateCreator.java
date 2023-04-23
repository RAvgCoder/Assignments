package ExtrasSmall;

import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
public class JavaTemplateCreator
{
    public static void main(String[] args) {
        Queue<String> templatesWanted;

        System.out.println( // User template choices
                """
                        What templates would you want created click -1 when done | Use 2x{name} to create multiple same templates
                        a) Abstract Class
                        b) Normal Class
                        c) Interface
                        d) Record
                        """
        );

        try(Scanner in = new Scanner(System.in)){

        }

        // Collects user input to create templates
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Start inputting your classes");
            templatesWanted = new LinkedList<>();
            collectInput(templatesWanted,input);
            templatesWanted = templatesWanted.stream().sorted().collect(Collectors.toCollection(LinkedList::new));
            System.out.println("List of classes to create :");
            templatesWanted.forEach(System.out::println);
            System.out.println("Are you happy with your choice {Y/N}");
        }while (input.nextLine().toUpperCase().charAt(0) == 'N');

        while (templatesWanted.size() != 0){
            switch (templatesWanted.remove()) {
//                case "Abstract" -> createAbstractTemp();
                case "Normal" -> createNormalTemp(input);
//                case "Interface" -> createInterfaceTemp();
//                case "Record" -> createRecordTemp();
            }
        }
        System.out.println("All classes have been created enjoy");
        input.close();
    }

    private static void createNormalTemp(Scanner input) {
        System.out.print("Inputting for Normal Classes, give fileName: ");
        String fileName = input.next();
        Queue<String>[] templates = instanceVariableMethodCreator_Norm_Abst();
        Queue<String> instVar = templates[0];
        Queue<String> method = templates[1];

        StringBuilder __instVar =  new StringBuilder();
        while (!instVar.isEmpty())
            __instVar.append(instVar.remove()).append(";\n");
        StringBuilder __method =  new StringBuilder();
        while (!method.isEmpty())
            __method.append(method.remove()).append("\n");

        StringBuilder __finalWrite =  new StringBuilder();
        __finalWrite.append(String.format(
                """
                public class %s {
                    %s
                    
                    public %1$s(){}
                    
                    %s
                }
                """,
                fileName, __instVar, __method
        ));
        writeToFile(fileName,__finalWrite);
    }

    private static void writeToFile(String fileName, StringBuilder finalWrite) {
        File file = new File(String.format(".\\src\\Practice\\%s.java",fileName));
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.append(finalWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a list of templates the user wants to make
     * @param templatesWanted List of templates to be created
     * @param input input from the scanner they'll use to get info
     */
    private static void collectInput(Queue<String> templatesWanted, Scanner input)
    {
        String choices = input.nextLine();
        while (!choices.equals("-1")) {
            choices = validateClasses(choices)
                    .map(str -> { // If found a valid word
                        System.out.println(Arrays.toString(str.split(" ")));
                        templatesWanted.addAll(
                                Arrays.asList(str.split(" "))
                        );
                        return input.nextLine();
                    })
                    .orElseGet(() -> {  // If given text wasn't valid
                        System.out.println("Wrong Input try again");
                        return input.nextLine();
                    });
        }
    }

    /**
     * Makes sure that the data inputted is valid and if not performs the
     * required operations
     * @param choices The users Input for their templates
     * @return String containing list of classes wanting to be created
     */
    private static Optional<String> validateClasses(String choices)
    {
        if(choices.length() < 4) return Optional.empty();

        HashMap<String, String> validWords = new HashMap<>();
        validWords.put("abs","Abstract");
        validWords.put("nor","Normal");
        validWords.put("cla","Normal");
        validWords.put("int","Interface");
        validWords.put("rec","Record");


        String[] words = choices.toLowerCase().trim().split("x");
        if (words.length == 1){
            return Optional.ofNullable(validWords.get( // Changes words to lower case then checks validity
                words[0].substring(0,3).toLowerCase()
            ));
        }else { // If user wants multiple classes created
            try {
                int numOfClasses = Integer.parseInt(words[0]);
                return Optional.ofNullable(validWords.get(words[1].substring(0, 3).toLowerCase()))
                    .map(str -> (str+" ").repeat(numOfClasses));
            }catch (NumberFormatException e){
                return Optional.empty();
            }
        }
    }

    /**
     * Creates instance variables with respective getters and setters for Normal and Abstract classes
     * @return InstanceVariables getters and setters
     */
    private static Queue<String>[] instanceVariableMethodCreator_Norm_Abst()
    {
        Queue<String[]> variables = new LinkedList<>(); // [accessMod, dataType, varName]
        Queue<String> instVariables = new LinkedList<>(); // "accessMod dataType varName"
        Queue<String> getter_setters = new LinkedList<>();    // [variables* , Getters & Setters]

        System.out.println("Give your instance variables in this format {accessor name:DataType}  exit at \"-1\"");
        Scanner input = new Scanner(System.in);
        String var = input.nextLine().trim();
        while (!var.equals("-1")){
            String[] nameDT = Arrays.stream(var.split("[ :]"))
                    .filter(str -> !str.equals("")).toArray(String[]::new);
            if (nameDT.length >= 3 && validateDataType(nameDT)){
                variables.add(nameDT);
                instVariables.add("\t"+String.join(" ",nameDT));
            }else {
                System.out.println("Wrong Input try again");
            }
            var = input.nextLine().trim();
        }

        System.out.println("Creating instance variables, getters and setters...\n");

        // Creates getters and setters for given variables
        for(String[] instanceVar: variables){
            String[] returnVal = createMethReturn(instanceVar[1]); // [returnType -> void/primitive/Obj, returnVal -> {0,null}]

            String getter = String.format("""
                    \tpublic %s get%s(){
                    \t    return %s;
                    \t}
                    """,
                    returnVal[0],
                    instanceVar[2].replace(instanceVar[2].charAt(0),Character.toUpperCase(instanceVar[2].charAt(0))), // getCapLetterBeginning
                    returnVal[1]);

            String setter = String.format("""
                    \tpublic void set%3$s(%2$s %1$s){
                    \t    this.%1$s = %1$s;
                    \t}
                    """,
                    instanceVar[2],instanceVar[1],
                    instanceVar[2].replace(instanceVar[2].charAt(0),Character.toUpperCase(instanceVar[2].charAt(0))));  // getCapLetterBeginning

            getter_setters.add(getter);
            getter_setters.add(setter);
        }
        return new Queue[]{instVariables, getter_setters};
    }

    private static String[] createMethReturn(String dataType)
    {
        String[] returnTypeAndVal = new String[]{dataType,null}; // Default for objects;
        if (Character.isLowerCase(dataType.charAt(0))){ // For void and primitives
            returnTypeAndVal[1] =  dataType.equals("void") ?  ""
                                                           : "0";
        }
        return returnTypeAndVal;
    }

    private static boolean validateDataType(String[] var)
    {
        for (String word: var){ // Checks if any word starts with a number {# | Sting | 1var}
            if (Character.isDigit(word.charAt(0))) return false;
        }

        switch (var[0]){
            case "#" -> {
                var[0] = "protected";
                String dataType = var[1].trim();
                var[1] = var[2].trim();
                var[2] = dataType;
                return true;
            }
            case "+" -> {
                var[0] = "public";
                String dataType = var[1].trim();
                var[1] = var[2].trim();
                var[2] = dataType;
                return true;
            }
            case "-" -> {
                var[0] = "private";
                String dataType = var[1].trim();
                var[1] = var[2].trim();
                var[2] = dataType;
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
