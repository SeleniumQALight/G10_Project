package apiDemoQa.dto.requestDtoDemoQa;

public class AddBookDto {
    private String userId;
    private String collectionOfIsbns;

    public AddBookDto(String userId, String collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
