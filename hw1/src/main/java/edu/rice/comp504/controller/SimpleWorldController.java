package edu.rice.comp504.controller;

import com.google.gson.Gson;
import java.awt.*;
import java.awt.geom.Point2D;
import edu.rice.comp504.model.Circle;
import edu.rice.comp504.model.Square;
import edu.rice.comp504.model.Triangle;
import edu.rice.comp504.model.Rectangle;
import edu.rice.comp504.model.Semicircle;
import static spark.Spark.*;
import java.util.HashMap;
/**
 * The SimpleWorldController is responsible for interfacing between the view and the model.  The model will determine
 * how shape objects are created.  The view is the browser.  The browser has a canvas that renders the shapes.
 * The controller interacts with the view by receiving REST get requests for various shapes.
 */
public class SimpleWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");
        Gson gson = new Gson();
        // create a circle instance with location, radius and color, and return Json
        get("/shape/circle", (req, res) -> {
            Circle circle1 = new Circle(new Point(40,40),25,"#FFFF00");
            //System.out.println(new Gson().toJson(circle1));
            String jsonObject = gson.toJson(circle1);// it can't be changed to gson
            return gson.toJson(jsonObject);


        });
        // create a square instance with location, size and color, and return Json
        get("/shape/square", (req, res) -> {
            Square Square1 = new Square(new Point(200,50),50,"#00FFFF");
            //System.out.println(new Gson().toJson(Square1));
            String jsonObject2 = gson.toJson(Square1);// it can't be changed to gson
            return gson.toJson(jsonObject2);


        });
        // create a triangle instance with location, length and color, and return Json

        get("/shape/triangle", (req, res) -> {
            Triangle Triangle1 = new Triangle(new Point(100,150),75,"#1E90FF");
            //System.out.println(new Gson().toJson(Triangle1));
            String jsonObject2 = gson.toJson(Triangle1);// it can't be changed to gson
            return gson.toJson(jsonObject2);


        });
        // create a rectangle instance with location, width, height and color, and return Json

        get("/shape/rectangle", (req, res) -> {
            Rectangle Rect1 = new Rectangle(new Point(200,200),60,40,"#0000FF");
            //System.out.println(new Gson().toJson(Rect1));
            String jsonObject2 = gson.toJson(Rect1);// it can't be changed to gson
            return gson.toJson(jsonObject2);


        });
        // create a semicircle instance with location, radius and color, and return Json

        get("/shape/semicircle", (req, res) -> {
            Semicircle Semicircle1 = new Semicircle(new Point(40,200),25,"#f00");
            //System.out.println(new Gson().toJson(Semicircle1));
            String jsonObject2 = gson.toJson(Semicircle1);// it can't be changed to gson
            return gson.toJson(jsonObject2);


        });





        redirect.get("/canvas", "/");





    }
}
