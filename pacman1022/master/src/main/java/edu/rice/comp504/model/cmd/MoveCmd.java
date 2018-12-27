package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class MoveCmd implements IPaintObjCmd{
    private int dir;

    public MoveCmd(int dir) {
        this.dir = dir;
    }

    public void execute(APaintObject context) {
        switch (dir) {
            case 0:
                context.setVelocity(new Point(0, -1));
                break;
            case 1:
                context.setVelocity(new Point(-1, 0));
                break;
            case 2:
                context.setVelocity(new Point(0, 1));
                break;
            case 3:
                context.setVelocity(new Point(1, 0));
                break;
            default:
                context.setVelocity(new Point(0, 0));
        }


    }
}
