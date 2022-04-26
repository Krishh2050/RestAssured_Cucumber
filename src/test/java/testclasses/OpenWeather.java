package testclasses;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class OpenWeather {
    public final String CITYNAME = "tirupati";
    public final String APIKEY = "aba852c7c875ddefa74a2908c588743d";
    public final String WEATHER_API_BY_CITYNAME_URI ="https://api.openweathermap.org/data/2.5/weather";
    public final String WEATHER_API_BY_CITYNAME_URI_QUERYPARAMS ="https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";

    @Test
    public void getRequestFailure1(){
        //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        //  Request with only cityname parameter
        given().queryParam("q", CITYNAME)
                //.queryParam("appid", APIKEY)
                .when().get(WEATHER_API_BY_CITYNAME_URI)
                .then().statusCode(401);
    }

    @Test
    public void getRequestFailure2(){
        //  Request with only Apikey parameter
        given()//.queryParam("q", CITYNAME)
                .queryParam("appid", APIKEY)
                .when().get(WEATHER_API_BY_CITYNAME_URI)
                .then().statusCode(400);
    }

    @Test
    public void getRequestSuccess1(){
        //  Request with both cityname, Apikey parameters
        given().queryParam("q", CITYNAME)
                .queryParam("appid", APIKEY)
                .when().get(WEATHER_API_BY_CITYNAME_URI)
                .then().statusCode(200).log().body();
    }

    @Test
    public void getRequestSuccess2(){
        //  Request with both cityname, Apikey parameters
        //  Extracting a value from the Response JSON - which is present deep inside.
        String country =
                given().queryParam("q", CITYNAME)
                        .queryParam("appid", APIKEY)
                        .when().get(WEATHER_API_BY_CITYNAME_URI)
                        .then().extract().path("sys.country");
        System.out.println("sys.country: "+country);
        Assert.assertEquals("IN", country);
    }

    @Test
    public void getRequestSuccess3(){
        //  Request with both cityname, Apikey parameters
        //  Extracting a value from the Response JSON - which is inside an array.
        int id = given().queryParam("q", CITYNAME)
                .queryParam("appid", APIKEY)
                .when().get(WEATHER_API_BY_CITYNAME_URI)
                .then().extract().path("weather[0].id");
        System.out.println("weather[0].id: "+id);
        Assert.assertEquals(801, id);
    }

    @Test
    public void getRequestSuccess4(){
        //  Request with both cityname, Apikey parameters
        //  Assert headers.
        given().queryParam("q", CITYNAME)
                .queryParam("appid", APIKEY)
                .when().get(WEATHER_API_BY_CITYNAME_URI)
                .then().assertThat().header("Content-Type", "application/json; charset=utf-8");
    }
}
