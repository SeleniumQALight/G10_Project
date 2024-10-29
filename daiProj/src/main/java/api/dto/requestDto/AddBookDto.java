package api.dto.requestDto;


import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookDto {
    private String userId;
    private List<BooksIsbnDto> collectionOfIsbns;
}
