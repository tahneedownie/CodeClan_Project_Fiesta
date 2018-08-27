package db;

import models.LineUp;
import models.Venue;

import java.time.LocalDate;

public class Seeds {

    public Seeds() {
    }

    public static void seedData(){

        DBHelper.deleteAll(Venue.class);
        DBHelper.deleteAll(LineUp.class);

        Venue assembly = new Venue("Assembly George Square Gardens", "George Square, EH8 9LH", 10000);
        DBHelper.save(assembly);
        Venue bedlam = new Venue("Bedlam Theatre", "11b Bristo Place, EH1 1EZ", 5000);
        DBHelper.save(bedlam);
        Venue dancebase = new Venue("Dance Base", "14-16 Grassmarket, EH1 2JU", 2000);
        DBHelper.save(dancebase);
        Venue playhouse = new Venue("Edinburgh Playhouse", "18-22 Greenside Place, EH1 3AA", 10000);
        DBHelper.save(playhouse);
        Venue gildedballoon = new Venue("Gilded Balloon Teviot", "Teviot Row House, EH8 9AJ", 1000);
        DBHelper.save(gildedballoon);

        LineUp gildedballoon2608 = new LineUp(LocalDate.of(2018,8,24), gildedballoon);
        DBHelper.save(gildedballoon2608);




    }

}
