import javax.swing.*;

public class TreeVisualizationTester {
    public static void main(String[] args) {

        // Demonstrate Binary Search Tree
        // demonstrateBSTree();

        // Demonstrate AVL Tree
        demonstrateAVLTree();

        // Demonstrate Binary Search Tree: 1000 postcodes

        // Demonstrate AVL tree: 1000 postcodes
    }

    // Create and visualize an Binary Search tree
    public static void demonstrateBSTree() {
        // Create an BST tree using our BSTTree class
        BinarySearchTree bsTree = new BinarySearchTree();

        // Insert the same values as in the BST example
        // The tree structure will be different due to balancing
        bsTree.Insert("7");
        bsTree.Insert("6");
        bsTree.Insert("5");
        bsTree.Insert("4");
        bsTree.Insert("3");
        bsTree.Insert("2");
        bsTree.Insert("1");
        bsTree.Insert("0");
        bsTree.Insert("8");
        bsTree.Insert("9");

        // bsTree.Delete("6");

        // Visualize the AVL tree
        SwingUtilities.invokeLater(() -> {
            TreeVisualizer visualizer = new TreeVisualizer(bsTree.root, "Binary Search Tree");
        });

    }

    // Create and visualize an AVL tree
    // (balanced binary search tree where height difference <= 1)
    public static void demonstrateAVLTree() {
        // Create an AVL tree using our AVLTree class
        AVLTree avlTree = new AVLTree();

        // Insert the same values as in the BST example
        // The tree structure will be different due to balancing
        avlTree.Insert("7");
        avlTree.Insert("6");
        avlTree.Insert("5");
        avlTree.Insert("4");
        avlTree.Insert("3");
        avlTree.Insert("2");
        avlTree.Insert("1");
        avlTree.Insert("0");
        avlTree.Insert("8");
        avlTree.Insert("9");

        avlTree.Delete("6");

        // Visualize the AVL tree
        SwingUtilities.invokeLater(() -> {
            TreeVisualizer visualizer = new TreeVisualizer(avlTree.root, "AVL Tree (Balanced BST)");
        });

    }

    public static void demonstrateBSTree1000() {

    }
}