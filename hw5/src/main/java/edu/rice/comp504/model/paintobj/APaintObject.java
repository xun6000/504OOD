package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * The APaintObject class provides an abstract representation of all the objects
 * in the ShapeWorld.
 */
public abstract class APaintObject implements Observer {
    Point loc;
    Point vel;
    IUpdateStrategy strategy;
    String color;
    String type;
    Point dims;

    int radius;
    private double roatangle;
    private int vroate;
    private static final int MIN_R = 10;
    private static final int MAX_R = 40;
    private static final int VEL_LIM = 50;
    // Set ranges of random variables
    public static int getRandomInt(int low, int high){
        Random rand = new Random();
        int randomInt = low + rand.nextInt((high - low) + 1);
        return randomInt;
    }
    // generate random color
    public static String getRandomColor(){
        int r = getRandomInt(0, 255);
        int g = getRandomInt(0, 255);
        int b = getRandomInt(0, 255);
        String randomColor = "rgb(" + String.valueOf(r)
                + ", " + String.valueOf(g)
                + ", " + String.valueOf(b) + ")";
        return randomColor;
    }

    /**
     * Constructor
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param color  The object color
     * @param type  The object type (e.g. ball, pentagon)
     * @param strategy  The object update strategy
     */
    public APaintObject(Point loc, Point vel, String color, String type, IUpdateStrategy strategy){
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;
        this.vroate=(int)((Math.random()*(30)));
        this.roatangle=0;

    }
    // special constructor for composite object
    public APaintObject(APaintObject[] children,IUpdateStrategy strategy,String[] childlist){
        this.strategy = strategy;

    };



    //set velocity
    public void setVelocity(int a, int b) {
        this.vel.x=a;
        this.vel.y=b;
    }
    //set setStrategy
    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }
    // get rotationangle
    public double getrotationangle(){
        return this.roatangle;

    }
    // get rotation velocity
    public int getvroate() {
        return vroate;
    }
    // set rotationangle
    public void setangle(double c){

        this.roatangle=c;
    }

    /**
     * Rotate the paintable object
     * @param angle  The angle to rotate the paintable object
     */
    public abstract void rotate(double angle);

    /**
     * Detects collision between a paint and a wall in the paint world.  Change direction if paint collides with a wall.
     * @param dims  The canvas dimensions
     */
    public abstract void collision();

    /**
     * Get the size of the shape, if square, get the radius, if square, get the side length
     */
    public abstract int getSize();

    /**
     * Set the size of the shape
     * @param size the radius of a ball of side length of a square
     */
    public abstract void setSize(int size);

    /**
     * Get the paintable object type
     * @return The paintable object type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point getLocation() { return loc; }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }


    /**
     * Get the paintable object color
     * @return The paintable object color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the paintable object color
     * @param color The new color
     */

    public void setColor(String color) {

        this.color = color;
    }
    // set radius
    public void setRadius(int b) {

        this.radius = b;
    }

    /**
     * Compute the next location of the paint in the paint world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        loc.x += velX;
        loc.y += velY;
    }

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */

    public  Point getVelocity() { return vel; }

    public IUpdateStrategy getStrategy() { return strategy; }

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
    }

    /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return dims;
    }

    /**
     * Generate a random int for position, velocity and color.
     * @param low, low bound
     * @param high, high bound
     * @return The random int in this range (both bounds are included).
     */


    /**
     * generate a random color of the ball.
     * @return a string for the random color.
     */


}
