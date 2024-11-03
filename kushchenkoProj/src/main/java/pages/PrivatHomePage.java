package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatHomePage extends ParentPage {

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRateButton;

    private String buyCurrencyRate = "";
    private String sellCurrencyRate = "";

    public PrivatHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatHomePage getPrivatHomePage() {
        return new PrivatHomePage(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    public void openExchangeRatePopup(){
        clickOnElement(exchangeRateButton);
    }

    public void openPrivatbankHomePage() {
        webDriver.get(baseUrlPrivatbank);
        logger.info("Privatbank home page was opened " + baseUrlPrivatbank);
    }

    public void getCurrencyExchangeRates(String currency) {

    }
}
