package Practice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Tokenizer {
    public static final HashSet<Character> symbols = new HashSet<>(List.of(
            '(',
            ')',
            '\''
    ));

    private final String prog;
    private int cursor;

    Tokenizer(String s) {
        prog = s;
        cursor = 0;
    }

    /**
     * Tokenizes the given source string into a list of tokens.
     *
     * @param source the source string to tokenize
     * @return a list of tokens
     */
    public static List<String> tokenize(String source) {
        // Split the source string by whitespace
        String[] split = source.split("\\s+");
        List<String> tokens = new LinkedList<>();

        // Process each substring resulting from the split
        for (String spacedTokens : split) {
            Tokenizer tokenizer = new Tokenizer(spacedTokens);
            // Extract tokens using the Tokenizer instance
            while (tokenizer.has_tokens()) {
                // Add each token to the token list if present
                tokenizer.next_token().ifPresent(tokens::add);
            }
        }
        return tokens;
    }

    /**
     * Extracts the next token if available.
     * <p>
     * Tokens are extracted character by character. When a special symbol is
     * encountered, the current token is returned and the cursor is adjusted accordingly.
     *
     * @return an Optional containing the next token, or an empty Optional if no more tokens
     */
    public Optional<String> next_token() {
        StringBuilder buffer = new StringBuilder();

        // Read characters until a special symbol is encountered or the end of the source is reached
        while (has_tokens()) {
            char c = read_char();
            buffer.append(c);

            // Check if the character is a special symbol
            if (symbols.contains(c)) {
                // If the buffer contains more than just the symbol, remove the symbol and rewind the cursor
                if (buffer.length() != 1) {
                    buffer.deleteCharAt(buffer.length() - 1);
                    rewind_cursor();
                }
                break;
            }
        }

        // Return the token if buffer is not empty, otherwise an empty Optional
        if (buffer.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(buffer.toString());
        }
    }

    /**
     * Moves the cursor back by one character.
     */
    private void rewind_cursor() {
        cursor--;
    }

    /**
     * Reads the next character from the source string.
     *
     * @return the next character
     */
    private char read_char() {
        return prog.charAt(cursor++);
    }

    /**
     * Checks if there are more tokens to extract.
     *
     * @return true if there are more tokens, false otherwise
     */
    private boolean has_tokens() {
        return cursor < prog.length();
    }
}
