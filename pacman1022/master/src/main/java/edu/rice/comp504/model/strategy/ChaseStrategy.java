package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class ChaseStrategy implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private ChaseStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new ChaseStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "chaseStrategy";
    }


    public void interact(APaintObject src, APaintObject dest) {
        //TODO ghost chase player
    }

}
