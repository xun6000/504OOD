package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class WallStrategy implements IInteractStrategy{

    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private WallStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new WallStrategy();
        }
        return singleton;
    }

    public String getName() {
        return "wallStrategy";
    }

    @Override
    public void interact(APaintObject src, APaintObject dest) {
        // TODO check if src is player or ghost
        src.nextLocation(-1 * src.getVelocity().x, -1 * src.getVelocity().y);
        src.setVelocity(new Point(0, 0));
    }
}
