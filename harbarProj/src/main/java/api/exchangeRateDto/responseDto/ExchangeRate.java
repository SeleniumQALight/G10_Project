package api.exchangeRateDto.responseDto;

public class ExchangeRate {
    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public ExchangeRate() {
    }

    public ExchangeRate(String baseCurrency, String currency, Double saleRateNB, Double purchaseRateNB, Double saleRate, Double purchaseRate) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public ExchangeRate(String baseCurrency, String currency, Double saleRateNB, Double purchaseRateNB) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}
