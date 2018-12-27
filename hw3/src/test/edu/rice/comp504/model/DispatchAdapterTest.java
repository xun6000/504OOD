package edu.rice.comp504.model;

import junit.framework.TestCase;
import  edu.rice.comp504.model.ball.Ball;
import java.awt.*;
import java.awt.Point;
/**
 * Created by gy12 on 9/19/18.
 */
public class DispatchAdapterTest extends TestCase {

    DispatchAdapter da = new DispatchAdapter();

    Ball stBall=da.loadBall("normal=1");
Ball rotationBall=da.loadBall("normal=5");
    Ball changesizeBall=da.loadBall("normal=3");
    Ball changecolorBall=da.loadBall("normal=6");
    Ball randomBall=da.loadBall("normal=4");
    Ball speedBall=da.loadBall("normal=2");
    Ball lowBall=da.loadBall("normal=7");
    Ball nuBall=da.loadBall("normal=0");
    Ball sw=da.loadBall("switcher=3");
    Ball mulBall=da.loadBall("normal=123");
    //test to creat a strategyball
  public void testsingleball() throws Exception {
      assertEquals("load straight strategy test","StraightStrategy",stBall.getStrategyname().getName());
      assertEquals("load rotation strategy test","RotateStrategy", rotationBall.getStrategyname().getName());
        assertEquals("load changing_size strategy","ChangeSizeStrategy", changesizeBall.getStrategyname().getName());
        assertEquals("load changing_color strategy test","ChangeColorStrategy", changecolorBall.getStrategyname().getName());
        assertEquals("load random strategy test","RandomStrategy", randomBall.getStrategyname().getName());
        assertEquals("load speed strategy test","SpeedStrategy", speedBall.getStrategyname().getName());
        assertEquals("load low strategy test","LowStrategy", lowBall.getStrategyname().getName());



  }
  //test a composite   strategyball
  public void testmuliplestrategies()  {
        assertEquals("load composite strategy test","composite", mulBall.getStrategyname().getName());

    }
    // test the nullstrategy
    public void testunknownball() throws Exception {
        assertEquals("load null ball test","NullStrategy", nuBall.getStrategyname().getName());


    }




    //test the update
    public void testStraightBallupdate(){
        //test straight ball
        Point v =stBall.getVelocity();
        double r = stBall.getRadius();
        Point l= stBall.getLocation();
        int velx = v.x;
        int vely=v.y;
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, stBall.getLocation());

    }
    public void testSpeedBallupdate(){
        //test straight ball
        Point v =speedBall.getVelocity();
        double r = speedBall.getRadius();
        Point l= speedBall.getLocation();
        int velx = v.x;
        int vely=v.y;
        if  (velx>0){
            velx=velx+4;}
        else{
            velx=velx-4;}
        if  (vely>0){
            vely=vely+4;}
        else{
            vely=vely-4;}
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, speedBall.getLocation());













    }
    public void testchangecolorBallupdate(){
        //test straight ball
        Point v =changecolorBall.getVelocity();
        double r = changecolorBall.getRadius();
        Point l= changecolorBall.getLocation();
        int velx = v.x;
        int vely=v.y;
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, changecolorBall.getLocation());











    }
    public void testchangesizeBallupdate(){
        //test straight ball
        Point v =changecolorBall.getVelocity();
        //double r = changecolorBall.getRadius();

        Point l= changecolorBall.getLocation();
        da.updateBallWorld();
        double r = changecolorBall.getRadius();
        int velx = v.x;
        int vely=v.y;
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, changecolorBall.getLocation());











    }
    public void testrandomBallupdate(){
        //test straight ball
        Point v =changecolorBall.getVelocity();
        //double r = changecolorBall.getRadius();
        da.updateBallWorld();
        double r = changecolorBall.getRadius();
        Point l= changecolorBall.getLocation();
        //da.updateBallWorld();

//        int velx = v.x;
//        int vely=v.y;
//        if (l.x+velx<r ||l.x+velx>500-r){
//            velx=-velx;
//            l.x=l.x+velx;}
//        else{
//            l.x=l.x+velx;
//        }
//        if (l.y+vely<r ||l.y+vely>500-r){
//            vely=-vely;
//            l.y=l.y+vely;}
//        else{
//            l.y=l.y+vely;}
//        da.updateBallWorld();
        assertEquals("location ",l, changecolorBall.getLocation());











    }
    public void testrotateBallupdate(){
        int vtotal = changecolorBall.getvroate();
        //Point pos=context.getLocation();
        double angle=changecolorBall.getrotationangle();
        changecolorBall.setangle(angle + 0.2);
        int vx = (int ) ((double) (vtotal)*Math.cos(angle));
        int vy = (int ) ((double) (vtotal)*Math.sin(angle));
        da.updateBallWorld();
        Point v =changecolorBall.getVelocity();

        double r = changecolorBall.getRadius();
        Point l= changecolorBall.getLocation();
        int velx = v.x;
        int vely=v.y;
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, changecolorBall.getLocation());











    }
    public void testLowBallupdate(){
        //test straight ball
        Point v =stBall.getVelocity();
        double r = stBall.getRadius();
        Point l= stBall.getLocation();
        int velx = v.x;
        int vely=10;
        if (l.x+velx<r ||l.x+velx>500-r){
            velx=-velx;
            l.x=l.x+velx;}
        else{
            l.x=l.x+velx;
        }
        if (l.y+vely<r ||l.y+vely>500-r){
            vely=-vely;
            l.y=l.y+vely;}
        else{
            l.y=l.y+vely;}
        da.updateBallWorld();
        assertEquals("location ",l, stBall.getLocation());

    }

    // test the null strategy update
    public void testNullStrategy() throws Exception {
        Point l= nuBall.getLocation();

        da.updateBallWorld();
        assertEquals("location ",l, nuBall.getLocation());

    }
    // test to switch the strategy
    public void testswitcher() throws Exception {


        da.switchStrategy("switcher=1");
        assertEquals("test to switch","StraightStrategy", sw.getStrategyname().getName());

    }

}