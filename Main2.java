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

/**
 * Main2 - menu interface
 */
public class Main2 {
    // String with all 50 states
    private static final String STATES_TEXT = "Alabama, Alaska, Arizona, Arkansas, California, " +
            "Colorado, Connecticut, Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, " +
            "Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, " +
            "Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, " +
            "New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, " +
            "Oregon, Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, " +
            "Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming.";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Please choose an option (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-3).\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n50 US States");
                    System.out.println(STATES_TEXT);
                    break;

                case 2:
                    System.out.print("Enter a pattern for searching: ");
                    String pattern = scanner.nextLine();

                    // Call the Boyer-Moore algorythm
                    List<Integer> indices = BoyerMoore.search(STATES_TEXT, pattern);

                    if (indices.isEmpty()) {
                        System.out.println("Pattern " + pattern + " not found.\n");
                    } else {
                        System.out.println("Pattern " + pattern + " found at index: " + indices + "\n");
                    }
                    break;
                case 3:
                    System.out.println("Script stops.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select numbers (1-3).\n");
            }
        }
        scanner.close();
    }

    /**
     * Print menu
     */
    private static void printMenu() {
        System.out.println("STRING SEARCH MENU");
        System.out.println("1 - Display the text");
        System.out.println("2 - Search");
        System.out.println("3 - Exit program");
    }
}
