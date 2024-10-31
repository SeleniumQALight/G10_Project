package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankHomePage extends ParentPage {
    @FindBy(xpath = "//li[@class='desctop exchangeRate']/button")
    private WebElement buttonCurrencyRate;

    private String buyCurrencyRate = "//span[normalize-space(text())='%s']/ancestor::tr[1]/td[contains(@id,'buy')][1]";
    private String sellCurrencyRate = "//span[normalize-space(text())='%s']/ancestor::tr[1]/td[contains(@id,'sell')][1]";

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatBankHomePage getPrivatBankHomePage() {
        return new PrivatBankHomePage(webDriver);
    }

    public void clickOnCurrencyRateButton() {
        clickOnElement(buttonCurrencyRate);
    }

    public String getBuyCurrencyRate(String currency) {
        return webDriver.findElement(By.xpath(buyCurrencyRate.formatted(currency))).getText();
    }

    public String getSellCurrencyRate(String currency) {
        return webDriver.findElement(By.xpath(sellCurrencyRate.formatted(currency))).getText();
    }

    @Step
    public void openPrivatBankHomePage() {
        webDriver.get(privatBankUrl);
        logger.info("Login page was opened " + privatBankUrl);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }
}
