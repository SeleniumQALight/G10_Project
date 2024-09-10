package data;


import static utils.ConfigProvider.*;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";

}
