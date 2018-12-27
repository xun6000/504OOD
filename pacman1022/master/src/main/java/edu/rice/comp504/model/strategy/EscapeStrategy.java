package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public class EscapeStrategy implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private EscapeStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new EscapeStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "escapeStrategy";
    }


    public void interact(APaintObject src, APaintObject dest) {
        //TODO ghost escape from player
    }

}
