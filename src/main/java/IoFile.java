import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoFile {
  private String sourceFilePath;

  public String getAbsuluteFilePath() {
    return absuluteFilePath;
  }

  public void setAbsuluteFilePath(String absuluteFilePath) {
    this.absuluteFilePath = absuluteFilePath;
  }

  private String absuluteFilePath;

  public IoFile(String sourceFilePath) {
    this.sourceFilePath = sourceFilePath;
  }

  public String readFromFile() throws filesException {
    if (sourceFilePath.length() == 0) {
      throw new filesException("input is empty!");
    }
    File file = new File(sourceFilePath);

    this.absuluteFilePath = file.getAbsolutePath();

    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new filesException("file not found!");
    }
    if (!file.canRead()) {
      throw new filesException("Read permission denied");
    } else if (file.length() == 0) {
      throw new filesException("Oops, the file is empty");
    }
    byte[] data = new byte[(int) file.length()];
    try {
      fis.read(data);
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new String(data, StandardCharsets.UTF_8);
  }

  public void writeToFile(String newFilePath, String dataToWrite) {
    try {
      FileWriter fileWriter = new FileWriter(newFilePath);
      fileWriter.write(dataToWrite);
      fileWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
