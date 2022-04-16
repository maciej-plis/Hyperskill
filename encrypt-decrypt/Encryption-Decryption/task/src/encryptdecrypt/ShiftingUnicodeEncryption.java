package encryptdecrypt;

public class ShiftingUnicodeEncryption implements EncryptingAlgorithm {

    private final int key;

    public ShiftingUnicodeEncryption(int key) {
        this.key = key;
    }

    private char shiftLetter(char letter, int key) {
        System.out.println((int)letter+"  "+(letter+key));
        return (char)(letter+key);
    }

    private String shiftText(String text, int key) {
        StringBuilder shifted = new StringBuilder();

        for(char letter : text.toCharArray()) {
            shifted.append(shiftLetter(letter, key));
        }

        return shifted.toString();
    }

    @Override
    public String encrypt(String toEncrypt) {
        return shiftText(toEncrypt, key);
    }

    @Override
    public String decrypt(String toDecrypt) {
        return shiftText(toDecrypt, -key);
    }
}
