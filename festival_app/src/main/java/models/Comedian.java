package models;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "comedians")
public class Comedian extends Artist {

    private Locale nationality;

    public Comedian() {
    }

    public Comedian(String firstName, String lastName, String type, String manager, Locale nationality) {
        super(firstName, lastName, type, manager);
        this.nationality = nationality;
    }

    @Column(name = "nationality")
    public Locale getNationality() {
        return nationality;
    }

    public void setNationality(Locale nationality) {
        this.nationality = nationality;
    }

}








