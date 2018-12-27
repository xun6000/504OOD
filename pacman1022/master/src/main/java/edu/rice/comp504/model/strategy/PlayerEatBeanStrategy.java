package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Bean;

public class PlayerEatBeanStrategy implements IInteractStrategy {
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private PlayerEatBeanStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PlayerEatBeanStrategy();
        }
        return singleton;
    }


    public String getName() {
        return "playerEatBeanStrategy";
    }

    @Override
    public void interact(APaintObject src, APaintObject dest) {
        ((Bean) dest).setIfEaten(true);
        dest.setColor("black");
    }
}