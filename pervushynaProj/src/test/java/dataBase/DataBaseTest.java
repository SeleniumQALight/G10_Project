package dataBase;

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
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySQLDataBase() throws SQLException, ClassNotFoundException {
       mySQLDataBase = MySQL_Database.getDataBase();
         logger.info("MySQL database is connected");
    }

    @After
    public void quitMySQLDataBase() throws SQLException {
        mySQLDataBase.quit();
        logger.info("MySQL database is disconnected");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "kait";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 16");
            logger.info(dataFromSeleniumTable);
            logger.info(dataFromSeleniumTable.size());

            dataFromSeleniumTable = mySQLDataBase.
                    selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals("", dataFromSeleniumTable.size(), 0);


        int numberOfIsertedRows = mySQLDataBase.
                changeTable("INSERT INTO seleniumTable VALUES ('13', '%s', '%s')", LOGIN, "12345");

        Assert.assertEquals("Number of inserted rows", 1, numberOfIsertedRows);

        dataFromSeleniumTable = mySQLDataBase.
                selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable);

        int numberOfDeletedRows = mySQLDataBase.
                changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals("Number of deleted rows", 1, numberOfDeletedRows);


        logger.info("-----------------");

        DB_Util_seleniumTable bdUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(bdUtilSeleniumTable.getPassForLogin("G9_taras_r"));

    }


}
