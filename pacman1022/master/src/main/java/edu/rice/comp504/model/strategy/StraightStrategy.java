package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class StraightStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;

    /**
     * Constructor
     */
    private StraightStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IUpdateStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new StraightStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "straight";
    }

    /**
     * Update the paintobj state in the paintobj world
     * @param context The paintobj to update
     */
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        context.nextLocation(vel.x, vel.y);
        //context.setColor("blue");
    }
}