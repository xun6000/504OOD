package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.WallStrategy;

import java.awt.*;
import java.util.Observable;

/**
 * A vertical wall at a location within the canvas
 */
public class Wall extends APaintObject {
    private Point size;

    /**
     * Constructor for Wall
     * @param loc The wall location.  The origin (0,0) is the upper left corner of the canvas.
     * @param size The size of the wall.
     */
    public Wall(Point loc, Point size) {
        this.loc = loc;
        this.vel = new Point(0, 0);
        this.color = "blue";
        this.type = "wall";
        this.strategy = WallStrategy.makeStrategy();
        this.size = size;
    }

    /**
     * Detects collision between a paintobj and a wall in the paintobj world.  Change direction if paintobj collides with a wall.
     */
    public boolean collision(APaintObject apo) {
        //TODO check collision
        return false;
    }


    /**
     * Update the state of the paintobj using strategies associated with the paintobj.
     */
    public void update(Observable obs, Object o) {
        ((IPaintObjCmd)o).execute(this);
    }
}
