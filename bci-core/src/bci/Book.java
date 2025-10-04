package bci;

import java.util.ArrayList;
import java.util.List;

public class Book extends Work{
    private String isbn;

    public Book(int id, String title, int price, int copies, Category category, String isbn, List<Creator> authors) {
        super(id, title, price, copies, category, new ArrayList<>(authors));
        this.isbn = isbn;
    }

    public String getIsbn() {return isbn;}
}
