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
import java.util.Map;

public class DataBaseTest {
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void SetMySQLDataBase() throws SQLException, ClassNotFoundException {
        mySQLDataBase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");
    }

    @After
    public void tearDownMySQLDataBase() throws SQLException {
        mySQLDataBase.quit();
        logger.info("MySQL database connection was closed");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G10_maslovsky_a";
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable where login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfInsertedRows =
                mySQLDataBase.changeTable("INSERT INTO seleniumTable VALUES('123','%s','%s')", LOGIN, "321");
        Assert.assertEquals("Number of inserted rows", 1, numberOfInsertedRows);

        dataFromSeleniumTable =
                mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable where login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable);

        int numberOfDeletedRows =
                mySQLDataBase.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals(1, numberOfDeletedRows);

        logger.info("-----------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G9_taras_r"));

    }

}
