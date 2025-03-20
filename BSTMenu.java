import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BSTMenu {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- BST Postcode Manager ---");
            System.out.println("1. Count postcodes");
            System.out.println("2. Search for a postcode");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Save postcodes to file");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Total postcodes: " + bst.Count());
                    break;
                case 2:
                    System.out.print("Enter postcode to search: ");
                    String searchPostcode = scanner.nextLine();
                    System.out.println("Input: " + searchPostcode);
                    System.out.println(bst.Search(searchPostcode) ? "Postcode found!" : "Postcode not found.");
                    break;
                case 3:
                    System.out.print("Enter postcode to add: ");
                    String addPostcode = scanner.nextLine();
                    bst.Insert(addPostcode);
                    System.out.println("Postcode added!");
                    break;
                case 4:
                    System.out.print("Enter postcode to delete: ");
                    String deletePostcode = scanner.nextLine();
                    System.out.println(bst.Delete(deletePostcode) ? "Postcode deleted!" : "Postcode not found.");
                    break;
                case 5:
                    savePostcodesToFile(bst);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void savePostcodesToFile(BinarySearchTree bst) {
        String[] postcodes = bst.InOrder();
        try (FileWriter writer = new FileWriter("postcodes.txt")) {
            for (String postcode : postcodes) {
                writer.write(postcode + "\n");
            }
            System.out.println("Postcodes saved to postcodes.txt!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}

