package api.dto.responseDto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookDTO {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publish_date;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}


