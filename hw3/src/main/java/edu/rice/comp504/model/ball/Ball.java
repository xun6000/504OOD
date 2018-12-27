package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StraightStrategy;
//import edu.rice.comp504.model.strategy.StopStrategy;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents one ball in the BallWorld. It implements the Observer class since there will be multiple
 * balls in the BallWorld that need to periodically update their location.
 */
public class Ball implements Observer {
    private Point loc;
    private String color;
    private int radius;
    private Point vel;
    private double roatangle;
    private IUpdateStrategy strategy;
    private int vroate;

    /**
     * Constructor for Ball
     * @param loc The ball location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The ball radius.
     * @param vel The ball velocity.  The velocity is a vector with an x and y component.
     * @param color The ball color.
     * @param strategy The ball strategy determining ball behavior
     */
    public Ball(Point loc, int radius, Point vel, IUpdateStrategy strategy) {

        this.loc=loc;
        this.radius=radius;
        this.vel=vel;
        if (strategy.getName()!="NullStrategy")
        {this.color=gencolor();}
        else{this.color="rgb(0,0,0)";}
        this.strategy=strategy;
        this.roatangle=0;
        this.vroate = (int)((Math.random()*10))+3;






    }

    /**
     * Get the ball color.
     * @return ball color
     */
    public double getrotationangle(){
        return this.roatangle;

    }
    public void setangle(double c ){
        this.roatangle=c;

    }
    public int getvroate(){
        return this.vroate;

    }
    public String getColor() {return this.color; }

    /**
     * Set the ball color.
     * @param c The new ball color
     */
    public void setColor(String c) {
        this.color=c;
    }
    public void setVelocity(int a, int b) {
        this.vel.x=a;
        this.vel.y=b;
    }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() { return this.loc; }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {

    }

    /**
     * Compute the next location of the ball in the ball world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        System.out.println(this.loc);
        this.vel.x=velX;
        this.vel.y=velY;
        System.out.println(this.vel.x);
        System.out.println(this.vel.y);
        if (this.loc.x+this.vel.x<this.radius ||this.loc.x+this.vel.x>500-this.radius){
            this.vel.x=-this.vel.x;
            this.loc.x=this.loc.x+this.vel.x;
        }
        else{
            this.loc.x=this.loc.x+this.vel.x;

        }
        if (this.loc.y+this.vel.y<this.radius ||this.loc.y+this.vel.y>500-this.radius){
            this.vel.y=-this.vel.y;
            this.loc.y=this.loc.y+this.vel.y;
        }
        else{
            this.loc.y=this.loc.y+this.vel.y;

        }




    }
    public void color() {
        this.color=gencolor();



    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() { return this.vel; }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() { return this.radius; }

    /**
     * Set the radius of the ball.
     * @param radius The ball radius.
     */
    public void setRadius(int radius) {
        this.radius=radius;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @param dims  The canvas dimensions.  The canvas width (x) and canvas height (y).
     */
    public void collision(Point dims) {
    }
    public IUpdateStrategy getStrategyname() {
        return this.strategy;
    }

    /**
     * Rotate the ball.
     * @param angle  The angle that determines how far to rotate the ball.
     */
    public void rotate(double angle) {
    }
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
    /**
     * Update the state of the ball using strategies associated with the ball.
     */
    public void update(Observable obs, Object o) {
        this.strategy.updateState(this);


    }
}