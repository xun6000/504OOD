package edu.rice.comp504.model;

//import static org.junit.Assert.*;
import edu.rice.comp504.model.ball.NullBall;
import junit.framework.TestCase;
import  edu.rice.comp504.model.ball.ABall;

import java.awt.*;

/**
 * Created by gy12 on 9/11/18.
 */
public class DispatchAdapterTest extends TestCase{
    DispatchAdapter da = new DispatchAdapter();
    ABall stBall=da.loadBall("straight");
    ABall rotationBall=da.loadBall("rotation");
    ABall changesizeBall=da.loadBall("changing_size");
    ABall changecolorBall=da.loadBall("changing_color");
    ABall randomBall=da.loadBall("random");
    ABall speedBall=da.loadBall("speed");
    ABall lowBall=da.loadBall("low");
    ABall nuBall=da.loadBall("unknown");
    //test to creat the ball
    public void testLoadBall()  {


        // fisrt test
        assertEquals("load straight ball test","straight",stBall.getName());
        assertEquals("load rotation ball test","rotation", rotationBall.getName());
        assertEquals("load changing_size test","changing_size", changesizeBall.getName());
        assertEquals("load changing_color ball test","changing_color", changecolorBall.getName());
        assertEquals("load random ball test","random", randomBall.getName());
        assertEquals("load speed ball test","speed", speedBall.getName());
        assertEquals("load low ball test","low", lowBall.getName());


    }
    // test to creat Nullball
    public void testNullBall(){
        assertEquals("load unknown ball test","null", nuBall.getName());


    }
    // test the update of straightball, colorball and speedball. Similar to test others.
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


    //test the update of nullball
    public void testNullBallupdate(){
        //test straight ball

        Point l= nuBall.getLocation();

        da.updateBallWorld();
        assertEquals("location ",l, nuBall.getLocation());













    }
    //test number of objects in obeserver
    public void testAddingBall(){
        assertEquals("total number ",8, da.countObservers());
        ABall lowBall2=da.loadBall("low");
        assertEquals("total number ",9, da.countObservers());






    }
    // test to delete the obeserver
    public void testClearing(){
        da.deleteObservers();
        assertEquals("total number after clearing ",0, da.countObservers());

    }













}