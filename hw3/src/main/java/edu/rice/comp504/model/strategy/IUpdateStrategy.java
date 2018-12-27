package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

/**
 * All ball strategies must implement the IUpdateStrategy interface
 */
public interface IUpdateStrategy {


    /**
     * Get the name of the strategy
     * @return The strategy name
     */
    public String getName();

    /**
     * Update the context based on the strategy
     * @param context The ball to apply the strategy to
     */
    public void updateState(Ball context);

}
