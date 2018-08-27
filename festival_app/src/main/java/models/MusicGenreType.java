package models;

public enum MusicGenreType {

    ELECTRONICDANCE("Electronic Dance"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    BLUES("Blues"),
    POP("Pop"),
    FOLK("Folk"),
    COUNTRY("Country"),
    HIPHOP("Hip Hop"),
    RHYTHMANDBLUES("R&B"),
    HEAVYMETAL("Heavy Metal"),
    REGGAE("Reggae"),
    REGGAETON("Reggaeton"),
    PUNKROCK("Punk Rock"),
    FUNK("Funk"),
    ALTROCK("Alt Rock"),
    DISCO("Disco"),
    SOUL("Soul"),
    INSTRUMENTAL("Instrumental"),
    HOUSE("House"),
    TECHNO("Techno"),
    RAP("Rap"),
    OPERA("Opera"),
    GRUNGE("Grunge"),
    PROGRESSIVEROCK("Progressive Rock"),
    WORLDMUSIC("World Music"),
    DUBSTEP("Dubstep"),
    GOSPEL("Gospel"),
    SKA("Ska"),
    AMBIENT("Ambient"),
    TRANCE("Trance"),
    NEWWAVE("New wave"),
    LATINO("Latino"),
    AFROPOP("Afropop"),
    INDIE("Indie"),
    BALLAD("Ballad"),
    DRUMANDBASS("Drum & Bass"),
    ELECTRO("Electro");

    private String genreName;

    MusicGenreType(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }
}
