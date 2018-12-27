package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.paintobj.APaintObject;
import java.awt.*;
import static spark.Spark.*;


public class ShapeWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatcherAdapter dis = new DispatcherAdapter();


        // load the object
        post("/load", (request, response) -> {

            APaintObject AA = dis.loadPaintObj(request.body());
            String a=gson.toJson(dis);
            System.out.println(a);
            return gson.toJson(dis);
            //return gson.toJson(dis.loadPaintObj(sig[0]+"&"+sig[1],sig[2]));

        });

        post("/switch", (request, response) -> {
            // change the switcher

            dis.switchStrategy(request.body());

            return gson.toJson(dis);
        });

        get("/update", (request, response) -> {
            dis.updateBallWorld();
            String a=gson.toJson(dis);
            System.out.println(a);
            return gson.toJson(dis);
        });
        //sent the dimension
        get("/canvas/:width/:height", (request, response) -> {

            dis.setCanvasDims(new Point (Integer.valueOf(request.params(":width")),Integer.valueOf(request.params(":height"))));

            return gson.toJson(null);
        });
        // clear the objects
        get("/clear", (request, response) -> {
            dis.deleteObservers();
            return gson.toJson(null);
        });

    }
}
