package API.DTO.requestDTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatePostDTO {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;
}
