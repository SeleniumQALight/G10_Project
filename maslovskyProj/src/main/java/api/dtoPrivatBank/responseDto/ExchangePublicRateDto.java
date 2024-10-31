package api.dtoPrivatBank.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangePublicRateDto {
    private String ccy;
    private Double buy;
    private Double sale;

}
