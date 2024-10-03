package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostsDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty("select1")
    private String select;
    private String uniquePost;
    private String createdDate;
    private AuthorDto author;
    private AuthorDto avatar;
    private Boolean isVisitorOwner;

    public PostsDto() {
    }

    public PostsDto(String title, String body, String select, String uniquePost,
                    AuthorDto author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select = select;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSelect() {
        return select;
    }

    public String getUniquePost() {
        return uniquePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public AuthorDto getAvatar() {
        return avatar;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public void setAvatar(AuthorDto avatar) {
        this.avatar = avatar;
    }

    public void setIsVisitorOwner(Boolean isVisitorOwner) {
       this.isVisitorOwner = isVisitorOwner;
    }

    @Override
    public  String toString() {
        return "PostsDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select='" + select + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", avatar=" + avatar +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }
}
