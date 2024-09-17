package database;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DB_Util_seleniumTable;
import utils.Database;
import utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseTest {
    private Database mySQL_DataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySQL_DataBase() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");
    }

    @After
    public void tearDownMySQLDataBase() throws SQLException {
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G10_DL";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 658");
        logger.info("Data from seleniumTable: " + dataFromSeleniumTable);
        logger.info("Data from seleniumTable: " + dataFromSeleniumTable.get(0).get("id"));
        logger.info(dataFromSeleniumTable.size());


        dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfInsertedRows = mySQL_DataBase.changeTable("INSERT INTO seleniumTable VALUES('13','%s','%s')",
                LOGIN, "123456");
        Assert.assertEquals("Number of inserted rows", 1, numberOfInsertedRows);

        dataFromSeleniumTable = mySQL_DataBase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info("Data from seleniumTable: " + dataFromSeleniumTable);

        int numberOfDeletedRows = mySQL_DataBase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals("Number of deleted rows", 1, numberOfDeletedRows);

        logger.info("----------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G9_taras_r"));
    }
}
