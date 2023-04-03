package TestLogFiles;

/**
 * @author: Egbor Osebhulimen
 * @date: 2023-02-27
 * @description: Essential methods that need to be able to
 * be implemented to become a logger class
 */
public interface Logger {

    /**
     * Validates file path to ensure
     * @param fileName File path used
     */
    void fileExtensionValidator(String fileName);

    /**
     * Resets all values to their default state
     */
    void resetVariables();
}
