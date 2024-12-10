package Assignment6C2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        // Name of the file containing usernames and passwords
        String name = "TopSort.txt";

        // Read user information from the file and store it in a HashMap
        HashMap<String, UserInfo> userInfoHashMap = readFromFile(name);

        // Scanner to get user input
        Scanner userInp = new Scanner(System.in);

        // Loop to handle login attempts with a maximum of 3 tries
        for (int triesLeft = 2; triesLeft >= 0; triesLeft--) {
            // Prompt for username
            System.out.print("Login: ");
            String userName = userInp.next();

            // Prompt for password
            System.out.print("Password: ");
            String passWord = userInp.next();
            UserInfo user;
            if ((user = userInfoHashMap.get(userName)) != null) {
                if (user.password().equals(passWord)){
                    System.out.println("Login successful\n" +
                            "Welcome "+user.name());
                    return;
                }
            }
            if(triesLeft != 0)System.out.println("Either the username or password is incorrect. You have "+triesLeft+" more attempts.");
        }

        // If all attempts fail, inform the user and suggest contacting the administrator
        System.out.println("Sorry. Incorrect login. Please contact the system administrator.");
    }


    /**
     * Reads user information from a file and stores it in a HashMap.
     * The file should contain user data in a specific format: [FirstName LastName UserName Password]
     * Each line in the file represents a user entry.
     *
     * @param name The name of the file containing user information.
     * @return A HashMap containing user information where the key is the username and the value is a UserInfo object.
     */
    private static HashMap<String, UserInfo> readFromFile(String name) {
        // HashMap to store user information
        HashMap<String, UserInfo> userInfoHashMap = new HashMap<>();

        // Create a File object based on the provided file name
        File file = new File(name);

        try (Scanner reader = new Scanner(file)) {
            // Read lines from the file until there are no more lines
            while (reader.hasNextLine()) {
                // Split the line into an array based on spaces
                String[] user = reader.nextLine().split(" ");

                // Create a UserInfo object and store it in the HashMap with the username as the key
                userInfoHashMap.put(
                        user[2],
                        new UserInfo(
                                // Format the user's full name
                                String.format("%s %s", user[0], user[1]),
                                user[2], // Username
                                user[3]  // Password
                        )
                );
            }
        } catch (FileNotFoundException f) {
            // If the file is not found, print an error message and stack trace
            System.out.println("Could not open file in: " + file.getAbsolutePath());
            f.printStackTrace();
        }

        return userInfoHashMap; // Return the HashMap containing user information
    }

}

/**
 * Represents a user's information which encapsulates a user's name, username, and password.
 *
 * @param name     The full name of the user.
 * @param userName The username of the user.
 * @param password The password associated with the user's account.
 */
record UserInfo(String name, String userName, String password) {
}

