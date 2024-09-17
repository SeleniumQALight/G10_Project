package database;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DB_Util_seleniumTable;
import utils.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseTest {
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setMySQLDataBase() throws SQLException, ClassNotFoundException {
        mySQLDataBase = libs.MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");

    }

    @After
    public void tearDownMySQLDataBase() throws SQLException {
        mySQLDataBase.quit();
        logger.info("MySQL database connection was closed");

    }

    @Test
    public void testDataFromDB () throws SQLException, ClassNotFoundException {
        final String login = "G10_ANDRIY_01";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable where id=658");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());


        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + login + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfInsertedRows =
                mySQLDataBase.changeTable("INSERT INTO seleniumTable VALUES ('771','%s','%s')", login, "123456");

        Assert.assertEquals( "Number of inserted rows", 1, numberOfInsertedRows);

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + login + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable);

        int numberOfDeletedRows =
                mySQLDataBase.changeTable("DELETE FROM seleniumTable where login = '%s'", login);
        Assert.assertEquals(1, numberOfDeletedRows);

        logger.info("--------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G9_taras_r"));

    }

}
