package utils;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util_seleniumUsers {
    private static Database mySQL_DataBase;
    static Logger logger = Logger.getLogger(DB_Util_seleniumUsers.class);

    public static String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("select password from seleniumUsers where login = '%s'", login)
                                                );
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }
}
