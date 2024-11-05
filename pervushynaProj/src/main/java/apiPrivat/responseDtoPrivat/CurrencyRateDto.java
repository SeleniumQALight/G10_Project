package apiPrivat.responseDtoPrivat;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CurrencyRateDto {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}