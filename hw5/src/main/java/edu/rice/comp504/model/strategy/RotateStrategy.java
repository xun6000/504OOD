package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class RotateStrategy implements IUpdateStrategy {
    public static RotateStrategy Singleton = new RotateStrategy();

    /**
     * Constructor
     */
    public RotateStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "RotateStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {

//
        int vtotal = context.getvroate();
        //Point pos=context.getLocation();
        double angle=context.getrotationangle();
        context.setangle(angle + 0.5);
        int vx = (int ) ((double) (vtotal)*Math.cos(angle));
        int vy = (int ) ((double) (vtotal)*Math.sin(angle));
        context.setVelocity(vx, vy);
        context.nextLocation(vx, vy);
        context.collision();
    }
}
