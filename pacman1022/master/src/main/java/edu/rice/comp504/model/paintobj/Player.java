package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StraightStrategy;

import java.awt.*;
import java.util.Observable;

public class Player extends APaintObject{
    private static APaintObject singleton;
    private int radius;
    //private int lifeNum;
    private Boolean ifPower;
    private IUpdateStrategy uStrategy;

    private Player(Point loc, int radius){
        this.color = "yellow";
        this.type = "player";
        this.loc = loc;
        this.vel = new Point(0, 0);
        this.radius = radius;
        this.uStrategy = StraightStrategy.makeStrategy();
        //this.lifeNum = 3;
        this.ifPower = false;
    }

    public static APaintObject makePlayer(Point loc, int radius) {
        if (singleton == null) {
            singleton = new Player(loc, radius);
        }
        return singleton;
    }


    public boolean getIfPower() {
        return this.ifPower;
    }

    public void setIfPower(boolean ifPower) {
        this.ifPower = ifPower;
    }

    public boolean collision(APaintObject apo) {
        return false;
    }

    /**
     * Update the state of the paintobj using strategies associated with the paintobj.
     */
    public void update(Observable obs, Object o) {
        this.uStrategy.updateState(this);
        //((IPaintObjCmd)o).execute(this);
        //nextLocation(vel.x, vel.y);
    }
}
