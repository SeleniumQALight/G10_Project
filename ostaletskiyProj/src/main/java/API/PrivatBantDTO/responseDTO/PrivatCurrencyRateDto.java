package API.PrivatBantDTO.responseDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class PrivatCurrencyRateDto {
    // [
    //            {
    //            "ccy":"EUR",
    //            "base_ccy":"UAH",
    //            "buy":"19.20000",
    //            "sale":"20.00000"
    //            },
    //            {
    //            "ccy":"USD",
    //            "base_ccy":"UAH",
    //            "buy":"15.50000",
    //            "sale":"15.85000"
    //            }
    //            ]
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
