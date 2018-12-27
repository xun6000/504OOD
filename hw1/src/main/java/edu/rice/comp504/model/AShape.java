package edu.rice.comp504.model;

import java.awt.*;

/**
 *  The abstract shape to be painted on the canvas.  All shapes extending this class must define a name and a
 *  paint method
 */
public abstract class AShape {
    protected Point loc;
    protected String color;

    /**
     * Get the shape name
     * @return shape name
     */
    public abstract String getName();

    /**
     * Get the shape color.
     * @return shape color
     */
    public String getColor() {return this.color; }


    /**
     * Paint or repaint the shape at a location.  The location will be an x,y coordinate representing the upper
     * lefthand corner of the shape.
     */
    public abstract void paint(Point loc, String c);
}
