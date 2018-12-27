package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class ChangeSizeStrategy implements IUpdateStrategy {

    /**
     * Constructor
     */
    public static ChangeSizeStrategy Singleton = new ChangeSizeStrategy();

    public ChangeSizeStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "ChangeSizeStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        //System.out.println("i am updating");
        int r= (int)((Math.random()*10))+3;
        context.setRadius(r);
        Point v=context.getVelocity();
        //context.setVelocity(context.getVelocity().x, 0);
        context.nextLocation(v.x, v.y);
        context.collision();
    }



}
