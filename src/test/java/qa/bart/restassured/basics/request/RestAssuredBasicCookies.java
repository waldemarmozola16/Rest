package qa.bart.restassured.basics.request;


import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredBasicCookies extends InfoshareTest
{
    @Test
    public void returnsSubmittedCookieValue() {
        // given
        String expected = "someValue";
        // given, when
        String actual =
        given()
            .cookie("custom_cookie", expected).
        when()
            .get(path("/cookie")).
        then()
            .extract()
            .body()
            .as(String.class);
        // then
        assertThat(actual, equalTo(expected));
    }
}
