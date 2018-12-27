package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class ChangeColorStrategy implements IUpdateStrategy {

    /**
     * Constructor
     */
    public static ChangeColorStrategy Singleton = new ChangeColorStrategy();
    public ChangeColorStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "ChangeColorStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(Ball context) {
        //System.out.println("i am updating");
        String c= context.gencolor();
        context.setColor(c);
        Point v=context.getVelocity();
        //context.setVelocity(context.getVelocity().x, 0);
        context.nextLocation(v.x, v.y);

    }



}
