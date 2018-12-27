package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Player;

import java.awt.*;

public class GhostCollidePlayerStrategy implements IInteractStrategy{

    public String getName() {
        return "ghostHuntPlayerStrategy";
    }

    @Override
    public void interact(APaintObject src, APaintObject dest) {
        if (((Player) src).getIfPower()) {
            //TODO: reset this ghost
        } else {
            //TODO: reset player and all ghosts, lifeNum--
        }
    }

}
