package encryptdecrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cryptographer {

    private final String[] allowedParameters = {"-key", "-mode", "-in", "-alg", "-out", "-data"};
    private EncryptingAlgorithm algorithm;
    private Map<String, String> parameters;

    public Cryptographer(String[] args) {
        this.parameters = this.mapArguments(args);
        this.algorithm = this.chooseAlgorithm();
    }

    public void run() {
        String text = readText();

        String processedText = this.process(text);

        this.write(processedText);
    }

    private String readText() {
        String text = this.parameters.get("-data");

        if(text == null) {
            String fileName = this.parameters.get("-in");
            text = Connector.readFile(fileName);
        }
        if(text == null) {
            return Connector.readConsole();
        }

        return text;
    }

    private String process(String text) {
        String mode = this.parameters.get("-mode");

        if(mode.equals("dec")) {
            return decrypt(text);
        } else {
            return encrypt(text);
        }
    }

    private void write(String toWrite) {
        String fileName = this.parameters.get("-out");

        if(fileName != null) {
            Connector.writeToFile(toWrite, fileName);
        } else {
            Connector.writeToConsole(toWrite);
        }
    }

    private Map<String, String> mapArguments(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));

        for(String parameter : this.allowedParameters) {

            if(arguments.contains(parameter)) {
                map.put(parameter, arguments.get(arguments.indexOf(parameter)+1));
            } else {
                map.put(parameter, null);
            }

        }

        return map;
    }

    private EncryptingAlgorithm chooseAlgorithm() {
        int key = Integer.parseInt(parameters.get("-key"));

        if(this.parameters.get("-alg").equals("unicode")) {
            return new ShiftingUnicodeEncryption(key);
        }

        return new ShiftingAlphabetEncryption(key);
    }

    private String encrypt(String toEncrypt) {
        return algorithm.encrypt(toEncrypt);
    }

    private String decrypt(String toDecrypt) {
        return algorithm.decrypt(toDecrypt);
    }
}
