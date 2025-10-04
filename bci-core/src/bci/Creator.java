package bci;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Creator implements Serializable {
    // Atributes
    private String name;
    private List<Work> works = new ArrayList<>();

    // Constructor
    public Creator(String name) {
        this.name = name;
    }

    // Getters
    public String getName() {return name;}

    public void addWork(Work work) {
        works.add(work);
    }
}
