package apiDemoQA.dto.responseDtoDemo;

import lombok.*;

        import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IsbnDto {
    private List<BookParamsDto> books;
}