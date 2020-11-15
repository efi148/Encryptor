import java.util.Scanner;

public class Menu {
  // TODO make this variable private. Otherwise, it'll be package accessible
  final String welcomeMessage =
      "    ______                            __            \n"
          + "   / ____/___  ____________  ______  / /_____  _____\n"
          + "  / __/ / __ \\/ ___/ ___/ / / / __ \\/ __/ __ \\/ ___/\n"
          + " / /___/ / / / /__/ /  / /_/ / /_/ / /_/ /_/ / /    \n"
          + "/_____/_/ /_/\\___/_/   \\__, / .___/\\__/\\____/_/     \n"
          + "                      /____/_/                      \n"
          + "welcome.\nIf you want to ENCODING something - just press '1' to continue,\n"
          + "If you want to DECODING something, press '2'.";

  public void print() {
    System.out.println(welcomeMessage);
    Scanner scan = new Scanner(System.in);
    int userChoice = scan.nextInt();
    while (!(userChoice == 1 || userChoice == 2)) {
      System.out.println("Sorry, i d'ont understand you, Please try again.");
      userChoice = scan.nextInt();
    }
    // TODO what does EncryptorProgram? you have an Encryptor class and an EncryptorProgram class? refactor names!
    EncryptorProgram encryptor = new EncryptorProgram();
    // TODO what "finishMessage" is? I'm supposed to understand what this variable is just by it's name. refactor name!
    String finishMessage = encryptor.startEncryption(userChoice);
    System.out.println(finishMessage);
  }
}
