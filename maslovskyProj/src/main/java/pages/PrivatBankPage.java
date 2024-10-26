package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static data.PrivatBankCurrencyExchangeData.*;

public class PrivatBankPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li//button[@type='button']")
    private WebElement currencyPopUp;

    @FindBy(xpath = "//button[@data-id='type-course']")
    private WebElement rateTypeDropdown;

    @FindBy(xpath = "(//ul//li)[2]")
    private WebElement rateForCards;

//    private String currencyBuy = "//td[@id='%s_buy']";
    private String currencyBuy = "//div[@class='course_type']//tr[td/span[text()='%s']]/td[2]";
//    private String currencySell = "//td[@id='%s_sell']";
    private String currencySell = "//div[@class='course_type']//tr[td/span[text()='%s']]/td[3]";

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public PrivatBankPage openPrivatBankPage() {
        webDriver.get(privatBankUrl);
        logger.info("PrivatBank page was opened " + privatBankUrl);
        return this;
    }

    public PrivatBankPage openCurrencyPopUp() {
        clickOnElement(currencyPopUp, "Exchange rates button");
        return this;
    }

    public PrivatBankPage selectRateType() {
        clickOnElement(rateTypeDropdown, "'Rate type' dropdown");
        clickOnElement(rateForCards, "'Rates for cards' option");
        return this;
    }

    public PrivatBankPage readCurrencyRates(String currency) {
        currencyBuyRateUI = returnDoubleFromElementByLocator(String.format(currencyBuy, currency));
        currencySellRateUI = returnDoubleFromElementByLocator(String.format(currencySell, currency));
        logger.info("Exchange rates values were read");
        return this;
    }

}
