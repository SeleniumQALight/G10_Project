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

/*    public AuthorDto() {
    }

    public AuthorDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }*/
}
