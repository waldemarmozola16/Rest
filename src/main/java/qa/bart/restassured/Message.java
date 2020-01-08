package qa.bart.restassured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Message
{
    private String firstname;
    private String surname;
    private String message;

    @JsonCreator
    public Message(@JsonProperty ("firstname") String firstname, @JsonProperty ("surname") String surname, @JsonProperty ("message") String message)
    {
        this.firstname = firstname;
        this.surname = surname;
        this.message = message;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final Message message1 = (Message) o;
        return Objects.equals(firstname, message1.firstname) &&
                Objects.equals(surname, message1.surname) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(firstname, surname, message);
    }
}