import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class TreeVisualizationTester {

    private static final String TEST_DIR = "London_Postcode_Test_Files";
    private static final String fileName = "1000_London_Postcodes.txt";

    public static void main(String[] args) throws IOException {

        List<String> postcodes = loadPostcodes(TEST_DIR + File.separator + fileName);

        // Demonstrate Binary Search Tree
        // demonstrateBSTree();

        // Demonstrate AVL Tree
        // demonstrateAVLTree();

        // Demonstrate MinHeap
        // demonstrateMinHeap();

        // Demonstrate Binary Search Tree: 1000 postcodes
        // demonstrateBSTree1000(postcodes);

        // Demonstrate AVL tree: 1000 postcodes
        demonstrateAVLTree1000(postcodes);

        // Demonstrate MinHeap: 1000 postcodes
        // demonstrateMinHeap1000(postcodes);
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
            new TreeVisualizer(bsTree.root, "Binary Search Tree");
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

        // avlTree.Delete("6");

        // Visualize the AVL tree
        SwingUtilities.invokeLater(() -> {
            new TreeVisualizer(avlTree.root, "AVL Tree (Balanced BST)");
        });

    }

    public static void demonstrateMinHeap() {
        // Create array-based min-heap (from your initial implementation)
        MinimumHeap minHeap = new MinimumHeap(15); // Assuming constructor takes maxSize

        // Insert test values
        minHeap.Insert("7");
        minHeap.Insert("6");
        minHeap.Insert("5");
        minHeap.Insert("4");
        minHeap.Insert("3");
        minHeap.Insert("2");
        minHeap.Insert("1");
        minHeap.Insert("0");
        minHeap.Insert("8");
        minHeap.Insert("9");

        // minHeap.ExtractMinimum();

        // Convert heap array to list (ignore nulls beyond current size)
        String[] heapArray = minHeap.getHeap(); // Requires getHeap() method
        List<String> heapList = new ArrayList<>();
        for (int i = 0; i < minHeap.Count(); i++) { // Use Count() to avoid nulls
            heapList.add(heapArray[i]);
        }

        // Visualize the heap
        SwingUtilities.invokeLater(() -> {
            new HeapVisualizer(heapList, "Minimum Heap Visualization");
        });
    }

    public static void demonstrateBSTree1000(List<String> postcodes){

        BinarySearchTree bsTree = new BinarySearchTree();
        for (String pc : postcodes) {
            bsTree.Insert(pc);
        }

        // Visualize the AVL tree
        SwingUtilities.invokeLater(() -> {
            new TreeVisualizer(bsTree.root, "Binary Search Tree");
        });
    }

    public static void demonstrateAVLTree1000(List<String> postcodes){       

        AVLTree avlTree = new AVLTree();
        for (String pc : postcodes) {
            avlTree.Insert(pc);
        }

        // Visualize the AVL tree
        SwingUtilities.invokeLater(() -> {
            new TreeVisualizer(avlTree.root, "AVL Tree (Balanced BST)");
        });

    }

    public static void demonstrateMinHeap1000(List<String> postcodes) {
        // Create array-based min-heap (from your initial implementation)
        MinimumHeap minHeap = new MinimumHeap(1001); // Assuming constructor takes maxSize

        for (String pc: postcodes){
            minHeap.Insert(pc);
        }

        // Convert heap array to list (ignore nulls beyond current size)
        String[] heapArray = minHeap.getHeap(); // Requires getHeap() method
        List<String> heapList = new ArrayList<>();
        for (int i = 0; i < minHeap.Count(); i++) { // Use Count() to avoid nulls
            heapList.add(heapArray[i]);
        }

        // Visualize the heap
        SwingUtilities.invokeLater(() -> {
            new HeapVisualizer(heapList, "Minimum Heap Visualization");
        });
    }

    private static List<String> loadPostcodes(String filePath) throws IOException {
        List<String> postcodes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                postcodes.add(line.trim());
            }
        }
        return postcodes;
    }
}