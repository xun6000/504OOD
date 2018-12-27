package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class RandomStrategy implements IUpdateStrategy {
    public static RandomStrategy Singleton = new RandomStrategy();

    /**
     * Constructor
     */
    public RandomStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "RandomStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        //System.out.println("i am updating");

        int vx= (int)((Math.random()*20))-10;
        int vy=(int)((Math.random()*20))-10;
        //context.setVelocity(context.getVelocity().x, 0);
        context.nextLocation(vx, vy);
        context.collision();
    }
}
