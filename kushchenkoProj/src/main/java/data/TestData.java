package data;

import utils.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String INVALID_LOGIN_UI = "1qaauto";
    public static final String INVALID_PASSWORD_UI = "123456";
}
