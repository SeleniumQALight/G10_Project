package api.exchangeRateDto.responseDto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CurrencyExchangeRateResponse {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;

}
