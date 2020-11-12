import java.io.IOException;
import java.util.Scanner;

public class Encryptor {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String userChoice = "";
    final String welcomeMessage =
        "    ______                            __            \n"
            + "   / ____/___  ____________  ______  / /_____  _____\n"
            + "  / __/ / __ \\/ ___/ ___/ / / / __ \\/ __/ __ \\/ ___/\n"
            + " / /___/ / / / /__/ /  / /_/ / /_/ / /_/ /_/ / /    \n"
            + "/_____/_/ /_/\\___/_/   \\__, / .___/\\__/\\____/_/     \n"
            + "                      /____/_/                      \n"
            + "welcome.\nIf you want to ENCODING something - just press '1' to continue,\n"
            + "If you want to DECODING someshing, press '2'.";
    final String keyFilePath = "key.txt";

    System.out.println(welcomeMessage);

    userChoice = scan.nextLine();
    while (!(userChoice.equals("1") || userChoice.equals("2"))) {
      System.out.println("Sorry, i d'ont understand you, Please try again.");
      userChoice = scan.nextLine();
    }

    switch (userChoice) {
      case ("1"):
        System.out.println(
            "Please enter the path (absolute / relative)\n"
                + "of file you want to encrypt\n"
                + "(e.g. 'a.txt')");
        // get the path
        String originalFilePath = scan.nextLine();

        // get the data from file
        IoFile originalFile = new IoFile(originalFilePath);
        String textToEncrypt = "";
        try {
          textToEncrypt = originalFile.readFromFile();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }

        // generate key
        Encryption encryption = new Encryption();
        int encryptKey = encryption.generateEncryptKey();

        // encrypt data
        String encryptedStr = encryption.encryptData(textToEncrypt, encryptKey);

        // get full original file path
        String fullOriginalFilePath = originalFile.getAbsoluteFilePath();

        // generate the new file path
        String encryptFilePath = generateFilePath(fullOriginalFilePath, "_encrypt.");

        // write the encrypted data to encrypted file
        try {
          originalFile.writeToFile(encryptFilePath, encryptedStr);
        } catch (IOException e) {
          e.printStackTrace();
        }

        // generate the key file path
        String fullKeyFilePath = fullOriginalFilePath.replace(originalFilePath, keyFilePath);

        // write the key data to key file
        try {
          originalFile.writeToFile(fullKeyFilePath, String.valueOf(encryptKey));
        } catch (IOException e) {
          e.printStackTrace();
        }

        // Print message
        System.out.println(
            "the encrypt file path is in '"
                + encryptFilePath
                + "'\n"
                + "the encrypt key  file path is in '"
                + fullKeyFilePath
                + "'.");
        break;
      case ("2"):
        System.out.println(
            "Please enter the path (absolute / relative)\n"
                + "of file you want to decrypt, and then the path of the key");
        // get the path
        System.out.print("copy paste this: 'a_encrypt.txt':  ");
        String encryptedFilePath = scan.nextLine();
        System.out.print("copy paste this: 'key.txt': ");
        String keyEncryptFilePath = scan.nextLine();

        // get the data from the files
        IoFile encryptedFile = new IoFile(encryptedFilePath);
        IoFile keyFile = new IoFile(keyEncryptFilePath);
        String textToDecrypt = "";
        int valOfKey = 0;
        try {
          textToDecrypt = encryptedFile.readFromFile();
          valOfKey = Integer.parseInt(keyFile.readFromFile());
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }

        // decrypt data
        Decryption decryption = new Decryption();
        String decryptedStr = decryption.decryptData(textToDecrypt, valOfKey);

        // get full original file path
        String fullEncryptedFilePath = encryptedFile.getAbsoluteFilePath();
        // get full original key path
        String fullKeyPath = keyFile.getAbsoluteFilePath();

        // generate the new file path
        String decryptFilePath = generateFilePath(fullEncryptedFilePath, "_decrypt.");

        // write the encrypted data to encrypted file
        try {
          encryptedFile.writeToFile(decryptFilePath, decryptedStr);
        } catch (IOException e) {
          e.printStackTrace();
        }

        // Print message
        System.out.println("the decrypt file path is in '" + decryptFilePath + "'.");

        break;
      default:
        throw new IllegalStateException("Unexpected value: " + userChoice);
    }
  }

  private static String generateFilePath(String filePath, String textToAppend) {
    String[] arrOfFP = filePath.split("\\.");
    String newFilePath = arrOfFP[0];
    newFilePath = newFilePath.concat(textToAppend);
    return newFilePath.concat(arrOfFP[1]);
  }
}
