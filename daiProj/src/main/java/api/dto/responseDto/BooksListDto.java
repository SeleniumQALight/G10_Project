package api.dto.responseDto;


import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksListDto {
    private List<BookParamsDto> books;
}
