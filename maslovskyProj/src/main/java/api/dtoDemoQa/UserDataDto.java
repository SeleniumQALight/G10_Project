package api.dtoDemoQa;

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
