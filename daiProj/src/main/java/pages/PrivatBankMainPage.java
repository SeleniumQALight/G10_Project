package pages;

import api.HWEndpointsAndDTO.PrivatBankEndpoints;
import data.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankMainPage extends CommonActionsWithElements{

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement buttonExchangeRate;
    String currencyLocatorBuy = "%s_buy";
    String currencyLocatorSale = "%s_sell";

    Logger logger = Logger.getLogger(getClass());

    public PrivatBankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPrivatBankMainPage() {
        webDriver.get(PrivatBankEndpoints.BASE_URL_UI);
    }

    public void clickOnButtonExchangeRate() {
        clickOnElement(buttonExchangeRate);
    }

    public void saveRateForCurrencyFromUI(String currency) {
        WebElement currencyBuy = webDriver.findElement(By.id(String.format(currencyLocatorBuy, currency)));
        WebElement currencySale = webDriver.findElement(By.id(String.format(currencyLocatorSale, currency)));
        Double currencyBuyRate = Double.valueOf(getTextFromElement(currencyBuy));
        Double currencySellRate = Double.valueOf(getTextFromElement(currencySale));

        TestData.CURRENCY_RATE_UI.put("buy", currencyBuyRate);
        TestData.CURRENCY_RATE_UI.put("sell", currencySellRate);
        logger.info("CURRENCY RATE UI: " + TestData.CURRENCY_RATE_UI);
    }
}
