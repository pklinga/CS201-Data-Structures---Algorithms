import java.util.Scanner;

/**
 * The Binary Search Tree node class
 */

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

/**
 * A Binary Search Tree class with the operations
 */

class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Create the default balanced tree from elements.
     */
    public void createDefaultTree() {
        this.root = null; // Delete any tree created earlier
        int[] initialValues = { 4, 2, 6, 1, 3, 5, 7 };
        for (int val : initialValues) {
            insert(val);
        }
        System.out.println("Binary Search Tree created successfully");
    }

    /**
     * Insert new node
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        } else {
            System.out.println("The " + value + " already exists!");
        }
        return current;
    }

    /**
     * Delete a node from the tree
     */
    public void delete(int value) {
        if (root == null) {
            System.out.println("The tree is empty");
        }
        root = deleteRecursive(root, value);
    }

    private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            System.out.println("No " + value + " value in the Tree!");
            return null;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = deleteRecursive(current.right, value);
        } else {
            // if 0 or 1 child
            if (current.left == null)
                return current.right;
            if (current.right == null)
                return current.left;

            // 2 children
            current.value = findMinValue(current.right);
            current.right = deleteRecursive(current.right, current.value);
        }
        return current;
    }

    private int findMinValue(TreeNode node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    /**
     * InOrder steps
     */
    public void printInOrder() {
        System.out.print("InOrder steps:");
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(TreeNode node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.value + " ");
            inOrderRecursive(node.right);
        }
    }

    /**
     * PreOrder steps
     */
    public void printPreOrder() {
        System.out.println("PreOrder steps: ");
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    /**
     * PostOrder steps
     */
    public void printPostOrder() {
        System.out.println("PostOrder steps: ");
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(TreeNode node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
}

/**
 * Main class for menu
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Please choose (1-7): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please choose a number (1-7).\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bst.createDefaultTree();
                    break;
                case 2:
                    System.out.print("Please give the next node value: ");
                    if (scanner.hasNextInt()) {
                        int valToAdd = scanner.nextInt();
                        bst.insert(valToAdd);
                        System.out.println(valToAdd + " value added.");
                    } else {
                        System.out.println("Invalid number!");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("Please give the node value for deletion: ");
                    if (scanner.hasNextInt()) {
                        int valToDelete = scanner.nextInt();
                        bst.delete(valToDelete);
                        System.out.println(valToDelete + " value deleted.");
                    } else {
                        System.out.println("Invalid number!");
                        scanner.next();
                    }
                    break;
                case 4:
                    bst.printInOrder();
                    break;
                case 5:
                    bst.printPreOrder();
                    break;
                case 6:
                    bst.printPostOrder();
                    break;
                case 7:
                    System.out.println("The script stops.");
                    running = false;
                    break;
                default:
                    System.out.println("Please choose a number (1-7)!");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("BINARY SEARCH TREE");
        System.out.println("1 - Create a binary search tree");
        System.out.println("2 - Add a node");
        System.out.println("3 - Delete a node");
        System.out.println("4 - Print nodes by InOrder");
        System.out.println("5 - Print nodes by PreOrder");
        System.out.println("6 - Print nodes by PostOrder");
        System.out.println("7 - Exit program");
    }
}