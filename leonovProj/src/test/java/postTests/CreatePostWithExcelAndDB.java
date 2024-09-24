package postTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.DB_Util_seleniumUsers;
import utils.ExcelSpreadsheetData;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class CreatePostWithExcelAndDB extends BaseTest {
    private Logger logger = Logger.getLogger(getClass());
    String titleForDelete = "";



    @Test
    @Parameters(method = "parametersForCreatePostWithExcelAndDB")
    public void TR007_createPostWithExcelAndDB(String title, String body, String access, String uniqueness
            , String message, String uniquenessMessage) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();

        final String LOGIN = "newqaauto";
        final String PASS = db_util_seleniumUsers.getPassForLogin(LOGIN);
        title = String.format(title, "TR007_leonov", Utils.getDateAndTimeFormatted());
        titleForDelete = title;
        body = String.format(body, " TR003_leonov" + Utils.getDateAndTimeFormatted());

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(PASS);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(title)
                .enterTextIntoInputBody(body)
                .checkboxStateOnPostPage(uniqueness)
                .selectInDropDownAccessByValue(access)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkIsCreatedPostUnique(uniquenessMessage)
                .checkTextThisPostWasWrittenIsVisible(access)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .checkPostWithTitleIsPresent(title, 1);

    }
    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomepageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .deletePostTillPresent(titleForDelete)
        ;
    }


    public Collection parametersForCreatePostWithExcelAndDB() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Reading data from file: " + pathToDataFile + " sheet: " + sheetName);
        logger.info("Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
