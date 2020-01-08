package qa.bart.restassured.basics.request;

import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class RestAssuredBasicMultipart extends InfoshareTest
{
    private static final File file = new File(RestAssuredBasicMultipart.class.getResource("/file.txt").getFile());


    @Test
    public void shouldReceiveFileAndReturnItsContent()
    {
        given().
            multiPart("file", file).
        when().
            post(path("/upload")).
        then().
            body(containsString("this is the content"));
    }
}
