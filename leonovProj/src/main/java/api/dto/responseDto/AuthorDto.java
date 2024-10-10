package api.dto.responseDto;


import lombok.*;

@Data
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class AuthorDto {
    private String username;
    private String avatar;
}
