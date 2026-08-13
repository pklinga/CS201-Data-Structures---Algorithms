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
        this.root = root;
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
            System.out.println("The " + value + "already exists!");
        }
        return current;
    }

}