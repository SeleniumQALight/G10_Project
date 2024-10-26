package apiTests;

import apiDemoQa.dto.ApiHelperDemoQa;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AddBookByApi {
    private final Logger logger = Logger.getLogger(getClass());
    private static ApiHelperDemoQa apiHelperDemoQa = new ApiHelperDemoQa();


    @Before
    public void getUserDataAndDeleteBooksForUser() {
        logger.info("Get token and userId for user");
        apiHelperDemoQa.getTokenAndUserId();
        apiHelperDemoQa.deleteAllBooksForUser();
    }

    @Test
    public void addBookByApi() {
        apiHelperDemoQa.getAllBooks();
        apiHelperDemoQa.addBookByUserId();
        apiHelperDemoQa.checkIsBookAddedToProfile();
    }
}
