import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    private static final String TEST_DIR = "London_Postcode_Test_Files";
    private static final String[] FILE_NAMES = {
            "1000_London_Postcodes.txt",
            "2000_London_Postcodes.txt",
            "4000_London_Postcodes.txt",
            "8000_London_Postcodes.txt",
            "16000_London_Postcodes.txt"
    };

    public static void main(String[] args) {
        try {
            System.out.println("=== Benchmarking BST Performance ===");
            runBenchmark("BST");

            System.out.println("\n=== Benchmarking AVL Performance ===");
            runBenchmark("AVL");

        } catch (IOException e) {
            System.err.println("Error reading test files: " + e.getMessage());
        }
    }

    private static void runBenchmark(String treeType) throws IOException {
        // Warm-up JVM with dummy data to remove extra time from the first benchmark operation
        List<String> warmupData = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            warmupData.add("WARMUP" + i);
            createAndInsert(treeType, warmupData);

        // Actual benchmark
        for (String fileName : FILE_NAMES) {
            List<String> postcodes = loadPostcodes(TEST_DIR + File.separator + fileName);
            int size = Integer.parseInt(fileName.split("_")[0]);

            long startTime = System.nanoTime();

            if (treeType.equals("BST")) {
                BinarySearchTree bst = new BinarySearchTree();
                for (String pc : postcodes) {
                    bst.Insert(pc);
                }
            } else if (treeType.equals("AVL")) {
                AVLTree avl = new AVLTree();
                for (String pc : postcodes) {
                    avl.Insert(pc);
                }
            }

            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1e6; // milliseconds

            System.out.printf("Size: %6d | Time: %8.2f ms%n", size, duration);
        }
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