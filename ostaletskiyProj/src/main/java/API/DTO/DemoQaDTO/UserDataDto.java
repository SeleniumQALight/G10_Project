package API.DTO.DemoQaDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDataDto {
    private String userName;
    private String password;
}
