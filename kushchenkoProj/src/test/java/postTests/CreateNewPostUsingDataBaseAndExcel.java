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
public class CreateNewPostUsingDataBaseAndExcel extends BaseTest {
    private String login = "newqaauto";
    private Logger logger = Logger.getLogger(getClass());
    String POST_TITLE;

    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void TR004_createNewPost(String title, String body, String dropdown, String checkbox, String successMessage,
                                    String isPostUnique) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();
        String password = db_util_seleniumUsers.getPassForLogin(login);
        POST_TITLE = String.format(title, "kushchenko", Utils.getDateAndTimeFormatted());

        pageProvider.getLoginPage()
                .openLoginPageAndLoginWithValidCreds(login, password)
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody(body)
                .setCheckBoxPostUniqueTrue(checkbox)
                .selectVaueInDropDownAccess(dropdown)
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage)
//                .checkIsPostUnique()
                .checkTextThisPostWasWrittenIsVisible(dropdown)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    public Collection parametersForCreateNewPostTest() throws IOException {
        String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile);
        logger.info("Sheet name: " + sheetName);
        logger.info("Skip first row: " + skipFirstRow);
        logger.info("test: " + new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData());
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }
}
