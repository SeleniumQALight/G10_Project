package utils;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DB_Util_seleniumUsers {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(String.format("SELECT password FROM seleniumUsers WHERE login = '%s'",login));

        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");

        return pass;
    }

    public ArrayList<Map<String,String>> getTable() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumUsers WHERE login = 'newqaauto'");
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");

        return dataFromSeleniumTable;
    }
}
