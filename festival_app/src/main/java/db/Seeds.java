package db;

import models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class Seeds {

    public Seeds() {
    }

    public static void seedData(){

        DBHelper.deleteAll(Visitor.class);
        DBHelper.deleteAll(Venue.class);
        DBHelper.deleteAll(LineUp.class);
        DBHelper.deleteAll(Performance.class);
        DBHelper.deleteAll(Artist.class);
        DBHelper.deleteAll(Musician.class);
        DBHelper.deleteAll(Comedian.class);

//        VISITORS__________________________________________________________________________________________________
        Visitor visitor1 = new Visitor("Digory", "Eyebrow");
        DBHelper.save(visitor1);
        Visitor visitor2 = new Visitor("Molly", "Drummer");
        DBHelper.save(visitor2);
        Visitor visitor3 = new Visitor("Vicky", "Jackson-Five");
        DBHelper.save(visitor3);
        Visitor visitor4 = new Visitor("Mike", "Thorpe-Park");
        DBHelper.save(visitor4);
        Visitor visitor5 = new Visitor("Robbie", "Drumbells");
        DBHelper.save(visitor5);
        Visitor visitor6 = new Visitor("Colin", "Riddles");
        DBHelper.save(visitor6);
        Visitor visitor7 = new Visitor("Ricardio", "Ruiz");
        DBHelper.save(visitor7);
        Visitor visitor8 = new Visitor("Stuart", "Blue");
        DBHelper.save(visitor8);
        Visitor visitor9 = new Visitor("Gemma", "Prettigal");
        DBHelper.save(visitor9);
        Visitor visitor10 = new Visitor("BumbleBee", "Ficek");
        DBHelper.save(visitor10);

//        VENUES_________________________________________________________________________________________________
        Venue venue1 = new Venue("Assembly George Square Gardens", "George Square, EH8 9LH", 10000);
        DBHelper.save(venue1);
        Venue venue2 = new Venue("Bedlam Theatre", "11b Bristo Place, EH1 1EZ", 5000);
        DBHelper.save(venue2);
        Venue venue3 = new Venue("Dance Base", "14-16 Grassmarket, EH1 2JU", 2000);
        DBHelper.save(venue3);
        Venue venue4 = new Venue("Edinburgh Playhouse", "18-22 Greenside Place, EH1 3AA", 10000);
        DBHelper.save(venue4);
        Venue venue5 = new Venue("Gilded Balloon Teviot", "Teviot Row House, EH8 9AJ", 1000);
        DBHelper.save(venue5);

//        LINEUPS___________________________________________________________________________________________________
        LineUp lineUp1 = new LineUp(LocalDate.of(2018,8,24), venue1);
        DBHelper.save(lineUp1);
        LineUp lineUp2 = new LineUp(LocalDate.of(2018,8,25), venue2);
        DBHelper.save(lineUp2);
        LineUp lineUp3 = new LineUp(LocalDate.of(2018,8,26), venue3);
        DBHelper.save(lineUp3);
        LineUp lineUp4 = new LineUp(LocalDate.of(2018,8,24), venue4);
        DBHelper.save(lineUp4);
        LineUp lineUp5 = new LineUp(LocalDate.of(2018,8,25), venue5);
        DBHelper.save(lineUp5);

//        ARTISTS____________________________________________________________________________________________________
        Musician chvrches1 = new Musician("Lauren", "Mayberry", "Singer", "Chvrches manager", MusicGenreType.POP);
        DBHelper.save(chvrches1);
        Musician chvrches2 = new Musician("Iain", "Cook", "Guitar, Bass, Sampler", "Chvrches manager", MusicGenreType.POP);
        DBHelper.save(chvrches2);
        Musician chvrches3 = new Musician("Martin", "Doherty", "Keyboards", "Chvrches manager", MusicGenreType.POP);
        DBHelper.save(chvrches3);

        Comedian comedian1 = new Comedian("Reginald", "D Hunter", "Standup", "Reg Manager", Locale.US);
        DBHelper.save(comedian1);
        Comedian comedian2 = new Comedian("Frankie", "Boyle", "Standup", "Frankie", Locale.UK);
        DBHelper.save(comedian2);

//        PERFORMANCES________________________________________________________________________________________________
        Performance performance1 = new Performance(LocalTime.of(7,30), 60, lineUp1);
        DBHelper.save(performance1);
        performance1.addArtistToPerformance(chvrches1);
        performance1.addArtistToPerformance(chvrches2);
        performance1.addArtistToPerformance(chvrches3);
        DBHelper.update(performance1);

        Performance performance2 = new Performance(LocalTime.of(8,30), 60, lineUp1);
        DBHelper.save(performance2);
        performance2.addArtistToPerformance(comedian1);
        performance2.addArtistToPerformance(comedian2);
        DBHelper.update(performance2);

        Performance performance3 = new Performance(LocalTime.of(9,30), 30, lineUp1);
        DBHelper.save(performance3);
        Performance performance4 = new Performance(LocalTime.of(7,0), 60, lineUp3);
        DBHelper.save(performance4);
        Performance performance5 = new Performance(LocalTime.of(8,0), 60, lineUp3);
        DBHelper.save(performance5);
    }

}
