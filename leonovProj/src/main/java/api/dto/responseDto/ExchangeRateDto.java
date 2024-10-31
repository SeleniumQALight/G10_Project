package api.dto.responseDto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class ExchangeRateDto {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<CurrencyRateDto> exchangeRate;
}
