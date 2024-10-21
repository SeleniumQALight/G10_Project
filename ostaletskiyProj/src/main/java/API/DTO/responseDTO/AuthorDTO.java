package API.DTO.responseDTO;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorDTO {
    private String username;
    private String avatar;
}
