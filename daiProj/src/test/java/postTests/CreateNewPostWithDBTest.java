package postTests;

import baseBase.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithDBTest extends BaseTest {
    private String newLogin = "newqaauto";
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
    @Parameters(method = "parametersForCreateNewPostFromExcel")
    public void TC025_createNewPostWithDB(String title, String body, String dropdown, String checkbox
            , String expectedMessage, String isPostUnique) throws SQLException, ClassNotFoundException {


        DB_Util_seleniumUsers dbSeleniumUsers = new DB_Util_seleniumUsers();
        String password = dbSeleniumUsers.getPasswordFromDB(newLogin);

        String uniquePostTitle = String.format(title, "dai", Utils.getDateAndTimeFormatted());


        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(newLogin);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectToHomePage();
        pageProvider.getHomePage().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(uniquePostTitle)
                .enterTextIntoInputBody(body)
                .selectValueInDropdownAccess(dropdown)
                .setCheckBoxState(checkbox)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkIsPostUniqueDisplayed()
                .checkIsPostUniqueText(isPostUnique)
                .checkTextThisPostWasWrittenIsVisible(dropdown)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(uniquePostTitle, 1)
                .deletePostsTillPresent(uniquePostTitle);
    }


    public Collection<Object[]> parametersForCreateNewPostFromExcel() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info(" sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}



