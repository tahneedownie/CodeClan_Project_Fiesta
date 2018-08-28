package controllers;

import db.DBHelper;
import models.Comedian;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ComedianController {

    public ComedianController() { setupEndPoints(); }

    private void setupEndPoints(){

//        RESTFUL ROUTES

//        1. #INDEX : get '/className'

        get("/comedians", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Comedian> comedians = DBHelper.getAll(Comedian.class);

            model.put("template", "templates/comedians/index.vtl");
            model.put("comedians", comedians);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        //        2. #NEW : get '/className/new'

        get("/comedians/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Comedian> comedians = DBHelper.getAll(Comedian.class);
            Locale.Category[] nationalities = Locale.Category.values();


            model.put("template", "templates/comedians/create.vtl");
            model.put("comedians", comedians);
            model.put("nationalities", nationalities);


            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());



        //        3. #CREATE : post '/className'

        post("/comedians", (request, response) -> {

//            Get the data from the form
//            create a new Comedian object from that form
//            add the new Comedian object to the list
//            render the original itinerary template, passing it the updated list

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String type = request.queryParams("type");
            String manager = request.queryParams("manager");

            Locale.Category[] nationalities = Locale.Category.values();
            Locale nationality = Locale.getDefault(Locale.Category.valueOf(request.queryParams("nationality")));

            Comedian comedian = new Comedian(firstName, lastName, type, manager, nationality);
            DBHelper.save(comedian);

            response.redirect("/comedians");

            return null;
        });

        //        4. #SHOW : get '/className/:id'

        get("/comedians/:id", (request, response) -> {

            int comedianId = Integer.parseInt(request.params(":id"));
            Comedian comedian = DBHelper.find(comedianId, Comedian.class);
            Locale.Category[] nationalities = Locale.Category.values();

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/comedians/show.vtl");
            model.put("comedian", comedian);
            model.put("nationalities", nationalities);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //        5. #EDIT : get '/className/:id/edit'

        get("/comedians/:id/edit", (request, response) ->{

            int comedianId = Integer.parseInt(request.params(":id"));
            Comedian comedian = DBHelper.find(comedianId, Comedian.class);
            Locale.Category[] nationalities = Locale.Category.values();

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/comedians/edit.vtl");
            model.put("comedian", comedian);
            model.put("nationalities", nationalities);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //        6. #UPDATE : post '/className/:id'

        post("/comedians/:id", (request, response) -> {

//            Get the new/updated parameters for the comedian(find by id) from the form
//            Update the Comedian object from that form (set values)
//            add the new Comedian object to the list (use DBHelper to save/update venue to db)
//            render the original itinerary template, passing it the updated list

            int comedianId = Integer.parseInt(request.params(":id"));
            Comedian comedian = DBHelper.find(comedianId, Comedian.class);

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String type = request.queryParams("type");
            String manager = request.queryParams("manager");

            Locale.Category[] nationalities = Locale.Category.values();
            Locale nationality = Locale.getDefault(Locale.Category.valueOf(request.queryParams("nationality")));

            comedian.setFirstName(firstName);
            comedian.setLastName(lastName);
            comedian.setType(type);
            comedian.setManager(manager);
            comedian.setNationality(nationality);

            DBHelper.save(comedian);

            response.redirect("/comedians");

            return null;
        });



        //        7. #DESTROY : get '/className/:id/delete'

        get("/comedians/:id/delete", (request, response) ->{

            int comedianId = Integer.parseInt(request.params(":id"));
            Comedian comedian = DBHelper.find(comedianId, Comedian.class);

            DBHelper.delete(comedian);

            response.redirect("/comedians");
            return null;
        });


    }

}
