package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SampleStepdefs {
    @Given("given the URL")
    public void givenTheURL() {
        given().contentType(ContentType.JSON)
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .then().statusCode(200).log().body();
    }

    @When("GET request")
    public void getRequest() {
    }

    @Then("it should be successful")
    public void itShouldBeSuccessful() {
    }
}
