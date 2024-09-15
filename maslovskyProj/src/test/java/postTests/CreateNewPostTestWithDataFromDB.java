package postTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithDataFromDB extends BaseTest {
    protected String mainPartPostTitleName = "HW7_my_post_";
    protected String datePartPostTitleName = Utils.getDateAndTimeFormatted();
    protected String finalTitleName;
    protected String finalPostBody;

    private Logger logger = Logger.getLogger(getClass());
    private Database mySQLDataBase;
    protected String userName = "newqaauto";
    protected String userPassword;

    protected void preconditionGetUserPasswordFromDB() throws SQLException, ClassNotFoundException {
        mySQLDataBase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");
        userPassword = DB_Util_seleniumUsers.getPassForLogin(userName);
    }

    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void HW7_createNewPost
            (String postTitle, String postBody, String dropdown, String checkBox, String creationStatus, String uniqueStatus)
            throws SQLException, ClassNotFoundException {
        preconditionGetUserPasswordFromDB();
        finalTitleName = String.format(postTitle, mainPartPostTitleName, datePartPostTitleName);
        finalPostBody = String.format(postBody, datePartPostTitleName);
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLoginAndContinue(userName)
                .enterTextIntoInputPasswordAndContinue(userPassword)
                .clickOnButtonSighIn()
                .getHomePage()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(finalTitleName)
                .enterTextIntoTextAreaBody(finalPostBody)
                .setCheckBoxStatus(checkBox)
                .selectValueInDropdownAccess(dropdown)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(creationStatus)
                .checkIsUniqueTextInPostDisplayed(uniqueStatus)
                .checkTextThisPostWasWrittenIsVisible(dropdown)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(finalTitleName, 1)
                ;
    }

    public Collection parametersForCreateNewPostTest() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile);
        logger.info("sheetName: " + sheetName);
        logger.info(" skipFirstRow: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(mainPartPostTitleName);
    }

}
