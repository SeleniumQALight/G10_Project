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
    private Database mySQL_DataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySQL_DataBase() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("MySQL_DataBase is connected");
    }

    @After
    public void tearDownMySQL_DataBase() throws SQLException {
        mySQL_DataBase.quit();
        logger.info("MySQL_DataBase is disconnected");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "yuliia_G10";
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("select * from seleniumTable WHERE id=658");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfInsertedRows =
                mySQL_DataBase.changeTable("INSERT INTO seleniumTable VALUES ('822', '%s', '%s')", LOGIN, "123456");
        Assert.assertEquals("Number of inserted rows", 1, numberOfInsertedRows);

        dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable);

        int numberOfDeletedRows =
                mySQL_DataBase.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals("Number of deleted rows", 1, numberOfDeletedRows);

        logger.info("---------");

        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info(db_util_seleniumTable.getPassForLogin("G09_yuliia"));
    }
}
