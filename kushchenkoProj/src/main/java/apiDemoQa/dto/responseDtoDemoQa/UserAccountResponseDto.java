package apiDemoQa.dto.responseDtoDemoQa;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserAccountResponseDto {
    private String userId;
    private String username;
    private List<ListBooksResponseDto> books;
}

