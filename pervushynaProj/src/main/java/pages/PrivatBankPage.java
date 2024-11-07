package pages;

import data.TestDataPrivat;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivatBankPage {

    private final WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    TestDataPrivat testDataPrivat;

    @FindBy(xpath = "//a[@class='logo-img']")
    private WebElement privatBankLogo;

    @FindBy(xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    private WebElement currencyRatesButton;

    @FindBy(xpath = "//div[@class='widget-container']//div[@class='exchange-rates']")
    private WebElement currencyRatesModalWindow;

//    параметризований локатор для валют
    String currencyRateLocator = "//td[@id='%s_%s']";

    WebDriverWait webDriverWait10;
    private WebDriver driver;

    public PrivatBankPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void openPrivatBankPage() {
        webDriver.get("https://privatbank.ua/");
        Assert.assertTrue("Privat Bank page was not opened", isElementDisplayed(privatBankLogo));
        logger.info("I open Privat Bank page");
    }

    public PrivatBankPage clickOnButtonOnHeader(String buttonName) {
        clickOnElement(currencyRatesButton);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        Assert.assertTrue("Currency rates modal window was not opened", isElementDisplayed(currencyRatesModalWindow));
        return this;
    }

    public void getSaveExchangeRates(String currency) {
        WebElement buyRate = webDriver.findElement(By.xpath(String.format(currencyRateLocator, currency.toUpperCase(), "buy")));
        WebElement saleRate = webDriver.findElement(By.xpath(String.format(currencyRateLocator, currency.toUpperCase(), "sell")));

        TestDataPrivat.uiBuyRate = Double.parseDouble(buyRate.getText());
        TestDataPrivat.uiSaleRate = Double.parseDouble(saleRate.getText());

        logger.info("UI buy rate is " + TestDataPrivat.uiBuyRate);
        logger.info("UI sale rate is " + TestDataPrivat.uiSaleRate);
    }

    public void compareExchangeRates() {
        if (TestDataPrivat.apiBuyRate == TestDataPrivat.uiBuyRate && TestDataPrivat.apiSaleRate == TestDataPrivat.uiSaleRate) {
            System.out.println("Курси збігаються: Buy Rate = " + TestDataPrivat.apiBuyRate + ", Sale Rate = " + TestDataPrivat.apiSaleRate);
        } else {
            System.out.println("Різниця в курсах: API Buy = " + TestDataPrivat.apiBuyRate + ", UI Buy = " + TestDataPrivat.uiBuyRate +
                    ", API Sale = " + TestDataPrivat.apiSaleRate + ", UI Sale = " + TestDataPrivat.uiSaleRate);
        }
    }


    private boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementsName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementsName(webElement) + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    private String getElementsName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return elementName;
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

}
