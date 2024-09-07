package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestWithExcel extends BaseTest {
    private Logger logger = Logger.getLogger(getClass());
    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_validationMessagesTest(String userName, String email, String password, String expectedMessage){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessage)
        ;
    }

    public Collection parametersForValidationMessagesTest() throws IOException {
        String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile);
        logger.info("Sheet name: " + sheetName);
        logger.info("Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }

}
