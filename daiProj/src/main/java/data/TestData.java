package data;

import api.HWEndpointsAndDTO.ExchangeRateDto;
import utils.ConfigProvider;

import java.util.HashMap;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";


    public static final String VALID_LOGIN_API = "mdai";
    public static final String VALID_PASSWORD_API = "123456qwerty";


    public static final String VALID_LOGIN_API_BOOK = "mdai";
    public static final String VALID_PASSWORD_API_BOOK = "123456Qwerty!";


    public static HashMap<String, Double> CURRENCY_RATE_API = new HashMap<>();
    public static HashMap<String, Double> CURRENCY_RATE_UI = new HashMap<>();


}
