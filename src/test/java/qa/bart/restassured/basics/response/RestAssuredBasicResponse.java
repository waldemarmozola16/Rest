package qa.bart.restassured.basics.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import qa.bart.restassured.InfoshareTest;
import qa.bart.restassured.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredBasicResponse extends InfoshareTest
{
    private final String firstname = "Bartosz";
    private final String surname = "Szulc";

    @Test
    public void returnsCustomisedCookieOnHello()
    {
        given()
            .params("firstname", firstname, "surname", surname).
        when()
            .get(path("/hello")).
        then()
            .assertThat()
            .cookie("hello_cookie", String.format("\"%s %s\"", firstname, surname))
            .statusCode(200)
            .header("Content-Type", "application/json")
            .contentType("application/json");
    }
}
