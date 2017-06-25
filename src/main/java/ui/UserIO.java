package ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Travis Rogers
 */
public interface UserIO {

    void printHeader(String message);
    
    void print(String prompt);

    void println(String prompt);
            
    BigDecimal readBigDecimal2(String prompt);

    LocalDate readDate(String prompt);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
}
