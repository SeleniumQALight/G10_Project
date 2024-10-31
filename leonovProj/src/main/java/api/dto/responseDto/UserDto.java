package api.dto.responseDto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String userId;
    private String username;
    private BookDTO[] books;
}
