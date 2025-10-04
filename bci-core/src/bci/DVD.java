package bci;

public class DVD extends Work {
    private String igacRegistration;
    private Creator director;

    public DVD(int id, String title, String igacRegistration, Creator director) {
        super(id, title);
        this.igacRegistration = igacRegistration;
        this.director = director;
    }

    public String getIgacRegistration() {return igacRegistration;}
    public Creator getDirector() {return director;}
}
