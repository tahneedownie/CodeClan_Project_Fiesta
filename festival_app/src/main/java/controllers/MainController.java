package controllers;

import db.Seeds;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();
        VenueController venueController = new VenueController();
        LineUpController lineUpController = new LineUpController();

    }


}
