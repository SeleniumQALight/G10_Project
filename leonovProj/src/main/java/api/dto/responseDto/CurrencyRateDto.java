package api.dto.responseDto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CurrencyRateDto {
    private String baseCurrency;
    private String currency;
    private double saleRateNB;
    private double saleRate;
    private Double purchaseRate;
    private Double purchaseRateNB;

}
