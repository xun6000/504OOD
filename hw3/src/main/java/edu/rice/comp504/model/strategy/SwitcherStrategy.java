package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

public class SwitcherStrategy implements IUpdateStrategy {


    public static SwitcherStrategy Singleton = new SwitcherStrategy(null);
    /**
     * This is the update strategy for Switcher, it has to be known by the model
     * for changing update rules, while we still have to keep it private
     */
    private IUpdateStrategy strategy; /* classname has to be visible between packages */

    public SwitcherStrategy(IUpdateStrategy strategy) {
        setStrategy(strategy);
    }
    @Override
    public String getName() {
        return strategy.getName();
    }

    /**
     * Get current strategy for current switchers
     * @return the current strategy
     */
    public IUpdateStrategy getStrategy() {
        return strategy;
    }

    /**
     * Set the update strategy for the switcher
     * @param strategy intended strategy
     */
    public void setStrategy(IUpdateStrategy strategy) { //"setStrategy" accessor-type method to change the strategy
        this.strategy = strategy;
    }

    /**
     * Update the ball with current selected strategy in
     * switcher
     * @param context, the host ball
     */
    @Override
    public void updateState(Ball context) {
        strategy.updateState(context);
    }


}
