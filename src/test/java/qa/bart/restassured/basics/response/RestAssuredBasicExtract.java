package qa.bart.restassured.basics.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import qa.bart.restassured.InfoshareTest;
import qa.bart.restassured.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredBasicExtract extends InfoshareTest
{
    private final String firstname = "Bartosz";
    private final String surname = "Szulc";
    private final String message = String.format("Hello %s %s!", firstname, surname);

    private final ObjectMapper mapper = new ObjectMapper();

    private Response request()
    {
        return
                given()
                    .params("firstname", firstname, "surname", surname).
                when()
                    .get(path("/hello"));
    }

    @Test
    public void returnsFirstnameSurnameAndMessageAsString()
    {
        // given
        String expected = "{\n  \"firstname\": \"Bartosz\", \n  \"message\": \"Hello Bartosz Szulc!\", \n  \"surname\": \"Szulc\"\n}\n";
        // when
        String actual = request().asString();
        // then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void returnsFirstnameSurnameAndMessageInBody()
    {
        // given
        String expected = "{\n  \"firstname\": \"Bartosz\", \n  \"message\": \"Hello Bartosz Szulc!\", \n  \"surname\": \"Szulc\"\n}\n";
        // when, then
        request().
        then()
            .assertThat()
            .body(equalTo(expected));
    }

    @Test
    public void returnsFirstnameSurnameAndMessageAsInputStream() throws IOException
    {
        // given
        Message expected = new Message(firstname, surname, message);
        // when
        InputStream response = request().asInputStream();
        Message actual = mapper.readValue(response, Message.class);
        // then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void returnsMessageOnHello()
    {
        request().
        then()
            .assertThat()
            .body("message", response -> equalTo(
                    String.format("Hello %s %s!",response.path("firstname"), response.path("surname"))));
    }

    @Test
    public void returnsTitlesOnly()
    {
        // when
        List<String> titles = get(path("/book")).then().extract().path("store.book.title");
        // then
        assertThat(titles, hasItems("Sayings of the Century", "Sword of Honour", "Moby Dick", "The Lord of the Rings"));
    }

    @Test
    public void respondsWithTitlesAndHeader()
    {
        // when
        Response response = get(path("/book")).then().extract().response();
        List<String> titles = response.path("store.book.title");
        String contentType = response.header("Content-Type");
        // then
        assertThat(titles, hasItems("Sayings of the Century", "Sword of Honour", "Moby Dick", "The Lord of the Rings"));
        assertThat(contentType, equalTo("application/json"));
    }

    @Test
    public void respondsWithStatusCode200WhenRequestingBooks() {
        Response response = get(path("/book")).then().extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.cookies().isEmpty(), is(true));
    }
}
