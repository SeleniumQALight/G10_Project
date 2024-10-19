package apiDemoQA.dto.requestDtoDemo;

import apiDemoQA.dto.responseDtoDemo.BookListDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddBookDto {
    private String userId;
    private List<BookListDto> collectionOfIsbns;
}