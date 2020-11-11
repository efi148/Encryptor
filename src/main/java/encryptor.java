import java.util.Scanner;

public class encryptor {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String UserChoice = "";
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

    UserChoice = scan.nextLine();
    while (!(UserChoice.equals("1") || UserChoice.equals("2"))) {
      System.out.println("Sorry, i d'ont understand you, Please try again.");
      UserChoice = scan.nextLine();
    }

    switch (UserChoice) {
      case ("1"):
        System.out.println(
            "Please enter the path (absolute / relative)\n"
                + "of file you want to encrypt\n"
                + "(e.g. 'file.txt')");
        // get the path
        String originalFilePath = scan.nextLine();

        // get the data from file
        IoFile file = new IoFile(originalFilePath);
        String textToEncrypt = "";
        try {
          textToEncrypt = file.readFromFile();
        } catch (filesException e) {
          System.err.println(e.getMessage());
        }

        // generate key
        int encryptKey = (int) (Math.random() * 100 + 1);

        // encrypt data
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < textToEncrypt.length(); i++) {
          char Char = textToEncrypt.charAt(i);
          String currentChar = Integer.toHexString(Char);
          int hexVal = (Integer.parseInt(currentChar, 16) + encryptKey);
          newStr.append((char) hexVal);
        }
        String encryptedStr = newStr.toString();

        // get full original file path
        String fullOriginalFilePath = file.getAbsuluteFilePath();

        // generate the new file path
        String[] arrOfOFP = fullOriginalFilePath.split("\\.");
        String newFilePath = arrOfOFP[0];
        newFilePath = newFilePath.concat("_encrypt.");
        newFilePath = newFilePath.concat(arrOfOFP[1]);
        file.writeToFile(newFilePath, encryptedStr);

        // generate the key file path
        String fullKeyFilePath = fullOriginalFilePath.replace(originalFilePath, keyFilePath);
        file.writeToFile(fullKeyFilePath, String.valueOf(encryptKey));

        // Print message
        System.out.println(
            "the encrypt file path is in '"
                + newFilePath
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
        String encryptedFilePath = "a_encrypt.txt" /*scan.nextLine()*/;
        String keyEncryptFilePath = "key.txt" /*scan.nextLine()*/;

        // get the data from the files
        IoFile encryptedFile = new IoFile(encryptedFilePath);
        IoFile keyFile = new IoFile(keyEncryptFilePath);
        String textToDecrypt = "";
        int valOfKey = 0;
        try {
          textToDecrypt = encryptedFile.readFromFile();
          valOfKey = Integer.parseInt(keyFile.readFromFile());
        } catch (filesException e) {
          System.err.println(e.getMessage());
        }

        // decrypt data
        StringBuilder decryptedStrBuilder = new StringBuilder();
        for (int i = 0; i < textToDecrypt.length(); i++) {
          char Char = textToDecrypt.charAt(i);
          String currentChar = Integer.toHexString(Char);
          int hexVal = (Integer.parseInt(currentChar, 16) - valOfKey);
          decryptedStrBuilder.append((char) hexVal);
        }
        String decryptedStr = decryptedStrBuilder.toString();

        // get full original file path
        String fullEncryptedFilePath = encryptedFile.getAbsuluteFilePath();
        // get full original key path
        String fullKeyPath = keyFile.getAbsuluteFilePath();

        // generate the new file path
        String[] arrOfFEP = fullEncryptedFilePath.split("\\.");
        String decryptFilePath = arrOfFEP[0];
        decryptFilePath = decryptFilePath.concat("_decrypt.");
        decryptFilePath = decryptFilePath.concat(arrOfFEP[1]);
        encryptedFile.writeToFile(decryptFilePath, decryptedStr);

        // Print message

        System.out.println("the decrypt file path is in '" + decryptFilePath + "'.");

        break;
      default:
        throw new IllegalStateException("Unexpected value: " + UserChoice);
    }
  }
}
