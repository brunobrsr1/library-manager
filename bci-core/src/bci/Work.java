package bci;

import java.io.Serializable;

public abstract class Work implements Serializable {
    private int id;
    private int lastId;
    private String title;
    private int price;
    private int copies;

    public Work(int id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}

}
