package data;

import utils.ConfigProvider;

import java.util.HashMap;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String INVALID_LOGIN_UI = "andriy";
    public static final String INVALID_PASSWORD_UI = "123456";

    public static final String VALID_LOGIN_API = "andriyqa";
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static HashMap<String, Double> CURRENCY_RATE_API = new HashMap<>();
    public static HashMap<String, Double> CURRENCY_RATE_UI = new HashMap<>();

}
