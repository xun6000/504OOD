package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;


public class NullStrategy implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private NullStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new NullStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "null";
    }


    public void interact(APaintObject src, APaintObject dest) {

    }
}
