package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.Gameboard;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.strategy.IInteractStrategy;

/**
 *  Two object interact with each other
 */
public class InteractCmd implements IPaintObjCmd{
    private Gameboard dis;

    public InteractCmd(Gameboard dis) {
        this.dis = dis;
    }

    public void execute(APaintObject context) {
        if(context.collision(dis.getPlayer())) {
            if (context.getType().equals("bean")) {
                //TODO check if eaten
                //dis.deleteObserver(context);
                dis.setBeanNum(dis.getBeanNum() - 1);
                dis.setScore(dis.getScore() + 10);
            } else if (context.getType().equals("ghost")) {
                //TODO check ghostType
                //TODO lifeNum-- || score++
            } else if (context.getType().equals("power")) {
                //TODO check if eaten
                dis.setScore(dis.getScore() + 100);
                //TODO change ghostType
            }
            IInteractStrategy is = context.getStrategy();
            is.interact(dis.getPlayer(), context);
        }
    }

}
