package edu.rice.comp504.controller;

import edu.rice.comp504.model.Gameboard;
import edu.rice.comp504.model.paintobj.APaintObject;
import com.google.gson.Gson;

import java.awt.*;

import static spark.Spark.*;

/**
 * The shape world controllers communicates to the model make and update paint object requests from the view and
 * communicates to the view new paint objects and updates to the existing paint objects
 */
public class PacManController {

    public static void main(String[] args) {
        staticFiles.location("/public");

        port(getHerokuAssignedPort());
        get("/hello", (req, res) -> "Hello Heroku World");

        Gson gson = new Gson();
        Gameboard dis = new Gameboard();

        get("/update", (request, response) -> {
            dis.updateGameboard();
            System.out.println(gson.toJson(dis));
            return gson.toJson(dis);
        });

        get("/canvas", (request, response) -> {
            dis.setCanvasDims(new Point(Integer.parseInt(request.queryParams("width")),
                    Integer.parseInt(request.queryParams("height"))));
            return gson.toJson(dis);
        });


        get("/move/:direction", (request, response) -> {
            String direction = request.params(":direction");
            System.out.println("direction->"+direction);
            dis.move(Integer.parseInt(direction));
            return gson.toJson(dis);
        });


        get("/start", (request, response) -> {
            dis.start();
            return  gson.toJson(dis);
        });

//        get("/score", (request, response) -> {
//            return null;
//        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
