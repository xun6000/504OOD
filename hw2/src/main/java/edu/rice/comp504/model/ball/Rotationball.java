package edu.rice.comp504.model.ball;

import java.awt.*;
import java.util.Observable;

/**
 * Created by gy12 on 9/10/18.
 */
public class Rotationball extends ABall{
    private double r;
    private String c;
    private Point l;
    private int v;
    private int velx;
    private int vely;
    private double theta;
    private double vang;
    private Point rotationcenter;
    private int distance;
    public Rotationball() {
        super();
       int randr=(int)(((Math.random()+1)*20))+3;
        int randx=(int)((Math.random()*400));
        int randy=(int)((Math.random()*200));
        int ball=(int)((Math.random()*20));
        this.rotationcenter=new Point (randx,randy);
        this.theta = (Math.random()*2*3.14);
        this.distance = randr;
        this.r = ball;
        this.c=gencolor();
        this.v=(int)((Math.random()*10));
        int x = (int) (this.distance*Math.cos(this.theta));
        int y = (int) (this.distance*Math.sin(this.theta));
        this.l=new Point (randx+x,randy+y);
        this.velx=(int)((Math.random()*20))-10;
        this.vely=(int)((Math.random()*20))-10;
        this.vang= (Math.random()*2);

    }
    @Override
    public String getName() {

        return "rotation";
    }

    @Override
    public void updateBallLoc() {

    }

    @Override
    public void updateBallAttrs() {

    }
    @Override
    public  Point getVelocity() {
        return  new Point (this.velx,this.vely);

    }

    @Override
    public  Point getLocation() {
        return  new Point (this.l);

    }




    @Override
    public void update(Observable o, Object arg) {
        //
        //System.out.println(this.l.x);
        this.theta = this.theta+this.vang;
        int x = (int) (this.distance*Math.cos(this.theta));
        int y = (int) (this.distance*Math.sin(this.theta));
        this.l=new Point (this.rotationcenter.x+x,this.rotationcenter.y+y);
        this.velx=(int)(this.v*Math.sin(this.theta));
        this.vely=(int)(this.v*Math.cos(this.theta));




    }
}
