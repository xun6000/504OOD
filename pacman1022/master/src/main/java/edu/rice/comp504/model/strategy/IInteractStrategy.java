package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

/**
 * The interface used to determine the interaction strategy when balls collide with each other
 */
public interface IInteractStrategy {

    /**
     * Get the interaction strategy name.
     * @return The interaction strategy name.
     */
    public String getName();

    /**
     * The interaction strategy when two balls collide
     * @param src  The src ball will impose the interaction strategy on the dest ball.
     * @param dest The dest ball behavior will be affected by the src ball interaction strategy
     */
    public void interact(APaintObject src, APaintObject dest);

}
