package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class NullStrategy implements IUpdateStrategy {
    public static NullStrategy Singleton = new NullStrategy();

    /**
     * Constructor
     */
    public NullStrategy() {



    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "NullStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        System.out.println("i am updating");

        //context.setVelocity(context.getVelocity().x, 0);
        context.nextLocation(0, 0);
    }
}
