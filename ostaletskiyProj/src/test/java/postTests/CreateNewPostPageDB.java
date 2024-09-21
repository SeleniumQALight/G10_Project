package postTests;


import baseBase.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
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
public class CreateNewPostPageDB extends BaseTest {
    //GUID = 1b3b3b3b-1b3b-1b3b-1b3b-1b3b3b3b1b3b
    private String login = "newqaauto";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void TR003_createNewPost(String title, String body, String dropDownAccess, String postUniqueCheckbox, String successfulMessage, String isPostUnique) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumTable = new DB_Util_seleniumUsers();
            String uniqueTitle = String.format(title, "Andriy", Utils.getDateAndTimeFormatted());
        String uniqueBody = String.format(body, Utils.getDateAndTimeFormatted());
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(login)
                .enterTextIntoInputPassword(dbUtilSeleniumTable.getPasswordForLogin(login))
                .clickOnButtonSignIn()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(uniqueTitle)
                .enterTextIntoInputBody(uniqueBody)
                .setCheckBoxState(postUniqueCheckbox)
                .selectValueInDropDownAccess(dropDownAccess)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successfulMessage)
                .checkIsPostUniqueText(isPostUnique)
                .checkTextThisPostWasWrittenIsVisible(dropDownAccess)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(uniqueTitle, 1)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(uniqueTitle);

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
