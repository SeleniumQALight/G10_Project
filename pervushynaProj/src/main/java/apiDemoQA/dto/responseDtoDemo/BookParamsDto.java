package apiDemoQA.dto.responseDtoDemo;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookParamsDto {
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
