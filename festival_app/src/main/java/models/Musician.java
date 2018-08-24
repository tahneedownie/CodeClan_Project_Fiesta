package models;

public class Musician extends Artist {

    private MusicGenreType genre;

    public Musician() {
    }

    public Musician(String name, String manager, MusicGenreType genre) {
        super(name, manager);
        this.genre = genre;
    }

    public MusicGenreType getGenre() {
        return genre;
    }

    public void setGenre(MusicGenreType genre) {
        this.genre = genre;
    }

}
