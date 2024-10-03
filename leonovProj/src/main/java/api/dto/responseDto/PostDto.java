package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDto {

    public PostDto() {
    }

    public PostDto(String title, String body, String select, String uniquePost, AuthorDto author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select = select;
        this.uniquePost = uniquePost;
        this.isVisitorOwner = isVisitorOwner;
        this.author = author;
    }

    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getUniquePost() {
        return uniquePost;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setIsVisitorOwner(Boolean isVisitorOwner) {
        this.isVisitorOwner = isVisitorOwner;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    @JsonProperty("select1")
    private String select;
    private String uniquePost;
    private String createdDate;

    private Boolean isVisitorOwner;
    private AuthorDto author;

    @Override
    public String toString() {
        return "PostDto{" +
                "_id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select='" + select + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", isVisitorOwner=" + isVisitorOwner +
                ", author=" + author +
                '}';
    }
}

