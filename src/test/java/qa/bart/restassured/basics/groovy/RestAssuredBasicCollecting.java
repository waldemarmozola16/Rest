package qa.bart.restassured.basics.groovy;


import org.junit.Test;
import qa.bart.restassured.InfoshareTest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredBasicCollecting extends InfoshareTest
{
    @Test
    public void returnsTotalPriceOfSelectedBooks()
    {
        given().
            get(path("/book")).
        then().
            body("store.book.findAll { it.price < 10 }.price.sum()", greaterThan(17.));
    }

    @Test
    public void returnsTotalAuthorNamesLengthOfSelectedBooks()
    {
        given().
            get(path("/book")).
        then().
            body("store.book.findAll { it.price < 10 }.collect { it.author.length() }.sum()", equalTo(25));
    }
}
