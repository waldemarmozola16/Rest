package qa.bart.restassured.basics.request;

import io.restassured.http.Method;
import org.junit.Test;
import qa.bart.restassured.Book;
import qa.bart.restassured.InfoshareTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.isIn;

public class RestAssuredBasicMethod extends InfoshareTest
{
    @Test
    public void shouldCreateNewBookFromObject()
    {
        // given
        Book expected = new Book(
                "Bartosz Szulc",
                "software",
                "123",
                "quality in software development",
                "/books/123",
                100000.5
        );
        // when
        Book actual =
                given()
                        .contentType("application/json")
                        .header("Content-Type", "application/json")
                        .body(expected).
                when()
                        .post(path("/book"))
                        .as(Book.class);
        // then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldCreateNewBookFromDict()
    {
        // given
        Map<String, Object> expected = new HashMap<>();
        expected.put("author", "Bartosz Szulc");
        expected.put("category", "software");
        expected.put("isbn", "123");
        expected.put("title", "quality in software development");
        expected.put("href", "/books/123");
        expected.put("price", 100000);
        // when
        Map<String, Object> actual =
                given()
                        .contentType("application/json")
                        .header("Content-Type", "application/json")
                        .body(expected).
                when()
                        .post(path("/book")).
                then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getMap("$");
        // then
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void shouldReturn200WhenRequestingBooks()
    {
        request(Method.HEAD, path("/book")).then().assertThat().statusCode(200);
    }
}
