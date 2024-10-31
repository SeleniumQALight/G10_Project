package qaclub;

import baseBase.BaseTest;
import org.junit.Test;


public class LoginTest extends BaseTest {
    // Стри новий тест по прикладу з тесту TR003_createNewPost
    // Кейс наступний:
    // 1. Відкрити сторінку логіну
    // 2. введи логін "NotRealLogin"
    // 3. введи пароль "NotRealPassword"
    // 4. натисни на кнопку "Login"
    // 5. перевір, що виведеться помилка "Invalid username/password."

    //    додай степ перевірки
//    Бачимо на логін пейджі заголовок H1 текст "Remember Writing?".
//    Якщо немає такого степа - створи його і опиши елемент аналогічно вже існуючим


    //   в оцей тест TR004_invalidLogin
    //   додай степи перевірки в кінець тесту
    //  що інпут логін показаний
    //  що інпут пароль показаний
    //  що кнопка логін показана
    // якщо немає такого степа - створи його
    // якщо такий елемент ще не описаний, опиши елемент аналогічно вже існуючим
    // намагайся використати вже існуючі методи і елементи

    @Test
    public void invalidLoginTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("NotRealLogin")
                .enterTextIntoInputPassword("NotRealPassword")
                .clickOnButtonSignIn()
                .checkTextInAlertMessageInCenter("Invalid username/password.")
                .checkH1HeaderText("Remember Writing?")
                .checkIsLoginInputVisible()
                .checkIsPasswordInputVisible()
                .checkIsLoginButtonVisible();
        ;
    }
}
