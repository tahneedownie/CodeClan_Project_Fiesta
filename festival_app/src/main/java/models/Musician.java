package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "musicians")
public class Musician extends Artist {

    private MusicGenreType genre;

    public Musician() {
    }

    public Musician(String firstName, String lastName, String type, String manager, MusicGenreType genre) {
        super(firstName, lastName, type, manager);
        this.genre = genre;
    }

    @Column(name = "genre")
    public MusicGenreType getGenre() {
        return genre;
    }

    public void setGenre(MusicGenreType genre) {
        this.genre = genre;
    }

}
