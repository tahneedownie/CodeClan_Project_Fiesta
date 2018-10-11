package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        Seeds.seedData();

        staticFileLocation("/public");

        VenueController venueController = new VenueController();
        LineUpController lineUpController = new LineUpController();
        ArtistController artistController = new ArtistController();
        PerformanceController performanceController = new PerformanceController();
        VisitorController visitorController = new VisitorController();
        MusicianController musicianController = new MusicianController();
        ComedianController comedianController = new ComedianController();


//      Getter for website main page

        get("/", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    }


//    TODO: Check table relationships
//    TODO: Create controllers and vtl files for remaining classes - restful routes and good wireframe
//    TODO: Make sure you can see all artists performing in a venue

//    TODO: Make sure that only visitors with tickets can be added to performances that are ticketed (ticket not nullable)
//    TODO: Add ticket class make it nullable so some performances are chargeable


