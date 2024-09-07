package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private WebDriver webDriver;
    private Logger logger;

    public Utils(WebDriver webDriver, Logger logger) {
        this.webDriver = webDriver;
        this.logger = logger;
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     */
    public static String getDateAndTimeFormatted(){
        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime In Format yyyyMMddHHmmss
     */
    public static String getDateAndTimeFormattedOnlyNumbers(){
        return getDateAndTime("yyyyMMddHHmmss");
    }

    /**
     * Method returned SystemDateAndTime
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

}
