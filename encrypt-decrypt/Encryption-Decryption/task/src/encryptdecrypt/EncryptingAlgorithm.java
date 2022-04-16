package encryptdecrypt;

public interface EncryptingAlgorithm {
    String encrypt(String toEncrypt);
    String decrypt(String toDecrypt);
}
