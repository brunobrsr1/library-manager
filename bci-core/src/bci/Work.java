package bci;

import java.io.Serializable;
import java.util.List;

public abstract class Work implements Serializable {
    private int id;
    private String title;
    private int price;
    private int copies;
    private Category category;
    private List<Creator> creators;

    public Work(int id, String title, int price, int copies, Category category, List<Creator> creators) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.category = category;
        this.creators = creators;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public int getPrice() {return price;}
    public int getCopies() {return copies;}
    public void setCopies(int copies) {this.copies = copies;}
    public Category getCategory() {return category;}
    public List<Creator> getCreators() {return creators;}
}
