package qa.bart.restassured;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Book
{
    public final String author;
    public final String category;
    public final String isbn;
    public final double price;
    public final String title;
    public final String href;

    @JsonCreator
    public Book(
            @JsonProperty ("author") String author,
            @JsonProperty ("category") String category,
            @JsonProperty ("isbn") String isbn,
            @JsonProperty ("title") String title,
            @JsonProperty ("href") String href,
            @JsonProperty ("price") double price
    )
    {
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.href = href;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                Objects.equals(author, book.author) &&
                Objects.equals(category, book.category) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(href, book.href);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(author, category, isbn, price, title, href);
    }
}
