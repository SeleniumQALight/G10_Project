package bdd.stepDefinitions;

import Pages.PrivatPage;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PrivatMainPageStepDefinitions {
    protected WebDriverHelper webDriverHelper;
    PrivatPage privatMainPage;

    public PrivatMainPageStepDefinitions(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        privatMainPage = new PrivatPage(webDriverHelper.getWebDriver());
    }

    @When("I open the Privat main page in the browser")
    public void i_open_the_privat_main_page_in_the_browser() {
        privatMainPage.openPrivateMainPage();

    }


    @And("I click currency rates button and save rates for {string} from UI")
    public void iClickCurrencyRatesButtonAndSaveRatesForFromUI(String currency) {
        privatMainPage.clickOnButtonExchangeRate();
        privatMainPage.saveRatesForCurrencyFromUI(currency);
    }

    @Then("I compare rates from API and UI to be equal")
    public void iCompareRatesFromAPIAndUIToBeEqual() {
        Assert.assertEquals("Rates from API and UI are not equal", TestData.CURRENCY_RATE_API, TestData.CURRENCY_RATE_UI);
    }
}
