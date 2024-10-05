package api.dtoPrivatBank.responseDto;

import java.util.List;

public class CurrencyRateDto {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRateDto> exchangeRate;

    public CurrencyRateDto() {
    }

    public CurrencyRateDto(String date, ExchangeRateDto exchangeRate) {
        this.date = date;
        this.bank = "PB";
        this.baseCurrency = 980;
        this.baseCurrencyLit = "UAH";
        this.exchangeRate = (List<ExchangeRateDto>) exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

//    public ExchangeRateDto getExchangeRate() {
//        return (ExchangeRateDto) exchangeRate;
//    }

    public List<ExchangeRateDto> getExchangeRate() {
        return exchangeRate;
    }

//    public void setExchangeRate(ExchangeRateDto exchangeRate) {
//        this.exchangeRate = (List<ExchangeRateDto>) exchangeRate;
//    }

    public void setExchangeRate(List<ExchangeRateDto> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "CurrencyRateDto{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
