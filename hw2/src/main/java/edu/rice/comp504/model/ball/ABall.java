package edu.rice.comp504.model.ball;


import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.awt.*;
import java.util.Observer;
import java.lang.Math;
/**
 * Abstract ball that will be extended by all concrete type balls.   The abstract ball implements the Observer
 * interface enabling all concrete balls to be updated when the dispatcher, which is an Observable,
 */
public abstract class ABall implements Observer {
    private Point loc;
    private String color;
    private int radius;
    private Point vel;

    /**
     * Constructor for ABall
     * @param loc The ball location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The ball radius.
     * @param vel The ball velocity.  The velocity is a vector with an x and y component.
     * @param color The ball color.
     */
    public ABall(Point loc, int radius, Point vel, String color) {
        this.loc = loc;
        this.radius = radius;
        this.vel = vel;
        this.color = color;
    }
    public ABall(){};
    /**
     * Get the ball name
     * @return ball name
     */
    public abstract String getName();

    /**
     * Get the ball color.
     * @return ball color
     */
    public String getColor() {return color; }

    /**
     * Set the ball color.
     * @param color The new ball color
     */
    public void setColor(String color) {

        this.color = color; }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() { return loc; }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Compute the next location of the ball in the ball world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() { return vel; }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() { return radius; }

    /**
     * Set the radius of the ball.
     * @param r The ball radius.
     */
    public void setRadius(int r) { radius = r; }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     */
    public void collision() {
    }

    /**
     * Move the ball to the next location in the ball world
     */
    public void move() {
    }

    /**
     * Rotate the ball
     * @param angle  The angle that determines how much to rotate the ball
     */
    public void rotate(double angle) {
    }

    /**
     * Update the ball x,y coordinate in the ball world
     */
    public abstract void updateBallLoc();

    /**
     * Update the ball attributes.   This may include the radius and color.
     */
    public abstract void updateBallAttrs();

    public String gencolor(){
        String a;
        int r1=(int)(Math.random()*255);
        int g1=(int)(Math.random()*255);
        int b1=(int)(Math.random()*255);

        String r= String.valueOf(r1);
        String g= String.valueOf(g1);
        String b= String.valueOf(b1);


        return "rgb"+"("+r+","+g+","+b+")";
    }
}
