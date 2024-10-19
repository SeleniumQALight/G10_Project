package apiDemoQA.dto.responseDtoDemo;

import lombok.*;

        import java.util.List;
import java.util.stream.DoubleStream;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookListDto {
    private List<BookParamsDto> books;
}