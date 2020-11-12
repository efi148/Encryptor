public class Encryption {

  public int generateEncryptKey() {
    return (int) (Math.random() * 100 + 1);
  }

  public String encryptData(String data, int key) {
    String newStr = "";
    for (int i = 0; i < data.length(); i++) {
      char Char = data.charAt(i);
      String currentChar = Integer.toHexString(Char);
      int hexVal = (Integer.parseInt(currentChar, 16) + key);
      newStr = newStr.concat(String.valueOf((char) hexVal));
    }
    return newStr;
  }
}
