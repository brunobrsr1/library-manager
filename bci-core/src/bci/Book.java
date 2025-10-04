package bci;

import java.util.List;

public class Book extends Work{
    private String isbn;
    private List<Creator> authors;

    public Book(int id, String title, String isbn, List<Creator> authors) {
        super(id, title);
        this.isbn = isbn;
        this.authors = authors;
    }

    public String getIsbn() {return isbn;}
}
