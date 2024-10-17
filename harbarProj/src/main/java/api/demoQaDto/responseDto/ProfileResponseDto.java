package api.demoQaDto.responseDto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProfileResponseDto {
    private String userId;
    private String username;
    private List<BookListResponseDto> books;
}
