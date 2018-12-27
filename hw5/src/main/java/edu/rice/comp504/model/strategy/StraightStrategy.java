package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import java.awt.*;
/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class StraightStrategy implements IUpdateStrategy {
    //private String name=getName();
    public static StraightStrategy Singleton = new StraightStrategy();

    /**
     * Constructor
     */
    public StraightStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "StraightStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        System.out.println("i am updating");
        Point v=context.getVelocity();
        //context.setVelocity(context.getVelocity().x, 0);
        context.nextLocation(v.x, v.y);
        context.collision();
    }
}