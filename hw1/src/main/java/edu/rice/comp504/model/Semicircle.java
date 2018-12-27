package edu.rice.comp504.model;

import java.awt.*;

/**
 *  The abstract shape to be painted on the canvas.  All shapes extending this class must define a name and a
 *  paint method
 */
public class Semicircle  extends edu.rice.comp504.model.AShape {
    private double radius;
    // for a Semicircle, we need the radius and position, color
    public Semicircle(Point loc,double r,String c){
        this.radius=r;
        paint(loc,c);

    }

    /**
     * Get the shape name
     * @return shape name
     */
    public  String getName(){

        return "Circle";};

    /**
     * Get the shape color.
     * @return shape color
     */
    public String getColor() {return this.color; }


    /**
     * Paint or repaint the shape at a location.  The location will be an x,y coordinate representing the upper
     * lefthand corner of the shape.
     */
    public void paint(Point loc,String c){

        this.loc=loc;
        this.color=c;
    };




}
