import java.io.IOException;
import java.util.Scanner;

// TODO delete all the comments in the class. The code should explain itself
public class EncryptorProgram {
  // TODO you never ever initialize a variable like that!!! you must initialize it only inside a function. Refactor!
  // TODO in addition, you must close your scanner after you opened it
  static Scanner scan = new Scanner(System.in);
  // TODO likewise the above
  final String keyFilePath = "key.txt";

  // TODO "startEncryption" is not a valid method name. Refactor name! (hint: You can reduce the name to just one word)
  public String startEncryption(int userChoice) {

    switch (userChoice) {
      case (1):
        try {
          return this.doEncrypt();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
      case (2):
        try {
          // TODO 2 things are wrong here: 1. Don't return a function call (difficult when there are bugs
          // TODO                          2. "this.doDecrypt()" is not java...think of another implementation
          return this.doDecrypt();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
      default:
        throw new IllegalStateException("Unexpected value: " + userChoice);
    }
  }

  // TODO "doEncrypt" is not a valid method name. Refactor name! (hint: You can reduce the name to just one word)
  public String doEncrypt() throws IOException {
    // get the original file path
    System.out.println(
        "Please enter the path (absolute / relative)\n"
            + "of file you want to encrypt\n"
            + "(e.g. 'a.txt')");
    String originalFilePath = scan.nextLine();

    // get the data from the original file
    IoFile originalFile = new IoFile(originalFilePath);
    // TODO readFromFile() should not be in this class. It's an IO operation
    String textToEncrypt = originalFile.readFromFile();

    // generate key
    // TODO you should not instantiate an Encryptor here...In addition Encryptor has a method called generateKey(). why?
    Encryptor encryptor = new Encryptor();
    // TODO "doEncrypt" is not a valid method name. Refactor name! (hint: You can reduce the name to just one word)
    int encryptKey = encryptor.generateEncryptKey();

    // encrypt data
    String encryptedStr = encryptor.encryptData(textToEncrypt, encryptKey);

    // get full original file path
    String fullOriginalFilePath = originalFile.getAbsoluteFilePath();

    // generate the new file path
    // TODO 2 things are wrong here: 1. Don't return a function call (difficult when there are bugs
    // TODO                          2. "this.doDecrypt()" is not java...think of another implementation
    String encryptFilePath = this.createFilePath(fullOriginalFilePath, "_encrypt.");

    // write the encrypted data to encrypted file
    originalFile.writeToFile(encryptFilePath, encryptedStr);

    // generate the key file path
    String fullKeyFilePath = fullOriginalFilePath.replace(originalFilePath, keyFilePath);

    // write the key data to key file
    originalFile.writeToFile(fullKeyFilePath, String.valueOf(encryptKey));

    // Print message
    return ("the encrypt file path is in '"
        + encryptFilePath
        + "'\n"
        + "the encrypt key  file path is in '"
        + fullKeyFilePath
        + "'.");
  }

  public String doDecrypt() throws IOException {
    System.out.println(
        "Please enter the path (absolute / relative)\n"
            + "of file you want to decrypt, and then the path of the key");
    // get the encrypted file path
    System.out.print("copy paste this: 'a_encrypt.txt':  ");
    String encryptedFilePath = scan.nextLine();
    System.out.print("copy paste this: 'key.txt': ");
    String keyEncryptFilePath = scan.nextLine();

    // get the data from the files
    IoFile encryptedFile = new IoFile(encryptedFilePath);
    IoFile keyFile = new IoFile(keyEncryptFilePath);
    String textToDecrypt = encryptedFile.readFromFile();
    int valOfKey = Integer.parseInt(keyFile.readFromFile());

    // decrypt data
    Decryptor decryptor = new Decryptor();
    String decryptedStr = decryptor.decryptData(textToDecrypt, valOfKey);

    // get full encrypted file path
    String fullEncryptedFilePath = encryptedFile.getAbsoluteFilePath();

    // generate the new file path
    String decryptFilePath = this.createFilePath(fullEncryptedFilePath, "_decrypt.");

    // write the decrypted data to decrypted file
    encryptedFile.writeToFile(decryptFilePath, decryptedStr);

    // Print message
    return ("the decrypt file path is in '" + decryptFilePath + "'.");
  }

  // TODO this method does not belong here, This is an IO operation...
  private String createFilePath(String filePath, String textToAppend) {
    String[] arrOfFP = filePath.split("\\.");
    String newFilePath = arrOfFP[0];
    newFilePath = newFilePath.concat(textToAppend);
    return newFilePath.concat(arrOfFP[1]);
  }
}
