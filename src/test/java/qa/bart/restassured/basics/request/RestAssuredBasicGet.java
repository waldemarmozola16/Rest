package qa.bart.restassured.basics.request;

import org.junit.Test;
import qa.bart.restassured.JiraTest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredBasicGet extends JiraTest
{
    @Test
    public void serverInfoReturnsBaseUrl()
    {
        when()
                .get(path("/rest/api/2/serverInfo")).
        then()
                .body("baseUrl", equalTo(hostname));
    }

    @Test
    public void serverInfoReturnsZeroAsMinorVersion()
    {
        when()
                .get(path("/rest/api/2/serverInfo")).
        then()
                .body("versionNumbers[1]", equalTo(0));
    }

    @Test
    public void serverInfoReturnsHasExactVersion()
    {
        when()
                .get(path("/rest/api/2/serverInfo")).
        then()
                .body("versionNumbers", hasItems(1001, 0, 0));
    }

    @Test
    public void serverInfoReturnsBigDecimal()
    {
        given().
            config(config().redirect(redirectConfig().followRedirects(true))).
        when().
            get(path("/rest/api/2/serverInfo")).
        then().
            body("buildNumber", equalTo(100082));
    }
}
