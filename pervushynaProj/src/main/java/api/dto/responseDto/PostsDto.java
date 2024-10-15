package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostsDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String createdDate;
    private AuthorDto author;
    public Boolean isVisitorOwner;


//    public PostsDto(String title, String body, String select1, String uniquePost, AuthorDto author, Boolean isVisitorOwner) {
//        this.title = title;
//        this.body = body;
//        this.select1 = select1;
//        this.uniquePost = uniquePost;
//        this.author = author;
//        this.isVisitorOwner = isVisitorOwner;
//    }

}
