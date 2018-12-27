package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.PlayerGetPowerStrategy;

import java.awt.*;
import java.util.Observable;

public class Power extends APaintObject{
    private int radius;
    private Boolean ifEaten;

    /**
     * Constructor for Bean
     * @param loc The paintobj location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The paintobj radius.
     */
    public Power(Point loc, int radius) {
        this.loc = loc;
        this.radius = radius;
        this.vel = new Point(0, 0);
        this.color = "gold";
        this.type = "power";
        this.ifEaten = false;
        this.strategy = PlayerGetPowerStrategy.makeStrategy();
    }

    /**
     * Get the radius of the paintobj.
     * @return The paintobj radius.
     */
    public int getRadius() { return radius; }

    /**
     * Set the radius of the paintobj.
     * @param r The paintobj radius.
     */
    public void setRadius(int r) { radius = r; }


    /**
     * get if eaten
     * @return Boolean
     */
    public Boolean getIfEaten() {
        return this.ifEaten;
    }

    /**
     * Set if eaten
     * @param ifEaten
     */
    public void setIfEaten(boolean ifEaten) {
        this.ifEaten = ifEaten;
    }

    @Override
    public boolean collision(APaintObject apo) {
        //TODO check collide with apo
        return false;
    }

    /**
     * Update the state of the paintobj using strategies associated with the paintobj.
     */
    public void update(Observable obs, Object o) {
        ((IPaintObjCmd)o).execute(this);
    }
}

