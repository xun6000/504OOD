package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.DoorStrategy;

import java.awt.*;
import java.util.Observable;

public class Door extends APaintObject{
    private static APaintObject singleton;

    private Point size;

    private Door(Point loc, Point size){
        this.loc = loc;
        this.size = size;
        this.vel = new Point(0, 0);
        this.color = "pink";
        this.type = "door";
        this.strategy = DoorStrategy.makeStrategy();
    }

    public static APaintObject makeDoor(Point loc, Point size) {
        if (singleton == null) {
            singleton = new Door(loc, size);
        }
        return singleton;
    }

    public boolean collision(APaintObject apo) {
        //TODO check collide with apo
        return false;
    }

    public void update(Observable obs, Object o) {
        ((IPaintObjCmd)o).execute(this);
    }
}
