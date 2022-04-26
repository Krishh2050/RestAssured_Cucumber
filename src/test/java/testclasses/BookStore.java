package testclasses;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookStore {
    public final String BASE_URL = "https://bookstore.toolsqa.com";
    public final String GET_BOOKS_URL = "https://bookstore.toolsqa.com/BookStore/v1/Books";
    public final String AUTHOROIZED_URL = "https://bookstore.toolsqa.com/Account/v1/Authorized";
    public final String GENERATE_TOKEN_URL = "https://bookstore.toolsqa.com/Account/v1/GenerateToken";

    @org.testng.annotations.Test
    public void get_Authorized_Request(){
        given().contentType(ContentType.JSON)
                .get(GET_BOOKS_URL)
                .then().statusCode(200).log().body();
    }
    @Test
    public void post_Authorized_Request(){
        String reqBody = "{ \"userName\": \"username@1234\", \"password\": \"Password@1234\"}";
        String body = "{\n" +
                "  \"userName\": \"username@123\",\n" +
                "  \"password\": \"Password@123\"\n" +
                "}";
        given().contentType(ContentType.JSON)
                .body(reqBody)
                .post(AUTHOROIZED_URL)
                .then().statusCode(200).log().body();
    }

    @Test
    public void post_GenerateToken_Request(){
        String reqBody = "{ \"userName\": \"username@1234\", \"password\": \"Password@1234\"}";
        String body = "{\n" +
                "  \"userName\": \"username@123\",\n" +
                "  \"password\": \"Password@123\"\n" +
                "}";
        String token = given().contentType(ContentType.JSON)
                .body(reqBody)
                .post(GENERATE_TOKEN_URL)
                .then().statusCode(200).extract().path("token");
        System.out.println("TOKEN: "+token);
    }

    //  GET REQUEST     -   /testclasses.BookStore/v1/Books
    //  POST REQUEST    -   /testclasses.BookStore/v1/Books
    //  DELETE REQUEST  -   /testclasses.BookStore/v1/Books
}
