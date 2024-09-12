package dataBase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DB_Util_seleniumTable;
import utils.Database;
import utils.MySQL_Database;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseTest {
    private Database mySQLDatabase;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setMySQLDatabase() throws SQLException, ClassNotFoundException {
        mySQLDatabase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");

    }

    @After
    public void tearDownMySQLDatabase() throws SQLException {
        mySQLDatabase.quit();
        logger.info("MySQL database connection was closed");

    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G10_maria_dai";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id = 658");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());


        dataFromSeleniumTable = mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());


        int numberOfInsertedRows = mySQLDatabase.changeTable("INSERT INTO seleniumTable VALUES('156', '%s', '%s')", LOGIN, "12345");
        Assert.assertEquals("Number of inserted rows ", 1, numberOfInsertedRows);


        dataFromSeleniumTable = mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable);

        int numberOfDeletedRows = mySQLDatabase.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);

        Assert.assertEquals(1, numberOfDeletedRows);

        logger.info("-------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G9_taras_r"));

    }

}
