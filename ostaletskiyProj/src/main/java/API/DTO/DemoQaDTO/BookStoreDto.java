package API.DTO.DemoQaDTO;

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
    private List<CollectionOfIsbnsDto> collectionOfIsbns;
}
