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

    /**
     * Search all of the pattern
     * 
     * @param text
     * @param pattern
     * @return A list
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            return matches;
        }

        int[] badChar = badCharHeuristic(pattern);
        int s = 0; // shift of the pattern

        while (s <= (n - m)) {
            int j = m - 1;

            // Reduce j
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            // If pattern is present
            if (j < 0) {
                matches.add(s);
                // Shift pattern
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
        return matches;
    }

}

public class Main2 {

}
