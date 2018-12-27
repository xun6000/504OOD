package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class APaintObject implements Observer {
    Point loc;
    Point vel;
    String color;
    String type;
    IInteractStrategy strategy;

    public APaintObject() {}

    /**
     * Constructor
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param strategy  The object update strategy
     */
    public APaintObject(Point loc, Point vel, String color, String type, IInteractStrategy strategy){
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;
    }


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
    public Point getVelocity() { return vel; }

    /**
     * Set the velocity of the paint.
     */
    public void setVelocity(Point vel) { this.vel = vel; }

    /**
     * Set color
     */
    public void setColor(String color) { this.color = color; }


    /**
     * get strategy
     * @return IInteractStrategy
     */
    public IInteractStrategy getStrategy() { return strategy; }

    /**
     * Detects collision
     */
    public abstract boolean collision(APaintObject apo);

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
        ((IPaintObjCmd) o).execute(this);
    }


}
