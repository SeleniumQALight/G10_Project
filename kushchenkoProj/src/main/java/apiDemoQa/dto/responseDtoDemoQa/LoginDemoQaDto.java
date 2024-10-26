package apiDemoQa.dto.responseDtoDemoQa;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginDemoQaDto {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private boolean isActive;
}
