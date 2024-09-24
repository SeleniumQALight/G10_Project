package postTests;

import baseTest.BaseTest;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.DB_Util_seleniumUsers;
import utils.Database;
import utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class CreatePostWithExcelAndDB extends BaseTest {
    private Logger logger = Logger.getLogger(getClass());





    @Test
    public void createPostWithExcelAndDB() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();

        final String LOGIN = "newqaauto";
        String PASS = db_util_seleniumUsers.getPassForLogin(LOGIN);

    }
}
