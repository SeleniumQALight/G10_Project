package postTests;


import baseBase.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostPageDB extends BaseTest {
    //GUID = 1b3b3b3b-1b3b-1b3b-1b3b-1b3b3b3b1b3b
    private String login = "newqaauto";
    private Logger logger = Logger.getLogger(getClass());
    private Database mySQLDatabase;

    @Before
    public void setMySQLDatabase() throws SQLException, ClassNotFoundException {
        mySQLDatabase = libs.MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");

    }



    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void TR003_createNewPost(String title, String body, String dropDownAccess, String postUniqueCheckbox, String successfulMessage, String isPostUnique) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumTable = new DB_Util_seleniumUsers();
        String uniquePostTitle = String.format(title, "Andriy", Utils.getDateAndTimeFormatted());
        String uniqueBody = String.format(body, Utils.getDateAndTimeFormatted());

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
        pageProvider.getLoginPage().enterTextIntoInputPassword(dbUtilSeleniumTable.getPasswordForLogin(login));
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectToHomePage();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(uniquePostTitle)
                .enterTextIntoInputBody(uniqueBody)
                .selectValueInDropDownAccess(dropDownAccess)
                .setCheckBoxState(postUniqueCheckbox)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successfulMessage)
                .checkIsPostUniqueDisplayed()
                .checkIsPostUniqueText(isPostUnique)
                .checkTextThisPostWasWrittenIsVisible(dropDownAccess)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(uniquePostTitle, 1)
                .deletePostsTillPresent(uniquePostTitle);
    }

    public Collection parametersForCreateNewPostTest() throws IOException {
        String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile);
        logger.info("Sheet name: " + sheetName);
        logger.info("Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }
}
