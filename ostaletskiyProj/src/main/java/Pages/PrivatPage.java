package Pages;

import API.PrivatBantDTO.PrivatEndPoints;
import data.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatPage extends CommonActionsWithElements{
    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement buttonExchangeRate;
    String currencyLocatorBuy = "%s_buy";
    String currencyLocatorSell = "%s_sell";

    Logger logger = Logger.getLogger(getClass());

    public PrivatPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPrivateMainPage() {
        webDriver.get(PrivatEndPoints.BASE_URL_UI);
    }


    public void clickOnButtonExchangeRate() {
        clickOnElement(buttonExchangeRate);
    }

    public void saveRatesForCurrencyFromUI(String currency) {
        WebElement currencyBuy = webDriver.findElement(By.id(String.format(currencyLocatorBuy, currency)));
        WebElement currencySell = webDriver.findElement(By.id(String.format(currencyLocatorSell, currency)));
        Double currencyRateBuy = Double.valueOf(getTextFromElement(currencyBuy));
        Double currencyRateSell = Double.valueOf(getTextFromElement(currencySell));

        TestData.CURRENCY_RATE_UI.put("buy", currencyRateBuy);
        TestData.CURRENCY_RATE_UI.put("sell", currencyRateSell);
        logger.info("CURRENCY RATE UI: " + TestData.CURRENCY_RATE_UI);
    }
}
