package utils;

import org.junit.Assert;

public class StringUtils {
    public static String deleteSomeSymbols(String line, int numberOfSymbols){
        if (line.length() <= numberOfSymbols){
            Assert.fail("Line is too short");
        }
        return line.substring(0, line.length() - numberOfSymbols);
    }
}
