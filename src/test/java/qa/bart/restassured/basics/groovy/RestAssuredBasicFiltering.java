package qa.bart.restassured.basics.groovy;


import io.restassured.path.json.JsonPath;
import org.junit.Test;
import qa.bart.restassured.JiraTest;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredBasicFiltering extends JiraTest
{
    @Test
    public void returnsProjectKeyMatchingFilter()
    {
        get(path("/rest/api/2/project")).then().body("find {it.id==\"10017\"}.key", equalTo("OP"));
    }

    @Test
    public void returnsAllProjectKeysMatchingFilter()
    {
        get(path("/rest/api/2/project")).then().body("findAll {it.name.startsWith(\"OPEN PROJECT\")}.key", hasItems("OP", "OPB"));
    }

    @Test
    public void returnsProjectMatchingResult()
    {
        String response = get(path("/rest/api/2/project")).asString();
        Map<String, String> actual = JsonPath.from(response).get("find {it.id==\"10017\"}");
        assertThat(actual.get("key"), equalTo("OP"));
        assertThat(actual.get("id"), equalTo("10017"));
    }
}
