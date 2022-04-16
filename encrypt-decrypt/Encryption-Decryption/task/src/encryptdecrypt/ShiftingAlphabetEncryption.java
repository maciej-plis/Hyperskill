package encryptdecrypt;

public class ShiftingAlphabetEncryption implements EncryptingAlgorithm {

    private final int key;

    public ShiftingAlphabetEncryption(int key) {
        this.key = key;
    }

    private boolean isLowCaseLetter(char letter) {
        return (letter >= 97 && letter <= 122);
    }

    private char shiftLetter(char letter, int key) {
        int number = ((letter-97)+key);
        number %= 26;
        if(number < 0)
            number = 26+number;
        return (char)(number+97);
    }

    private String shiftText(String text, int key) {
        StringBuilder shifted = new StringBuilder();

        for(char letter : text.toCharArray()) {
            shifted.append( isLowCaseLetter(letter) ? shiftLetter(letter, key) : letter );
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
