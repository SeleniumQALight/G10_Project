package apiTests;

import api.BookApiHelper;
import api.dto.responseDto.BookDTO;
import api.dto.responseDto.UserDto;
import data.TestData;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HW2ApiAdditionalTask {
    Logger logger = Logger.getLogger(getClass());
    BookApiHelper bookApiHelper = new BookApiHelper();
    String token;
    String userId;
    BookDTO[] allBooks;
    List<String> allIsbn;
    int numberOfBooksToBeAdded;

    @Before
    public void precondition() {
        logger.info("Precondition is running");

        ResponseBody response = bookApiHelper.login(TestData.VALID_LOGIN_BOOK, TestData.VALID_PASSWORD_BOOK);
        token = response.path("token");
        userId = response.path("userId");

        //delete all books
        bookApiHelper.deleteUserBooks(userId, token);
        logger.warn("All books removed from user collection");

        //get books catalog
        allBooks = bookApiHelper.getAllBooksCatalog();

        //add random number of books to user collection
        allIsbn = new ArrayList<>();
        for (BookDTO book : allBooks) {
            allIsbn.add(book.getIsbn());
        }
        Random random = new Random();
        //the number of books to be added may vary between 0 and (all books in the catalog - 1) to have al least 1 book difference
        numberOfBooksToBeAdded = random.nextInt( allIsbn.size()-1);
        for (int i = 0; i < numberOfBooksToBeAdded; i++) {
            String bookIsbn = allIsbn.remove(random.nextInt(allIsbn.size()));
            bookApiHelper.addBookToCollection(userId, token, bookIsbn);
        }
    }

    @Test
    public void addBookToCollection() {
        logger.info("Test is running");

        //get all books by user
        UserDto userBooks = bookApiHelper.getAllBooksByUser(userId, token);
        // find the books that are not in the user collection
        List<String> userIsbn = new ArrayList<>();
        for (BookDTO book : userBooks.getBooks()) {
            userIsbn.add(book.getIsbn());
        }
        userIsbn.forEach(isbn -> allIsbn.remove(isbn));
        logger.warn("******ALL ISBN: " + allIsbn);

        //add the first book from the list of books that are not in the user collection
        String firstBookIsbn = allIsbn.get(0);
        bookApiHelper.addBookToCollection(userId, token, firstBookIsbn);
        userBooks = bookApiHelper.getAllBooksByUser(userId, token);
        logger.info("User books number: " + userBooks.getBooks().length);

        //check the number of books in the user collection and the ISBN of the added book the last
        Assert.assertEquals("Number of books in user collection", numberOfBooksToBeAdded+ 1, userBooks.getBooks().length);
        Assert.assertEquals("ISBN of the book", firstBookIsbn, userBooks.getBooks()[numberOfBooksToBeAdded].getIsbn());
        logger.info("Test finished");
    }


}
