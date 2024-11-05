package data;


public class TestDataPrivat {
    private final String currency;
    private final double apiBuyRate;
    private final double apiSaleRate;
    private final double uiBuyRate;
    private final double uiSaleRate;

    // Конструктор
    public TestDataPrivat(String currency, double apiBuyRate, double apiSaleRate, double uiBuyRate, double uiSaleRate) {
        this.currency = currency;
        this.apiBuyRate = apiBuyRate;
        this.apiSaleRate = apiSaleRate;
        this.uiBuyRate = uiBuyRate;
        this.uiSaleRate = uiSaleRate;
    }

    // Геттери для полів
    public String getCurrency() {
        return currency;
    }

    public double getApiBuyRate() {
        return apiBuyRate;
    }

    public double getApiSaleRate() {
        return apiSaleRate;
    }

    public double getUiBuyRate() {
        return uiBuyRate;
    }

    public double getUiSaleRate() {
        return uiSaleRate;
    }


}

