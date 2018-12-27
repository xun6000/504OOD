package edu.rice.comp504.model.ball;

import java.awt.*;
import java.util.Observable;

/**
 * Created by gy12 on 9/10/18.
 */
public class Changingcolorball extends ABall{
    private double r;
    private String c;
    private Point l;
    private int velx;
    private int vely;
    public Changingcolorball() {
        super();
        int randr=(int)((Math.random()*10))+3;
        int randx=(int)((Math.random()*400));
        int randy=(int)((Math.random()*200));
        this.r = randr;
        this.c=gencolor();
        this.l=new Point (randx,randy);
        this.velx=(int)((Math.random()*20))-10;
        this.vely=(int)((Math.random()*20))-10;

    }
    @Override
    public String getName() {

        return "changing_color";
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

    public  double  getradius() {
        return  this.r;

    }

    @Override
    public void update(Observable o, Object arg) {
        //
        //System.out.println(this.l.x);
        this.c=gencolor();
        if (this.l.x+this.velx<this.r||this.l.x+this.velx>500-this.r){
            this.velx=-this.velx;
            this.l.x=this.l.x+this.velx;
        }
        else{
            this.l.x=this.l.x+this.velx;

        }
        if (this.l.y+this.vely<this.r ||this.l.y+this.vely>500-this.r){
            this.vely=-this.vely;
            this.l.y=this.l.y+this.vely;
        }
        else{
            this.l.y=this.l.y+this.vely;

        }



    }
}
