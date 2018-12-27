package edu.rice.comp504.controller;

import com.google.gson.Gson;

import edu.rice.comp504.model.ball.Straightball;
import edu.rice.comp504.model.DispatchAdapter;
import java.awt.*;
import edu.rice.comp504.model.ball.ABall;
import static spark.Spark.*;


/**
 * The controller creates the adapter and sets up the communication between the adapter and the view as well as the
 * adapter and the model
 */
public class BallWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");
        Gson gson = new Gson();
        DispatchAdapter DispatchAdapter1=new DispatchAdapter();


        get("/ball/:kind", (req, res) -> {


            //System.out.println(req.params(":kind"));

            return gson.toJson(DispatchAdapter1.loadBall(req.params(":kind")));






        });
        // clear all the objects
        get("/clear",(req, res) -> {
            DispatchAdapter1.deleteObservers();
            return gson.toJson(null);
        });
        // update the ball positions
        get("/update", (req, res) -> {

            DispatchAdapter1.updateBallWorld();
            String jsonObject = gson.toJson(DispatchAdapter1);
            System.out.println(DispatchAdapter1.countObservers());
            return gson.toJson(jsonObject);

        });


        redirect.get("/canvas", "/");
    }
}
