package edu.rice.comp504.model;

import java.awt.*;

/**
 *  The abstract shape to be painted on the canvas.  All shapes extending this class must define a name and a
 *  paint method
 */
public class Rectangle  extends edu.rice.comp504.model.AShape {
    private double width;
    private double height;
    // TO Define a rectangle, we need the width and height position and color
    public Rectangle(Point loc,double a,double b,String c){
        this.width=a;
        this.height=b;
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
