package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorDto {
    private String username;
    private String avatar;

}

