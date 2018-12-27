package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class SpeedStrategy implements IUpdateStrategy {
    public static SpeedStrategy Singleton = new SpeedStrategy();

    /**
     * Constructor
     */
    public SpeedStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "SpeedStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        //System.out.println("i am updating");
        Point v=context.getVelocity();
        int dx = 3;
        int dy=3;

        if (v.x<0){
            dx=-3;
        }
        if (v.x<0){
            dy=-3;
        }
        context.setVelocity(v.x+dx, v.y+
        dy);
        context.nextLocation(v.x+dx, v.y+dy);
        context.collision();
    }
}
