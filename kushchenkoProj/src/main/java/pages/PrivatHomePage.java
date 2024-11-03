package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatHomePage extends ParentPage {

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRateButton;

    private String buyCurrencyRate = "%s_buy";
    private String sellCurrencyRate = "%s_sell";

    public PrivatHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openExchangeRatePopup(){
        clickOnElement(exchangeRateButton);
    }

    public void openPrivatbankHomePage() {
        webDriver.get(baseUrlPrivatbank);
        logger.info("Privatbank home page was opened " + baseUrlPrivatbank);
    }

    public void getCurrencyExchangeRates(String currency) {
        WebElement currencySale = webDriver.findElement(By.id(String.format(sellCurrencyRate, currency)));
        WebElement currencyBuy = webDriver.findElement(By.id(String.format(buyCurrencyRate, currency)));
        Double currencyRateSale = Double.valueOf(currencySale.getText());
        Double currencyRateBuy = Double.valueOf(currencyBuy.getText());


        TestData.CURRENCY_RATE_UI.put("sale", currencyRateSale);
        TestData.CURRENCY_RATE_UI.put("buy", currencyRateBuy);

        logger.info("Currency Rate from UI " + TestData.CURRENCY_RATE_UI);
    }
}
