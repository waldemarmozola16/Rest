package qa.bart.restassured.basics.auth;

import org.junit.Test;
import qa.bart.restassured.JiraTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RestAssuredBasicAuth extends JiraTest
{
    @Test
    public void shouldBasicAuthenticate() {
        given()
            .auth()
            .preemptive()
            .basic(username, password).
        when()
            .get(path("/rest/api/2/myself")).
        then()
            .statusCode(200);
    }
}
