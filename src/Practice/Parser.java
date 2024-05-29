package Practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static Practice.SchemeCore.list;


public class Parser {
    private static List<String> tokens;
    private static int bracket_counter = 0;

    /**
     * Parses a list of tokens and returns the resulting object.
     * <p>
     * This method processes a list of tokens and returns
     * the list as a parsed Pair.
     *
     * @param t the list of tokens to parse
     * @return the resulting object after parsing
     * @throws IllegalStateException if tokens are unbalanced or invalid
     */
    public static Object parse(List<String> t) {
        tokens = t;
        bracket_counter = 0;

        // Handle the case where there is only one token
        if (tokens.size() == 1) {
            String s = tokens.remove(0);
            if (Tokenizer.symbols.contains(s.charAt(0))) {
                throw new IllegalStateException("No more tokens available");
            }
            return get_type(s);
        }

        // Recursively descend to parse the tokens
        Object parsed_object = parse_tokens();

        // Ensure all brackets are balanced
        if (bracket_counter != 0) {
            throw new IllegalStateException("Brackets are not fully balanced: " + bracket_counter);
        }

        return parsed_object;
    }


    /**
     * Parses tokens recursively and returns the resulting object.
     * <p>
     * This method processes tokens, managing nested structures with brackets, and
     * returns a nested list of Pair objects or a single Pair object
     * based on the token content.
     *
     * @return the resulting Pair object after parsing
     * @throws IllegalStateException if there are unbalanced brackets
     */
    private static Object parse_tokens() {
        if (tokens.isEmpty()) {
            throw new IllegalStateException("Unbalanced brackets");
        }

        ArrayList<Object> parsed_objects = new ArrayList<>();

        while (!tokens.isEmpty()) {
            // Take out a string to process
            String curr = tokens.remove(0);

            if (curr.equals("(")) {
                // Increment bracket counter for opening bracket
                bracket_counter++;
                // Recursively parse tokens inside brackets
                parsed_objects.add(parse_tokens());
            } else if (curr.equals(")")) {
                // Decrement bracket counter for closing bracket
                bracket_counter--;
                // Return a list of objects parsed within the brackets
                return list(parsed_objects.toArray());
            } else {
                // Add the parsed object to the list
                parsed_objects.add(get_type(curr));
            }
        }

        // Return the list of objects if no more tokens are available
        return to_list(parsed_objects);
    }

    /**
     * Converts an ArrayList of objects to a Pair object
     * <p>
     * If the list contains a single element that is an instance of Pair, it returns that element.
     * Otherwise, it converts the list to a Pair object.
     *
     * @param objects the ArrayList of objects
     * @return a single Pair object
     */
    private static Object to_list(ArrayList<Object> objects) {
        if (objects.size() == 1 && (objects.get(0) instanceof Pair)) {
            return objects.get(0);
        }
        return list(objects.toArray());
    }

    /**
     * Determines the type of token and returns the appropriate object.
     * <p>
     * This method interprets the token string and converts it to its respective object
     * <p>
     * BigInteger, Boolean, or Symbol based on its content.
     *
     * @param token the token string
     * @return the object representing the type of the token
     */
    private static Object get_type(String token) {
        // Check if the string represents a number
        if (token.split("[0-9]").length == 0) {
            return new BigInteger(token);
        }
        // Check if the string represents a negative number
        else if (token.charAt(0) == '-') {
            StringBuilder number = new StringBuilder(token).deleteCharAt(0);
            if (number.isEmpty()) return new Symbol("-");
            return new BigInteger(number.toString()).negate();
        }
        // Check if the string represents a boolean value
        else if (token.charAt(0) == '#') {
            if (token.charAt(1) == 't') return Boolean.TRUE;
            else return Boolean.FALSE;
        }
        // Check if the string represents a quote symbol
        else if (token.charAt(0) == '\'') {
            return new Symbol("quote");
        }
        // Default case: the string represents a symbol
        return new Symbol(token);
    }

}
