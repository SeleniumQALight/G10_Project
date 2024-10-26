package api.demoQaDto.requestDto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBookRequestDto {
    private String userId;
    private List<IsbnRequestDto> collectionOfIsbns;
}
