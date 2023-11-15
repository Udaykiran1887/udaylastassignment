package lab11;
public class EncryptionTester {

    public static void main(String[] args) {
        String encryptedFilePath = "src/encrypted.txt";
        String decryptedFilePath = "src/decrypted.txt";
        String inputFilePath = "src/encryptMe.txt";
        String outputFilePath = "src/newEncrypted.txt";

        int shift = 1; // Specifies how much you would like to shift

        Encrypter enc = new Encrypter(shift);

        try {
            // Decrypt the encrypted.txt file and write to decrypted.txt
            enc.decrypt(encryptedFilePath, decryptedFilePath);

            // Encrypt the encryptMe.txt file with the same shift and write to newEncrypted.txt
            enc.encrypt(inputFilePath, outputFilePath);

            System.out.println("Tasks completed successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
