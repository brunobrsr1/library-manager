package bci;

import java.io.Serializable;

public abstract class Category implements Serializable {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
