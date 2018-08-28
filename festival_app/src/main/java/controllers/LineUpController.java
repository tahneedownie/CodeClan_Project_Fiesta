package controllers;

import db.DBHelper;
import models.LineUp;
import models.Venue;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class LineUpController {

    public LineUpController() {
        setupEndPoints();
    }

    private void setupEndPoints() {

//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/lineups", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<LineUp> lineUps = DBHelper.getAll(LineUp.class);

            model.put("template", "templates/lineups/index.vtl");
            model.put("lineUps", lineUps);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


//        2. #NEW : get '/className/new'

        get("/lineups/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<LineUp> lineUps = DBHelper.getAll(LineUp.class);
            List<Venue> venues = DBHelper.getAll(Venue.class);

            model.put("template", "templates/lineups/create.vtl");
            model.put("lineUps", lineUps);
            model.put("venues", venues);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        3. #CREATE : post '/className'

        post("/lineups", (request, response) -> {

//            Get the data from the form
//            create a new LineUp object from that form
//            add the new LineUp object to the list
//            render the original itinerary template, passing it the updated list

//            int venueId = Integer.parseInt(request.queryParams("venue"));
//            Venue venue = DBHelper.find(venueId, Venue.class);

            LocalDate date = LocalDate.parse(request.queryParams("date"));

            LineUp lineUp = new LineUp(date);
            DBHelper.save(lineUp);

            response.redirect("/lineups");

            return null;
        });

//        4. #SHOW : get '/className/:id'

        get("/lineups/:id", (request, response) -> {

            int lineUpId = Integer.parseInt(request.params(":id"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/lineups/show.vtl");
            model.put("lineUp", lineUp);


            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        5. #EDIT : get '/className/:id/edit'

        get("/lineups/:id/edit", (request, response) ->{

            int lineUpId = Integer.parseInt(request.params(":id"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);
            List<Venue> venues = DBHelper.getAll(Venue.class);

            LocalDate date = LocalDate.parse(request.queryParams("date"));

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/lineups/edit.vtl");
            model.put("lineUp", lineUp);
            model.put("venues", venues);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        6. #UPDATE : post '/className/:id'

        post("/lineups/:id", (request, response) -> {

//            Get the new/updated parameters for the Line Up(find by id) from the form
//            Update the LineUp object from that form (set values)
//            add the new LineUp object to the list (use DBHelper to save/update venue to db)
//            render the original itinerary template, passing it the updated list

            int lineUpId = Integer.parseInt(request.params(":id"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);

            LocalDate date = LocalDate.parse(request.queryParams("date"));

            int venueId = Integer.parseInt(request.queryParams("venue"));
            Venue venue = DBHelper.find(venueId, Venue.class);

            lineUp.setDate(date);
//            lineUp.setVenue(venue);

            DBHelper.save(lineUp);

            response.redirect("/lineups");

            return null;
        });

//        7. #DESTROY : get '/className/:id/delete'

        get("/lineups/:id/delete", (request, response) ->{

            int lineUpId = Integer.parseInt(request.params(":id"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);

            DBHelper.delete(lineUp);

            response.redirect("/lineups");
            return null;
        });

    }
}
