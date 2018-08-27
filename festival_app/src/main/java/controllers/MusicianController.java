package controllers;

import db.DBHelper;
import models.MusicGenreType;
import models.Musician;
import models.Venue;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class MusicianController {

    public MusicianController() {
        setupEndPoints();
    }

    private void setupEndPoints(){


//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/musicians", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Musician> musicians = DBHelper.getAll(Musician.class);

            model.put("template", "templates/musicians/index.vtl");
            model.put("musicians", musicians);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


//        2. #NEW : get '/className/new'

        get("/musicians/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Musician> musicians = DBHelper.getAll(Musician.class);
            MusicGenreType[] genres = MusicGenreType.values();

            model.put("template", "templates/musicians/create.vtl");
            model.put("musicians", musicians);
            model.put("genres", genres);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        3. #CREATE : post '/className'

        post("/musicians", (request, response) -> {

//            Get the data from the form
//            create a new Musician object from that form
//            add the new Musician object to the list
//            render the original itinerary template, passing it the updated list

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String type = request.queryParams("type");
            String manager = request.queryParams("manager");

            MusicGenreType[] genres = MusicGenreType.values();
            MusicGenreType genre = MusicGenreType.valueOf(request.queryParams("genre"));

            Musician musician = new Musician(firstName, lastName, type, manager, genre);
            DBHelper.save(musician);

            response.redirect("/musicians");

            return null;
        });

//        4. #SHOW : get '/className/:id'

        get("/musicians/:id", (request, response) -> {

            int musicianId = Integer.parseInt(request.params(":id"));
            Musician musician = DBHelper.find(musicianId, Musician.class);
            MusicGenreType[] genres = MusicGenreType.values();

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/musicians/show.vtl");
            model.put("musician", musician);
            model.put("genres", genres);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        5. #EDIT : get '/className/:id/edit'

        get("/musicians/:id/edit", (request, response) ->{

            int musicianId = Integer.parseInt(request.params(":id"));
            Musician musician = DBHelper.find(musicianId, Musician.class);
            MusicGenreType[] genres = MusicGenreType.values();

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/musicians/edit.vtl");
            model.put("musician", musician);
            model.put("genres", genres);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        6. #UPDATE : post '/className/:id'

        post("/musicians/:id", (request, response) -> {

//            Get the new/updated parameters for the musician(find by id) from the form
//            Update the Musician object from that form (set values)
//            add the new Musician object to the list (use DBHelper to save/update venue to db)
//            render the original itinerary template, passing it the updated list

            int musicianId = Integer.parseInt(request.params(":id"));
            Musician musician = DBHelper.find(musicianId, Musician.class);

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String type = request.queryParams("type");
            String manager = request.queryParams("manager");

            MusicGenreType[] genres = MusicGenreType.values();
            MusicGenreType genre = MusicGenreType.valueOf(request.queryParams("genre"));

            musician.setFirstName(firstName);
            musician.setLastName(lastName);
            musician.setType(type);
            musician.setManager(manager);
            musician.setGenre(genre);

            DBHelper.save(musician);

            response.redirect("/musicians");

            return null;
        });

//        7. #DESTROY : get '/className/:id/delete'

        get("/musicians/:id/delete", (request, response) ->{

            int musicianId = Integer.parseInt(request.params(":id"));
            Musician musician = DBHelper.find(musicianId, Musician.class);

            DBHelper.delete(musician);

            response.redirect("/musicians");
            return null;
        });


    }


}
