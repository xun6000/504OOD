package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.NullStrategy;

import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;

public class CompositePaintObj extends APaintObject {
    //private int radius;
    // a LinkedList to contain the children and the types of the children
    private APaintObject[] children;
    private String[] subtype;
    public String[] getSubtype(){return this.subtype;}
    public APaintObject[] getchild(){return this.children;}
    /**
     * Ball constructor
     * @param loc
     * @param vel
     * @param color
     * @param type
     * @param strategy
     */
//    public CompositePaintObj(Point loc, Point vel, String color, String type, IUpdateStrategy strategy){
//        //super(loc, vel, color, type, strategy);
//        this.type = "composite";
//        this.radius = APaintObject.getRandomInt(10,40);
//
//    }
    public CompositePaintObj(APaintObject[] children, IUpdateStrategy strategy,String[] childlist){
        super(children,strategy,childlist);
        this.type = "composite";
        this.children=children;
        this.subtype=childlist;

    }

    /**
     * Get the radius of the ball
     * @return radius
     */
    public int getSize(){
        return radius;
    }

    /**
     * Set the radius of the ball
     * @param size the radius of the ball
     */
    public void setSize(int size){
        this.radius = size;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @param dims  The canvas dimensions.  The canvas width (x) and canvas height (y).
     */
    public void collision() {
        System.out.println("check the dismension");
//        System.out.println(dims.x);
       System.out.println(loc.x );
        System.out.println(radius );
        int maxX = 800;
        int maxY = 800;
        if (loc.x < radius){
            loc.x = 2 * (radius) - loc.x;
            vel.x = -vel.x;
        }
        if (loc.x > maxX - radius){
            loc.x = 2 * (maxX - radius) - loc.x;
            vel.x = -vel.x;
        }
        if (loc.y <  radius){
            loc.y = 2 * (radius) - loc.y;
            vel.y = -vel.y;
        }
        if (loc.y > maxY - radius){
            loc.y = 2 * (maxY - radius) - loc.y;
            vel.y = -vel.y;
        }
    }

    /**
     * Rotate the ball.
     * @param angle  The angle that determines how far to rotate the ball.
     */
    public void rotate(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        int new_vx = (int) Math.round((double) vel.x * cosA - (double) vel.y * sinA);
        int new_vy = (int) Math.round((double) vel.x * sinA + (double) vel.y * cosA);
        vel.x = new_vx;
        vel.y = new_vy;
    }
    public void update(Observable obs, Object o) {

        ((IPaintObjCmd) o).execute(this);
    }
}
