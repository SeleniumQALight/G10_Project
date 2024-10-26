package data;


import static utils.ConfigProvider.*;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String INVALID_LOGIN_UI = "invalidLogin";
    public static final String INVALID_PASSWORD_UI = "invalidPassword";
    public static final String invalidCredentialsMessage = "Invalid username/password.";

    public static final String VALID_LOGIN_API = "denysleonov";
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static final String VALID_LOGIN_BOOK = "denystest1";
    public static final String VALID_PASSWORD_BOOK = "Qwerty123456!";

}
