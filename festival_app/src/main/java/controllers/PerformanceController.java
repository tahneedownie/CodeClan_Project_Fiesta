package controllers;

import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class PerformanceController {

    public PerformanceController() {
        setupEndPoints();
        }

    private void setupEndPoints() {

//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/performances", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Performance> performances = DBHelper.getAll(Performance.class);

            model.put("template", "templates/performances/index.vtl");
            model.put("performances", performances);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


//        2. #NEW : get '/className/new'

        get("/performances/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Performance> performances = DBHelper.getAll(Performance.class);
            List<LineUp> lineUps = DBHelper.getAll(LineUp.class);

            model.put("template", "templates/lineups/create.vtl");
            model.put("performances", performances);
            model.put("lineUps", lineUps);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        3. #CREATE : post '/className'

        post("/performances", (request, response) -> {

//            Get the data from the form
//            create a new Performance object from that form
//            add the new Performance object to the list
//            render the original itinerary template, passing it the updated list

            LocalTime time = LocalTime.parse(request.queryParams("time"));
            int duration = Integer.parseInt(request.queryParams("duration"));

            int lineUpId = Integer.parseInt(request.queryParams("lineUp"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);

            Performance performance = new Performance(time, duration, lineUp);
            DBHelper.save(performance);

            response.redirect("/performances");

            return null;
        });

//        4. #SHOW : get '/className/:id'

        get("/performances/:id", (request, response) -> {

            int performanceId = Integer.parseInt(request.params(":id"));
            Performance performance = DBHelper.find(performanceId, Performance.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/lineups/show.vtl");
            model.put("performance", performance);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        5. #EDIT : get '/className/:id/edit'

        get("/performances/:id/edit", (request, response) ->{

            int performanceId = Integer.parseInt(request.params(":id"));
            Performance performance = DBHelper.find(performanceId, Performance.class);
            List<LineUp> lineUps = DBHelper.getAll(LineUp.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/performances/edit.vtl");
            model.put("performance", performance);
            model.put("lineUps", lineUps);


            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        6. #UPDATE : post '/className/:id'

        post("/performances/:id", (request, response) -> {

//            Get the new/updated parameters for the Performance(find by id) from the form
//            Update the Performance object from that form (set values)
//            add the new Performance object to the list (use DBHelper to save/update venue to db)
//            render the original itinerary template, passing it the updated list

            int performanceId = Integer.parseInt(request.params(":id"));
            Performance performance = DBHelper.find(performanceId, Performance.class);

            LocalTime time = LocalTime.parse(request.queryParams("time"));
            int duration = Integer.parseInt(request.queryParams("duration"));
            int lineUpId = Integer.parseInt(request.queryParams("lineUp"));
            LineUp lineUp = DBHelper.find(lineUpId, LineUp.class);

            performance.setTime(time);
            performance.setDuration(duration);
            performance.setLineUp(lineUp);

            DBHelper.save(performance);

            response.redirect("/performances");

            return null;
        });

//        7. #DESTROY : get '/className/:id/delete'

        get("/performances/:id/delete", (request, response) ->{

            int performanceId = Integer.parseInt(request.params(":id"));
            Performance performance = DBHelper.find(performanceId, Performance.class);

            DBHelper.delete(performance);

            response.redirect("/performances");
            return null;
        });

    }




}
