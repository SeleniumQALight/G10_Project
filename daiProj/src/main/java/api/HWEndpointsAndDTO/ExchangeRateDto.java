package api.HWEndpointsAndDTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ExchangeRateDto {
    private String date;
    private String bank;
    private String baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRate> exchangeRate;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
   public static class ExchangeRate {
        private String baseCurrency;
        private String currency;
        private String saleRateNB;
        private String purchaseRateNB;
        private String saleRate;
        private String purchaseRate;
    }
}


