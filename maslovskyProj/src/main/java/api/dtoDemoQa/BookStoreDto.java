package api.dtoDemoQa;

import api.dtoPrivatBank.responseDto.ExchangeRateDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookStoreDto {
    private String userId;
    private List<CollectionOfIsbnsDto> collectionOfIsbnsDto;
}
