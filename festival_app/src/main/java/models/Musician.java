package models;

public class Musician extends Artist {

    private MusicGenreType genre;


    public Musician() {
    }

    public Musician(String name, String manager, double account, Performance performance) {
        super(name, manager, account, performance);
        this.genre = genre;

    }





}
