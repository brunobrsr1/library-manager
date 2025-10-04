package bci;

import java.util.Collections;

public class DVD extends Work {
    private String igacRegistration;

    public DVD(int id, String title, int price, int copies, Category category, String igacRegistration, Creator director) {
        super(id, title, price, copies, category, Collections.singletonList(director));
        this.igacRegistration = igacRegistration;
    }

    public String getIgacRegistration() {return igacRegistration;}
}
