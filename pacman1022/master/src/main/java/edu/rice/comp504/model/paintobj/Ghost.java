package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.Gameboard;
import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.GhostCollidePlayerStrategy;
import edu.rice.comp504.model.strategy.IInteractStrategy;

import java.awt.*;
import java.util.Observable;

public class Ghost extends APaintObject{
    private Point size;
    private int ghostType;
    private IInteractStrategy moveStrategy;

    /**
     * Constructor for Banner
     * @param loc The paintobj location.  The origin (0,0) is the upper left corner of the canvas.
     */
    public Ghost(Point loc, Point size, int ghostType, IInteractStrategy moveStrategy) {
        this.loc = loc;
        this.size = size;
        this.vel = new Point(0, -1);
        this.type = "ghost";
        this.ghostType = ghostType;
        this.strategy = new GhostCollidePlayerStrategy();
        this.moveStrategy = moveStrategy;
    }

    public boolean collision(APaintObject apo) {
        //TODO check collide with apo
        return false;
    }


    public void update(Observable obs, Object o) {
        this.moveStrategy.interact(this, ((Gameboard) obs).getPlayer());
        ((IPaintObjCmd)o).execute(this);
    }
}
