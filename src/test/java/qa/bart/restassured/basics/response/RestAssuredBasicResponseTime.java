package qa.bart.restassured.basics.response;


import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredBasicResponseTime extends InfoshareTest
{
    @Test
    public void returnsResponseBelow2s()
    {
        given()
            .params("firstname", "Bartosz", "surname", "Szulc").
        when()
            .get(path("/hello")).
        then()
            .time(lessThan(2000L))
            .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
