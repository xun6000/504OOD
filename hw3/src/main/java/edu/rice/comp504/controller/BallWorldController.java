package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatchAdapter;
import java.awt.*;
import static spark.Spark.*;

/**
 * The ball world controller provides the interface between the view and the dispatch adapter. It creates
 * the dispatch adapter and sets the communication between the dispatch adapter and the model.
 */
public class BallWorldController {

    /**
     * Entrypoint to local server
     * @param args  arguments passed to the entrypoint (normally from the command line)
     */
    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        //creat a normal ball
        post("/load", (request, response) -> {

            String jsonObject=gson.toJson(dis.loadBall(request.body()));

            return gson.toJson(jsonObject);
        });





        //creat a switcher ball
        post("/load/switcher", (request, response) -> {

            String jsonObject=gson.toJson(dis.loadBall(request.body()));

            return gson.toJson(jsonObject);
        });

        //change the strategy
        post("/switch", (request, response) -> {
            System.out.println(request.body());
            dis.switchStrategy(request.body());

            String jsonObject=gson.toJson(dis);
            return gson.toJson(jsonObject);
        });
        //update the position
        get("/update", (request, response) -> {
            dis.updateBallWorld();
            String jsonObject=gson.toJson(dis);
            System.out.println(jsonObject);
            return gson.toJson(jsonObject);
        });
        //clear the canvas
        get("/clear", (request, response) -> {
            //dis.clear();
            dis.deleteObservers();
            return gson.toJson(null);
        });
        //get the dimension
        get("/canvas/:width/:height", (request, response) -> {
            System.out.println(Integer.valueOf(request.params(":width")));
            dis.setCanvasDims(new Point (Integer.valueOf(request.params(":width")),Integer.valueOf(request.params(":height"))));
            System.out.println("dimenstion is done");
            return null;
        });

    }
}
