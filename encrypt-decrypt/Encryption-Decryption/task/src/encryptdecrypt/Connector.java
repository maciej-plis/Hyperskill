package encryptdecrypt;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Connector {

    private static Scanner consoleScanner = new Scanner(System.in);

    public static String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch(Exception e) {
            System.out.println(fileName);
            throw new IllegalArgumentException();
        }
    }

    public static void writeToFile(String text, String fileName) {
        try(FileOutputStream outputStream = new FileOutputStream(fileName);) {
            byte[] textToBytes = text.getBytes();
            outputStream.write(textToBytes);
        } catch(Exception e) {
            System.out.println(fileName);
            throw new IllegalArgumentException();
        }
    }

    public static String readConsole() {
        return consoleScanner.nextLine();
    }

    public static void writeToConsole(String text) {
        System.out.println(text);
    }
}
