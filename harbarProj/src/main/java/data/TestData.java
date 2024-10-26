package data;

import utils.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin"
            , ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "NaQa".toLowerCase();
    public static final String VALID_PASSWORD_API = "qwerty123456789";

    public static final String DEMO_QA_VALID_USERNAME_API = "harbarQa";
    public static final String DEMO_QA_VALID_PASSWORD_API = "Qwerty123$";

}
