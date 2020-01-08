package qa.bart.restassured.basics.auth;

import org.junit.Test;
import qa.bart.restassured.InfoshareTest;
import qa.bart.restassured.JiraTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredCustomAuth extends InfoshareTest
{
    @Test
    public void shouldCustomAuthenticate() {
        given().
            filter((requestSpec, responseSpec, ctx) -> {
                String userId = requestSpec.getHeaders().getValue("X-User-Id");
                String roleId = requestSpec.getHeaders().getValue("X-Role-Id");
                requestSpec.header("Auth", userId + roleId);
                return ctx.next(requestSpec, responseSpec);
            }).
            header("X-User-Id", 1).
            header("X-Role-Id", 1).
        when().
            get(path("/auth")).
        then().
            statusCode(200).
            body("message", equalTo("welcome 1"));
    }
}
