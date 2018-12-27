package edu.rice.comp504.model.ball;

import java.awt.*;
import java.util.Observable;

/**
 * Created by gy12 on 9/10/18.
 */
public class NullBall extends ABall{
    private double r;
    private String c;
    private Point l;
    private int velx;
    private int vely;
    public NullBall() {
        super();
        this.c="#CCCCCC";
        this.velx=0;
        this.vely=0;
        this.l=new Point(-1,-1);
    }
    @Override
    public String getName() {

        return "null";
    }
    public Point getLocation(){

        return new Point(this.l);
    }
    @Override
    public void updateBallLoc() {

    }

    @Override
    public void updateBallAttrs() {

    }

    @Override
    public void update(Observable o, Object arg) {
        //
        //System.out.println(this.l.x);




    }
}
