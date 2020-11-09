import java.util.Scanner;

public class encryptor {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String UserChoise = "";

    System.out.println(
        "    ______                            __            \n"
            + "   / ____/___  ____________  ______  / /_____  _____\n"
            + "  / __/ / __ \\/ ___/ ___/ / / / __ \\/ __/ __ \\/ ___/\n"
            + " / /___/ / / / /__/ /  / /_/ / /_/ / /_/ /_/ / /    \n"
            + "/_____/_/ /_/\\___/_/   \\__, / .___/\\__/\\____/_/     \n"
            + "                      /____/_/                      ");
    System.out.println(
        "welcome.\nIf you want to ENCODING something - just press '1' to continue,\n"
            + "If you want to DECODING someshing, press '2'.");

    UserChoise = scan.nextLine();
    while (!(UserChoise.equals("1") || UserChoise.equals("2"))) {
      System.out.println("Sorry, i d'ont understand you, Please try again.");
      UserChoise = scan.nextLine();
    }

    switch (UserChoise) {
      case ("1"):
        System.out.println(
            "Please enter the path (absulute or relative)\n"
                + "of file you want to encrypt\n"
                + "(e.g.: 'file.txt')");
        String originalFilePath = scan.nextLine();
        IoFile file = new IoFile(originalFilePath);
        String textToEncrypt = file.readFromFile();

        break;
      case ("2"):
        //        do Staff
        break;
    }
  }
}
