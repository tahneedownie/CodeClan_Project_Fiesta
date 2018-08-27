package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();

        staticFileLocation("/public");

        VenueController venueController = new VenueController();
        LineUpController lineUpController = new LineUpController();
        ArtistController artistController = new ArtistController();
        MusicianController musicianController = new MusicianController();
        PerformanceController performanceController = new PerformanceController();

//      Getter for website main page

        get("/", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

    }


//    TODO: Check table relationships
//    TODO: Create controllers and vtl files for remaining classes
//    TODO: Check tables populate and controllers run
//    TODO: Check time object in performance
//    TODO: Make sure you can add artists to performances
//    TODO: Make sure you can add performances to lineups as long as long as they do not have the same time as another performance
//    TODO: Add ticket class make it nullable so some performances are chargeable
//    TODO: Add manager class instead of string manager manager has an account
//    TODO: Add logic for artists and managers to get paid by number of performances maybe both classes have a fee variable?
//    TODO: Make sure that visitors can only be added to venues as long as the capacity is not exceeded
//    TODO: Make sure that only visitors with tickets can be added to performances that are ticketed (ticket not nullable)
//    TODO: Make sure you can see all artists performing in a venue
//    TODO: Make sure that lineups can only be added to venues on different days (venue cant have two lineups on same day)



