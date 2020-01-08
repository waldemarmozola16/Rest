package qa.bart.restassured.basics.request;


import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredBasicHeaders extends InfoshareTest
{
    @Test
    public void returnsFirstnameSurnameAndEncodedMessageWhenRequestedWithAHeader() {
        // given
        String firstname = "Bartosz";
        String surname = "Szulc";
        String message = String.format("Hello %s %s!", firstname, surname).replaceAll(".", "*");
        // given, when, then
        given()
            .params("firstname", firstname, "surname", surname)
            .and()
            .header("Content-Type", "secret").
        when()
            .get(path("/hello")).
        then()
            .body("firstname", equalTo(firstname))
            .body("surname", equalTo(surname))
            .body("message", equalTo(message));
    }
}
