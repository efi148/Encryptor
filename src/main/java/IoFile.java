import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoFile {
  private String sourceFilePath;
  private String dataToWrite;

  public IoFile(String sourceFilePath) {
    this.sourceFilePath = sourceFilePath;
  }

  public String readFromFile() {
    /* if (sourceFilePath.length() == 0) {
      try {
        throw new encryptorException("the input is empty!");
      } catch (encryptorException e) {
        e.getMessage();
      }
    } else if (!sourceFilePath.contains(".")) {
      try {
        throw new encryptorException("Filename does not contain extension!");
      } catch (encryptorException e) {
        e.getMessage();
      }
    }*/
    File file = new File(sourceFilePath);
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
    }
    /*try {
      if (file.length() == 0) {
        throw new encryptorException("the file is empty!");
      }
    } catch (encryptorException e) {
      e.getMessage();
    }*/
    byte[] data = new byte[(int) file.length()];
    try {
      fis.read(data);
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new String(data, StandardCharsets.UTF_8);
  }

  public void writeToFile() {
    try {
      FileWriter fileWriter = new FileWriter(sourceFilePath);
      fileWriter.write(dataToWrite);
      fileWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
