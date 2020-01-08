package qa.bart.restassured.basics.spec;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import qa.bart.restassured.Book;
import qa.bart.restassured.InfoshareTest;

import static io.restassured.RestAssured.given;


public class RestAssuredBasicSpec extends InfoshareTest
{
    private static RequestSpecification request;
    private static ResponseSpecification response;

    private static void buildRequest()
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType("application/json");
        builder.setBody(new Book(
                "Bartosz Szulc",
                "software",
                "123",
                "quality in software development",
                "/books/123",
                100000.5
        ));
        request = builder.build();
    }

    private static void buildResponse()
    {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(201);
        builder.expectContentType("application/json");
        response = builder.build();
    }

    @BeforeClass
    public static void setUp()
    {
        buildRequest();
        buildResponse();
    }

    @Test
    public void shouldReturn200AndContentTypeWhenCreatingBook()
    {
        given()
                .spec(request).
        when()
                .post(path("/book")).
        then()
                .spec(response);
    }
}
