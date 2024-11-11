package api.HWEndpointsAndDTO;


import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrivatBankCurrencyRateDto {

    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
