package apiDemoQa.dto.responseDtoDemoQa;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksResponseDto {
    private List<ListBooksResponseDto> books;
}
