package qa.bart.restassured.basics.spec;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import qa.bart.restassured.Book;
import qa.bart.restassured.InfoshareTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RestAssuredBasicRequestSpec extends InfoshareTest
{
    private static RequestSpecification request;

    @BeforeClass
    public static void setUp()
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType("application/json");
        request = builder.build();
    }

    @Test
    public void shouldReturn200AndContentTypeWhenCreatingBook()
    {
        // given
        Book book = new Book(
                "Bartosz Szulc",
                "software",
                "123",
                "quality in software development",
                "/books/123",
                100000.5
        );
        // when, then
        given()
                .spec(request)
                .body(book).
        when()
                .post(path("/book")).
        then()
                .statusCode(201)
                .contentType("application/json");
    }
}
