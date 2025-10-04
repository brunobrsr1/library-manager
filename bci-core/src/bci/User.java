package bci;

import java.io.Serializable;

public class User implements Serializable {
    // Attributes
    private int id;
    private int lastId;
    private String name;
    private String email;

    // Constructor
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}

}
