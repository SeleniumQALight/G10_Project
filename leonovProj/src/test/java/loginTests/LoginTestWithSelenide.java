package loginTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.ParentPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTestWithSelenide {


    @Test
    public void validLogin() {
        // Should be in LoginPage

        SelenideElement inputUserName = $(By.xpath(".//input[@placeholder='Username']"));
        SelenideElement inputPassword = $(By.xpath(".//input[@placeholder='Password']"));
        SelenideElement buttonSignIn = $(By.xpath(".//button[text()='Sign In']"));
// Should be in HomePage
        SelenideElement buttonMyProfile = $(By.xpath("//img[@alt='My profile']"));
         //$$ - list<SelenideElements>

        WebDriverManager.chromedriver().setup();

        open(ParentPage.baseUrl);
        inputUserName.setValue("qaauto");
        inputPassword.setValue("123456qwerty");
        buttonSignIn.click();

        buttonMyProfile.shouldBe(Condition.visible);

    }
}
