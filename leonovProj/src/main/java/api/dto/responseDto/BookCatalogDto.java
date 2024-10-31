package api.dto.responseDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookCatalogDto {
    private BookDTO[] books;
}
