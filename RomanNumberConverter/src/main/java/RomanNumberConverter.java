import java.util.Arrays;

public class RomanNumberConverter {

    private final int MIN_NUMBER = 0;
    private final int MAX_NUMBER = 1000;

    private final String[] romanNumbers = new String[] {"M", "D", "C", "L", "X", "V", "I"};
    private final int[] arabicNumbers = new int[] {1000, 500, 100, 50, 10, 5, 1};

    public String convertArabicToRomanNumber(int arabicNumber) throws IllegalArgumentException {
        StringBuilder romanNumberBuilder = new StringBuilder("");

        if(!isInHandledRange(arabicNumber)) {
            String message = String.format("Program converts only numbers in range %d-%d", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(message);
        }

        for(int i = 0; i < arabicNumbers.length; i++) {
            int firstDigit = getFirstDigit(arabicNumber);
            int result = arabicNumber / arabicNumbers[i];

            if(firstDigit == 9) {
                String toAppend = "" + romanNumbers[i+2] + romanNumbers[i];
                romanNumberBuilder.append(toAppend);
                arabicNumber = removeFirstDigit(arabicNumber);
            } else if(result == 4) {
                String toAppend = "" + romanNumbers[i] + romanNumbers[i-1];
                romanNumberBuilder.append(toAppend);
                arabicNumber %= arabicNumbers[i];
            } else {
                String toAppend = romanNumbers[i].repeat(result);
                romanNumberBuilder.append(toAppend);
                arabicNumber %= arabicNumbers[i];
            }
        }

        return romanNumberBuilder.toString();
    }

    private boolean isInHandledRange(int arabicNumber) {
        boolean isInRange = arabicNumber >= MIN_NUMBER && arabicNumber <= MAX_NUMBER;
        return isInRange ? true : false;
    }

    private int getFirstDigit(int number) {
        while(number > 10) {
            number /= 10;
        }
        return number;
    }

    private int removeFirstDigit(int number) {
        return number % (int) Math.pow(10, (int) Math.log10(number));
    }

    public int convertRomanToArabicNumber(String romanNumber) throws IllegalArgumentException {
        int result = 0;
        int lastValue = 0;

        if(!isInHandledRange(romanNumber)) {
            String message = String.format("Program converts only numbers in range %d-%d", MIN_NUMBER, MAX_NUMBER);
            throw new IllegalArgumentException(message);
        }

        if(!isValidRomanNumber(romanNumber)) {
            throw new IllegalArgumentException("Cannot Convert Invalid Roman Number");
        }

        for(int i = 0; i < romanNumber.length(); i++) {
            String symbol = romanNumber.substring(i, i+1);
            int index = Arrays.asList(romanNumbers).indexOf(symbol);

            if(index == -1) {
                throw new IllegalArgumentException("Roman Number Contains Invalid Symbols");
            }

            int value = arabicNumbers[index];

            if(lastValue < value) {
                result -= lastValue;
                value = value - lastValue;
            }

            lastValue = value;
            result += value;
        }

        return result;
    }

    private boolean isInHandledRange(String number) {
        if(number.length() > 1 && number.charAt(0) == 'M') {
            return false;
        }

        return true; // TODO: Improve Roman Number Range Validator
    }

    private boolean isValidRomanNumber(String romanNumber) {
        return true; // TODO: Create Roman Number Validation
    }

}
