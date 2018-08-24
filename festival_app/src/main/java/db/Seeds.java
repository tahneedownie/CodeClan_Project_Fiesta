package db;

import models.Venue;

public class Seeds {

    public static void seedData(){

        DBHelper.deleteAll(Venue.class);

        Venue pyramid = new Venue("Pyramid Stage", "Pyramid", 80000);
        DBHelper.save(pyramid);
        Venue gladeLounge = new Venue("Glade Lounge", "Glade", 5000);
        DBHelper.save(gladeLounge);
        Venue johnPeel = new Venue("John Peel Tent", "NW Corner", 10000);
        DBHelper.save(johnPeel);
        Venue arcadia = new Venue("Arcadia", "NE Central", 15000);
        DBHelper.save(arcadia);
        Venue westholts = new Venue("West Holts Stage", "West Holts", 50000);
        DBHelper.save(westholts);

    }

}
