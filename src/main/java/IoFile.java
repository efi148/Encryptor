import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// TODO IO operations/utils are not an object (you have a c'tor here) but a class filled with various util methods. refactor!
public class IoFile {
  private final String sourceFilePath;
  private final String absoluteFilePath;

  public IoFile(String sourceFilePath) {
    this.sourceFilePath = sourceFilePath;
    File file = new File(sourceFilePath);
    this.absoluteFilePath = file.getAbsolutePath();
  }

  public String getAbsoluteFilePath() {
    return absoluteFilePath;
  }

  public String readFromFile() throws IOException {
    if (sourceFilePath.length() == 0) {
      throw new IOException("input is empty!");
    }
    File file = new File(sourceFilePath);
    FileInputStream fis = new FileInputStream(file);
    if (!file.canRead()) {
      throw new IOException("Read permission denied");
    } else if (file.length() == 0) {
      throw new IOException("Oops, the file is empty");
    }
    byte[] data = new byte[(int) file.length()];
    fis.read(data);
    fis.close();
    return new String(data, StandardCharsets.UTF_8);
  }

  public void writeToFile(String filePath, String dataToWrite) throws IOException {
    FileWriter fileWriter = new FileWriter(filePath);
    fileWriter.write(dataToWrite);
    fileWriter.close();
  }
}
