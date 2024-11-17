package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.PrivatBankMainPage;

public class PrivatBankPageStepsDefinitions {
    protected WebDriverHelper webDriverHelper;
    PrivatBankMainPage privatBankMainPage;

    public PrivatBankPageStepsDefinitions(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        privatBankMainPage = new PrivatBankMainPage(webDriverHelper.getWebDriver());
    }

    @When("I open the PrivatBank main page in browser")
    public void i_open_the_privat_bank_main_pade_in_browser() {
        privatBankMainPage.openPrivatBankMainPage();
    }

    @And("I click on currency rates button and I save rates for {string} from UI")
    public void i_click_on_currency_rates_button_and_save_rates_for_currency_from_ui(String currency) {
        privatBankMainPage.clickOnButtonExchangeRate();
        privatBankMainPage.saveRateForCurrencyFromUI(currency);
    }

    @Then("I compare rates from API with UI")
    public void i_compare_rates_from_api_with_ui() {
        Assert.assertEquals("Rates from API and UI are not equal", TestData.CURRENCY_RATE_API, TestData.CURRENCY_RATE_UI);
    }

}
