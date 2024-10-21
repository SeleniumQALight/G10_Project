package API.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostsDTO {
    @JsonProperty ("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty ("select1")
    private String select;
    private String uniquePost;
    private String createdDate;
    private AuthorDTO author;
    private Boolean isVisitorOwner;
}
