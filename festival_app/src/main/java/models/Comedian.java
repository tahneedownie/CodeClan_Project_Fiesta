package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comedians")
public class Comedian extends Artist {

    public Comedian() {
    }

    public Comedian(String firstName, String lastName, String type, String manager) {
        super(firstName, lastName, type, manager);
    }

}
