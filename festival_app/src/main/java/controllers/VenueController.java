package controllers;

import db.DBHelper;
import db.DBVenue;
import models.LineUp;
import models.Venue;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class VenueController {

    public VenueController() {
        setupEndPoints();
    }

    private void setupEndPoints() {

//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/venues", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Venue> venues = DBHelper.getAll(Venue.class);

            model.put("template", "templates/venues/index.vtl");
            model.put("venues", venues);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


//        2. #NEW : get '/className/new'

        get("/venues/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Venue> venues = DBHelper.getAll(Venue.class);

            model.put("template", "templates/venues/create.vtl");
            model.put("venues", venues);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        3. #CREATE : post '/className'

        post("/venues", (request, response) -> {

//            Get the data from the form
//            create a new Venue object from that form
//            add the new Venue object to the list
//            render the original itinerary template, passing it the updated list

            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int visitorCapacity = Integer.parseInt(request.queryParams("visitorCapacity"));

            Venue venue = new Venue(name, location, visitorCapacity);
            DBHelper.save(venue);

            response.redirect("/venues");

            return null;
        });

//        4. #SHOW : get '/className/:id'

        get("/venues/:id", (request, response) -> {

            int venueId = Integer.parseInt(request.params(":id"));
            Venue venue = DBHelper.find(venueId, Venue.class);
            List<LineUp> lineups = DBVenue.getVenuesLineups(venue);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/venues/show.vtl");
            model.put("venue", venue);
            model.put("lineups", lineups);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        5. #EDIT : get '/className/:id/edit'

        get("/venues/:id/edit", (request, response) ->{

            int venueId = Integer.parseInt(request.params(":id"));
            Venue venue = DBHelper.find(venueId, Venue.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/venues/edit.vtl");
            model.put("venue", venue);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        6. #UPDATE : post '/className/:id'

        post("/venues/:id", (request, response) -> {

//            Get the new/updated parameters for the venue(find by id) from the form
//            Update the Venue object from that form (set values)
//            add the new Venue object to the list (use DBHelper to save/update venue to db)
//            render the original itinerary template, passing it the updated list

            int venueId = Integer.parseInt(request.params(":id"));
            Venue venue = DBHelper.find(venueId, Venue.class);

            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int visitorCapacity = Integer.parseInt(request.queryParams("visitorCapacity"));

            venue.setName(name);
            venue.setLocation(location);
            venue.setVisitorCapacity(visitorCapacity);

            DBHelper.save(venue);

            response.redirect("/venues");

            return null;
        });

//        7. #DESTROY : get '/className/:id/delete'

        get("/venues/:id/delete", (request, response) ->{

            int venueId = Integer.parseInt(request.params(":id"));
            Venue venue = DBHelper.find(venueId, Venue.class);

            DBHelper.delete(venue);

            response.redirect("/venues");
            return null;
        });

    }

}
