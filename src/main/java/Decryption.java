public class Decryption {
    public String decryptData(String data, int key) {
        String newStr = "";
        for (int i = 0; i < data.length(); i++) {
            char Char = data.charAt(i);
            String currentChar = Integer.toHexString(Char);
            int hexVal = (Integer.parseInt(currentChar, 16) - key);
            newStr = newStr.concat(String.valueOf((char) hexVal));
        }
        return newStr;
    }
}
