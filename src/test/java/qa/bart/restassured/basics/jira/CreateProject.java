package qa.bart.restassured.basics.jira;


import io.restassured.http.ContentType;
import org.junit.Test;
import qa.bart.restassured.JiraTest;
import qa.bart.restassured.jira.Project;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CreateProject extends JiraTest
{
    @Test
    public void shouldCreateProject()
    {
        // given
        String resource = "/rest/api/2/project";
        long time = Instant.now().toEpochMilli();
        String key = String.format("PK%04d",  time % 1000);
        String name = String.format("PN%08d", time % 100000);
        Project project = new Project(
                key,
                name,
                "description",
                "admin",
                "PROJECT_LEAD",
                "software",
                "com.pyxis.greenhopper.jira:gh-simplified-kanban"
        );
        given()
                .relaxedHTTPSValidation()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(project)
                .contentType(ContentType.JSON).
        when()
                .post(path(resource)).
        then()
                .log()
                .ifError()
                .assertThat()
                .statusCode(201)
                .body("self", response -> equalTo(String.format("%s%s/%s", hostname, resource, response.path("id"))))
                .time(lessThan(5000L));
    }
}
