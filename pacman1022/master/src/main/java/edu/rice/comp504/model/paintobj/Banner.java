package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.Gameboard;
import edu.rice.comp504.model.strategy.NullStrategy;

import java.awt.*;
import java.util.Observable;

public class Banner extends APaintObject{
    private Point size;
    private String text;
    private Boolean ifShow;

    /**
     * Constructor for Banner
     * @param loc The paintobj location.  The origin (0,0) is the upper left corner of the canvas.
     */
    public Banner(Point loc, Point size) {
        this.loc = loc;
        this.size = size;
        this.color = "red";
        this.vel = new Point(0, 0);
        this.type = "banner";
        this.text = "";
        this.ifShow = false;
        this.strategy = NullStrategy.makeStrategy();
    }

    public boolean collision(APaintObject apo) {
        return false;
    }

    /**
     * Update the state of the paintobj using strategies associated with the paintobj.
     */
    public void update(Observable obs, Object o) {
        //((IPaintObjCmd)o).execute(this);
        if (((Gameboard) obs).getIfOver()) {
            if (((Gameboard) obs).getIfWin()) {
                this.text = "WIN";
            } else {
                this.text = "THIS IS LIFE";
            }
            this.ifShow = true;
        }
    }
}
