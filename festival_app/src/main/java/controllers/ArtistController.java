package controllers;

import db.DBHelper;
import models.Artist;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ArtistController {

    public ArtistController() {
        setupEndPoints();
    }

    private void setupEndPoints(){

//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/artists", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Artist> artists = DBHelper.getAll(Artist.class);

            model.put("template", "templates/artists/index.vtl");
            model.put("artists", artists);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }


}
