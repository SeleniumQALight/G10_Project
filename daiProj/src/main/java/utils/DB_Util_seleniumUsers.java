package utils;

import java.sql.*;


import org.apache.log4j.Logger;


public class DB_Util_seleniumUsers {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPasswordFromDB(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String password = mySQL_DataBase.selectValue(
                String.format("select passWord from seleniumUsers where login = '%s'", login));

        logger.info("Password retrieved for user " + login + ": " + password);


        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return password;
        }
        }











