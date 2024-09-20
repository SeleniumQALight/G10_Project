package data;

import com.github.javafaker.Faker;
import utils.Utils;

public class UserForRegistration {
    private String userName;
    private String email;
    private String password;

    private Faker faker = new Faker();

    public final static String SHORT_PASSWORD_NOT_VALID = "tr";
    public final static String SHORT_EMAIL_NOT_VALID = "tr";

    public final static String USER_NAME_MIN_LENGTH = "t".repeat(3);
    public final static String USER_NAME_MAX_LENGTH = "t".repeat(30);

    public final static String PASSWORD_MIN_LENGTH = "t".repeat(6) + "1".repeat(6);
    public final static String PASSWORD_MAX_LENGTH = "t".repeat(25) + "1".repeat(25);


    public UserForRegistration(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserForRegistration(String tcNumber) {
        this.userName = tcNumber + "da" + faker.name().lastName() + Utils.getDateAndTimeFormattedOnlyNumbers();
        this.email = this.userName + "@gml.rr";
        this.password = TestData.VALID_PASSWORD_UI;

    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;

    }

    public UserForRegistration updateUserName(String newUserName) {
        this.userName = newUserName;
        return this;
    }


    public UserForRegistration updateEmail(String newEmail) {
        this.email = newEmail;
        return this;
    }

    public UserForRegistration updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @Override

    public String toString() {
        return "UserForRegistration{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';


    }
}

