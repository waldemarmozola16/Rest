package qa.bart.restassured.schema;


import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;
import qa.bart.restassured.JiraTest;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;

public class RestAssuredSchema extends JiraTest
{
    @Test
    public void serverInfoPassesSchemaValidation()
    {
        get(path("/rest/api/2/serverInfo")).then().assertThat().body(matchesJsonSchemaInClasspath("server-info-schema.json"));
    }

    @Test
    public void serverInfoPassesUncheckedSchemaValidation()
    {
        get(path("/rest/api/2/serverInfo")).then().assertThat().body(matchesJsonSchemaInClasspath("server-info-schema.json")
                .using(settings().with().checkedValidation(false)));
    }

    @Test
    public void serverInfoPassesUnchaeckedSchemaValidationDefinedStatically() {
        JsonSchemaValidator.settings = settings().with().checkedValidation(false);
        serverInfoPassesUncheckedSchemaValidation();
        JsonSchemaValidator.reset();
    }
}
