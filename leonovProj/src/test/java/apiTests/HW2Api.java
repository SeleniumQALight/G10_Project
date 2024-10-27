package apiTests;

import api.BookApiHelper;
import api.dto.responseDto.BookCatalogDto;
import api.dto.responseDto.BookDTO;
import api.dto.responseDto.UserDto;
import data.TestData;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HW2Api {
    Logger logger = Logger.getLogger(getClass());
    BookApiHelper bookApiHelper = new BookApiHelper();
    String token;
    String userId;

    @Before
    public void precondition() {
        logger.info("Precondition is running");

        //PRECONDITION
        //login
        //get token and userID

        ResponseBody response = bookApiHelper.login(TestData.VALID_LOGIN_BOOK, TestData.VALID_PASSWORD_BOOK);
        token = response.path("token");
        userId = response.path("userId");

        //delete all books
        bookApiHelper.deleteUserBooks(userId, token);
        logger.warn("All books removed from user collection");
    }

    @Test
    public void addBookToCollection() {
        logger.info("Test is running");


        BookDTO[] allBooks = bookApiHelper.getAllBooksCatalog();
        String firstBookIsbn = allBooks[0].getIsbn();
        bookApiHelper.addBookToCollection(userId, token, firstBookIsbn);
        logger.info("Book with ISBN " + firstBookIsbn + " added to user collection");
        UserDto userBooks = bookApiHelper.getAllBooksByUser(userId, token);
        logger.info("User books number: " + userBooks.getBooks().length);

        Assert.assertEquals("Number of books in user collection", 1, userBooks.getBooks().length);
        Assert.assertEquals("ISBN of the book", firstBookIsbn, userBooks.getBooks()[0].getIsbn());
        logger.info("Test finished");
        //TEST
        //get a list with books catalog
        // get the first book ISBN
        //add the first book to user collection
        //check that the book is added


    }


}
