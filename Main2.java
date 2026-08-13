import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementing the Boyer-Moore string algorithm
 */
class BoyerMoore {
    // ASCII characters
    private static final int ALPHABET_SIZE = 256;

    /**
     * Pattern to create bad character table
     * 
     * @param pattern
     * @return Array
     */
    private static int[] badCharHeuristic(String pattern) {
        int m = pattern.length();
        int[] badChar = new int[ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badChar[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            badChar[(int) pattern.charAt(i)] = i;
        }
        return badChar;
    }

}

public class Main2 {

}
