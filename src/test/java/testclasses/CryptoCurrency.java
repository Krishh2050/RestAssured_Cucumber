package testclasses;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CryptoCurrency {

    public final String CRYPTOCURRENCY_URI = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Test
    public void getRequest1(){
        given().when().get(CRYPTOCURRENCY_URI).then().statusCode(200);
    }


    @Test
    public void getRequest2(){
        given().when().get(CRYPTOCURRENCY_URI).then().log().body();
    }

    @Test
    public void getRequest3(){
        Response response = given().when().get(CRYPTOCURRENCY_URI);
        Assert.assertEquals(200, response.getStatusCode());
        response.then().log().body();
    }


}
