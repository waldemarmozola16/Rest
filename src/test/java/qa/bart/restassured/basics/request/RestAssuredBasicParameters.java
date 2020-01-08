package qa.bart.restassured.basics.request;


import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredBasicParameters extends InfoshareTest
{
    @Test
    public void returnsFirstnameSurnameAndMessage() {
        // given
        String firstname = "Bartosz";
        String surname = "Szulc";
        String message = String.format("Hello %s %s!", firstname, surname);
        // given, when, then
        given()
            .params("firstname", firstname, "surname", surname).
        when()
            .get(path("/hello")).
        then()
            .body("firstname", equalTo(firstname))
            .body("surname", equalTo(surname))
            .body("message", equalTo(message));
    }

    @Test
    public void shouldAddValues() {
        // given
        List values = new ArrayList<Integer>() {{
            add(2);
            add(3);
            add(4);
        }};
        int expected = 9;
        // when
        int actual =
        given()
                .queryParam("value", values).
        when()
                .get(path("/add")).
        then()
                .extract()
                .body()
                .as(Integer.class);
        // then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldAddValuesMultipliedByAFactor() {
        // given
        List values = new ArrayList<Integer>() {{
            add(2);
            add(3);
            add(4);
        }};
        int factor = 2;
        int expected = 9 * factor;
        // when
        int actual =
        given()
                .queryParam("value", values)
                .pathParam("factor", factor).
        when()
                .get(path("/add/{factor}")).
        then()
                .extract()
                .body()
                .as(Integer.class);
        // then
        assertThat(actual, equalTo(expected));
    }
}
