package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

/**
 * The CompositeStrategy uses the Composite Design Pattern to update the ball.   As a result, it calls
 * the updateState method on each child
 */
public class CompositeStrategy implements IUpdateStrategy {
    private IUpdateStrategy[] children;
    //public static CompositeStrategy Singleton = new CompositeStrategy();

    /**
     * Constructor
     */
    public CompositeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the ball name
     * @return ball name
     */
    public String getName() {
        return "composite";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        //children[0].updateState(context);
        //children[1].updateState(context);
        for (IUpdateStrategy strategy: children) {
            strategy.updateState(context);
            //System.out.println(strategy.getName());
        }
    }
}
