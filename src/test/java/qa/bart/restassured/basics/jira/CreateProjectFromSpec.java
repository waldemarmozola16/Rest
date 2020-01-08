package qa.bart.restassured.basics.jira;


import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.bart.restassured.JiraTest;
import qa.bart.restassured.jira.Project;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@RunWith (Parameterized.class)
public class CreateProjectFromSpec extends JiraTest
{
    private final String type;

    private RequestSpecification request;
    private ResponseSpecification response;

    private static final String CREATE_PROJECT_RESOURCE = "/rest/api/2/project";
    private static final String PROJECT_TEMPLATE_KEY = "{{project_template_key}}";

    public CreateProjectFromSpec(String type) {
        this.type = type;
    }

    private void buildRequest()
    {
        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        basicAuth.setUserName(username);
        basicAuth.setPassword(password);
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setRelaxedHTTPSValidation();
        builder.setAuth(basicAuth);
        builder.setContentType(ContentType.JSON);
        builder.setBody(createProject());
        builder.addFilter(setupProject());
        builder.setBasePath(CREATE_PROJECT_RESOURCE);
        builder.setBaseUri(hostname);
        request = builder.build();
    }

    private void buildResponse()
    {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(201);
        builder.expectContentType(ContentType.JSON);
        builder.expectResponseTime(lessThan(5000L));
        response = builder.build();
    }

    private Project createProject()
    {
        long time = Instant.now().toEpochMilli();
        String key = String.format("PK%04d", time % 1000);
        String name = String.format("PN%08d", time % 100000);
        return new Project(
                key,
                name,
                "description",
                "admin",
                "PROJECT_LEAD",
                "software",
                PROJECT_TEMPLATE_KEY
        );
    }

    private Filter setupProject()
    {
        return (requestSpec, responseSpec, ctx) -> {
            String body = requestSpec.getBody();
            body = body.replace(PROJECT_TEMPLATE_KEY, type);
            requestSpec.body(body);
            return ctx.next(requestSpec, responseSpec);
        };
    }

    @Before
    public void setUp()
    {
        buildRequest();
        buildResponse();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][] {
                { "com.pyxis.greenhopper.jira:gh-simplified-kanban" },
                { "com.pyxis.greenhopper.jira:gh-simplified-scrum" },
        });
    }

    @Test
    public void shouldCreateProject()
    {
        given()
                .spec(request).
        when()
                .post().
        then()
                .spec(response)
                .log()
                .ifError();
    }
}
