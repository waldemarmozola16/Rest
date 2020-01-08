package qa.bart.restassured.basics.spec;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import static io.restassured.RestAssured.when;


public class RestAssuredBasicResponseSpec extends InfoshareTest
{
    private static ResponseSpecification response;

    @BeforeClass
    public static void setUp()
    {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectContentType("application/json");
        response = builder.build();
    }

    @Test
    public void shouldReturn200AndContentTypeWhenRequestingBooks()
    {
        when().
                get(path("/book")).
        then().
                spec(response);
    }

    @Test
    public void shouldReturn200AndContentTypeWhenRequestingHello()
    {
        when().
                get(path("/hello")).
        then().
                spec(response);
    }
}
