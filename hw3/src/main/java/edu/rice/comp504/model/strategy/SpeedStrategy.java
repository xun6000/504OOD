package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

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
    public void updateState(Ball context) {
        //System.out.println("i am updating");
        Point v=context.getVelocity();
        context.setVelocity(v.x+3, v.y+3);
        context.nextLocation(v.x+3, v.y+3);
    }
}
