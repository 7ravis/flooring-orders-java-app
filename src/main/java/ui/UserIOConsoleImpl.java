package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Travis Rogers
 */

public class UserIOConsoleImpl implements UserIO {
    
    @Override
    public BigDecimal readBigDecimal2(String prompt) {
        boolean isValid;
        BigDecimal money = null;
        do {
            isValid = true;
            try {
            money = new BigDecimal(readString(prompt)).setScale(2, RoundingMode.HALF_UP);
            } catch (NumberFormatException e) {
                println("ERROR: input must match one of the following numerical formats: 2.00 or 2");
                isValid = false;
            }
        } while (isValid == false);
        return money;
    }
    @Override
    public LocalDate readDate(String prompt) {
        boolean isValid;
        String dateString = "";
        LocalDate date = null;
        do {
            isValid = true;
            dateString = readString(prompt);
            try {
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                println("ERROR: date must use the following format: \"yyyy-mm-dd\"");
                isValid = false;
            }
        } while (isValid == false);
        return date;
    }
    @Override
    public void println(String prompt) {
        System.out.println(prompt);
    }
    @Override
    public void print(String prompt) {
        System.out.print(prompt);
    }
    @Override
    public void printHeader(String message) {
        System.out.println("\n===" + message.toUpperCase() + "===");
    }
    @Override
    public double readDouble(String prompt) {
        return Double.parseDouble(readPointNumber(prompt));
    }
    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid;
        double input;
        do {
            isValid = true;
            input = Double.parseDouble(readPointNumber(prompt));
            if (input < min || input > max) {
                System.out.println("\nError: input is out of range.");
                isValid = false;
            }
        } while (!isValid);
        return input;
    }
    @Override
    public float readFloat(String prompt) {
        return Float.parseFloat(readPointNumber(prompt));
    }
    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean isValid;
        float input;
        do {
            isValid = true;
            input = Float.parseFloat(readPointNumber(prompt));
            if (input < min || input > max) {
                System.out.println("\nError: input is out of range.");
                isValid = false;
            }
        } while (!isValid);
        return input;
    }
    @Override
    public int readInt(String prompt) {
        return Integer.parseInt(readWholeNumber(prompt));
    }
    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isValid;
        int input;
        do {
            isValid = true;
            input = Integer.parseInt(readWholeNumber(prompt));
            if (input < min || input > max) {
                System.out.println("\nError: input is out of range.");
                isValid = false;
            }
        } while (!isValid);
        return input;
    }
    @Override
    public long readLong(String prompt) {
        return Long.parseLong(readWholeNumber(prompt));
    }
    @Override
    public long readLong(String prompt, long min, long max) {
        String inputString;
        long input = 0;
        boolean isValid;
        do {
            input = Long.parseLong(readWholeNumber(prompt));
            isValid = (input >= min && input <= max);
        } while (!isValid);
        return input;
    }
    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }
    
    private String readPointNumber(String prompt) {
        boolean isValid;
        String input;
        do {
            input = readString(prompt);
            isValid = isNumber(input);
        } while (isValid == false);
        return input;
    }
    private String readWholeNumber(String prompt) {
        boolean isValid;
        String input;
        do {
            isValid = true;
            input = readString(prompt);
            if (!isNumber(input)) {
                isValid = false;
            } else if (hasDecimal(input)) {
                System.out.println("\nError: input contains an invalid character: '.'");
                isValid = false;
            }
        } while (!isValid);
        return input;
    }
    private boolean isNumber(String input) {  //keep non-interface methods private, so they add functionality internally, but don't deviate from the interface
        int onlyOneDecimal = 0;
        if (input.length() == 0) {
            print("Error: no input.");
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (i == 0 && input.charAt(i) == '-') {
            } else if (input.charAt(i) == '.' && onlyOneDecimal < 1) {
                onlyOneDecimal++;
            } else {
                if (!Character.isDigit(input.charAt(i))) {
                    print("\nError: input contains non-numerical characters.");
                    return false;
                }
            }
        }
        return true;
    }
    private boolean hasDecimal(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }
}