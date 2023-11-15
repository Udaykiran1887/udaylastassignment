package lab11;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     *
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        // Call the read method
        String message = readFile(inputFilePath);

        // Encrypt the file contents with the same shift
        encrypted = caesarCipherEncrypt(message, shift);

        // Write to a new file
        writeFile(encrypted, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        // Call the read method
        String encryptedMessage = readFile(messageFilePath);

        // Decrypt the file contents with the same shift
        String decryptedMessage = caesarCipherDecrypt(encryptedMessage, shift);

        // Write to a new file
        writeFile(decryptedMessage, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        StringBuilder message = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Read file from filePath
            while (scanner.hasNext()) {
                message.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            throw new Exception("Error reading the file: " + e.getMessage());
        }
        return message.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write to filePath
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Caesar Cipher Encryption
     *
     * @param message the message to be encrypted
     * @param shift   the shift amount
     * @return the encrypted message
     */
    private static String caesarCipherEncrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                encryptedMessage.append((char) ((ch - base + shift) % 26 + base));
            } else {
                encryptedMessage.append(ch);
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Caesar Cipher Decryption
     *
     * @param encryptedMessage the message to be decrypted
     * @param shift            the shift amount
     * @return the decrypted message
     */
    private static String caesarCipherDecrypt(String encryptedMessage, int shift) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (char ch : encryptedMessage.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                decryptedMessage.append((char) ((ch - base - shift + 26) % 26 + base));
            } else {
                decryptedMessage.append(ch);
            }
        }
        return decryptedMessage.toString();
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}


