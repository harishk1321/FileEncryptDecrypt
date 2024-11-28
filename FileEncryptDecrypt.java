   import java.io.*;
import java.util.Scanner;

public class FileEncryptDecrypt {
    private static final int SHIFT_KEY = 3; // Shift key for encryption and decryption

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for operation choice
        System.out.println("Choose an option: ");
        System.out.println("1. Encrypt a file");
        System.out.println("2. Decrypt a file");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Get input and output file names
        System.out.print("Enter the input file name (with path if necessary): ");
        String inputFile = scanner.nextLine();
        System.out.print("Enter the output file name (with path if necessary): ");
        String outputFile = scanner.nextLine();

        // Perform the chosen operation
        try {
            if (choice == 1) {
                encryptFile(inputFile, outputFile);
                System.out.println("File encrypted successfully.");
            } else if (choice == 2) {
                decryptFile(inputFile, outputFile);
                System.out.println("File decrypted successfully.");
            } else {
                System.out.println("Invalid choice. Please choose either 1 or 2.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to encrypt a file
    public static void encryptFile(String inputFile, String outputFile) throws IOException {
        processFile(inputFile, outputFile, SHIFT_KEY);
    }

    // Method to decrypt a file
    public static void decryptFile(String inputFile, String outputFile) throws IOException {
        processFile(inputFile, outputFile, -SHIFT_KEY);
    }

    // Method to process file for encryption or decryption
    public static void processFile(String inputFile, String outputFile, int shift) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
             
            int character;
            while ((character = reader.read()) != -1) {
                // Shift character if it is a letter
                if (Character.isLetter(character)) {
                    char base = Character.isUpperCase(character) ? 'A' : 'a';
                    character = (character - base + shift + 26) % 26 + base;
                }
                writer.write(character);
            }
        }
    }
}


