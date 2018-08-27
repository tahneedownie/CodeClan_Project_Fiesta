package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        VelocityTemplateEngine banana = new VelocityTemplateEngine();
        staticFileLocation("/public/");

        get("/", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/home_page.vtl");
        }, banana);

        get("/venues", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/venues.vtl");
        }, banana);

        get("/add_venue", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/add_venue.vtl");
        }, banana);

        get("/artists", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/artists.vtl");
        }, banana);

        get("/add_artist", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/add_artist.vtl");
        }, banana);

        get("/visitors", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/visitors.vtl");
        }, banana);

        get("/add_visitor", (request, response) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "/templates/add_visitor.vtl");
        }, banana);



    }
}
