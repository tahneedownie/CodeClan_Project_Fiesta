package controllers;

import db.DBHelper;
import models.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class VisitorController {

    public VisitorController() {
        setupEndPoints();
    }

    private void setupEndPoints(){

//        1. #INDEX : get '/className'

        get("/visitors", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Visitor> visitors = DBHelper.getAll(Visitor.class);

            model.put("template", "templates/visitors/index.vtl");
            model.put("visitors", visitors);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        2. #NEW : get '/className/new'

        get("/visitors/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            List<Visitor> visitors = DBHelper.getAll(Visitor.class);

            model.put("template", "templates/visitors/create.vtl");
            model.put("visitors", visitors);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//        3. #CREATE : post '/className'

        post("/visitors", (request, response) -> {

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");

            Visitor visitor = new Visitor(firstName, lastName);
            DBHelper.save(visitor);

            response.redirect("/visitors");

            return null;
        });

//        4. #SHOW : get '/className/:id'

        get("/visitors/:id", (request, response) -> {

            int visitorId = Integer.parseInt(request.params(":id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/visitors/show.vtl");
            model.put("visitor", visitor);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        5. #EDIT : get '/className/:id/edit'

        get("/visitors/:id/edit", (request, response) -> {

            int visitorId = Integer.parseInt(request.params(":id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);

            Map<String, Object> model = new HashMap<>();

            model.put("template", "templates/venues/edit.vtl");
            model.put("visitor", visitor);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        6. #UPDATE : post '/className/:id'

        post("/visitors/:id", (request, response) -> {

            int visitorId = Integer.parseInt(request.params(":id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");

            visitor.setFirstName(firstName);
            visitor.setLastName(lastName);

            DBHelper.save(visitor);

            response.redirect("/visitors");

            return null;
        });

//        7. #DESTROY : get '/className/:id/delete'

        get("/visitors/:id/delete", (request, response) -> {

            int visitorId = Integer.parseInt(request.params(":id"));
            Visitor visitor = DBHelper.find(visitorId, Visitor.class);

            DBHelper.delete(visitor);

            response.redirect("/visitors");
            return null;
        });

    }

}
