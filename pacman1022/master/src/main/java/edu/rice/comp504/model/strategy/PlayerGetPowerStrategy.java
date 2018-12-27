package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.cmd.InteractCmd;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Player;

public class PlayerGetPowerStrategy implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    private PlayerGetPowerStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PlayerGetPowerStrategy();
        }
        return singleton;
    }

    public String getName() {
        return "playerGetPowerStrategy";
    }

    @Override
    public void interact(APaintObject src, APaintObject dest) {
        ((Player) src).setIfPower(true);
        //TODO delete power, score++
        //TODO change all ghostType
    }

}
