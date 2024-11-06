package apiPrivatbank.dto.reqsponseDto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class CurrencyExchangeRateResponse {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
