package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.*;
import edu.rice.comp504.model.strategy.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

//import static edu.rice.comp504.model.ball.Ball.MAX_VEL;

/**
 * The dispatch adapter is used to communicates with the model to periodically update all the balls
 * in the BallWorld using its notifyObservers method
 */
public class DispatchAdapter extends Observable {
    Point dims;

    /**
     * Constructor
     */
    public DispatchAdapter() {

    }

    /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims=dims;

    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return this.dims;
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world
     */
    public void updateBallWorld() {


        setChanged();
        notifyObservers();
    }


    /**
     * Load a ball into the ball world
     * @param body  The REST request body has the strategy names.
     * @return A ball
     */
    //according to the single letter, find the Strategy
    public IUpdateStrategy findSingleStrategy(String c)
    {
        IUpdateStrategy strategy ;

        switch(c){

            case ("1"):
                strategy = StraightStrategy.Singleton;
                break;
            case ("2"):
                strategy = SpeedStrategy.Singleton;
                break;
            case ("3"):
                strategy = ChangeSizeStrategy.Singleton;
                break;
            case ("4"):
                strategy = RandomStrategy.Singleton;
                break;
            case ("5"):
                strategy = RotateStrategy.Singleton;
                break;
            case ("6"):
                strategy = ChangeColorStrategy.Singleton;
                break;
            case ("7"):
                strategy = LowStrategy.Singleton;
                break;
            default:
                strategy = NullStrategy.Singleton;
                break;
        }


    return strategy;


    }
    //according to the input, find the strategys
    private IUpdateStrategy findStrategy(String[] output) {
        IUpdateStrategy strategy;
        System.out.println("the output is "+output[0]+output[1]);
        if (output[1].length()>1) {
            ArrayList<IUpdateStrategy> list = new ArrayList<>();

            for (int i=0 ;i<output[1].length();i++) {
                IUpdateStrategy returned = findSingleStrategy(output[1].substring(i,i+1));
                System.out.println(returned.getName());
                list.add(returned);
            }
            IUpdateStrategy[] children = list.toArray(new IUpdateStrategy[list.size()]);
            strategy=new CompositeStrategy(children);
        }
        else {

            strategy = findSingleStrategy(output[1]);

        }
        return strategy;
    }
    // creat a ball
    public Ball loadBall(String body) {
        String[] output = body.split("=");
        Ball ball;

        IUpdateStrategy strategy = findStrategy(output);

        if (output[0].equals("switcher")) {

            SwitcherStrategy.Singleton.setStrategy(strategy);
            strategy = SwitcherStrategy.Singleton;
        }
        int radius =(int)((Math.random()*20));
        System.out.println(this.getCanvasDims());
        Point location = new Point((int)((Math.random()*(400))),(int)((Math.random()*(300))));
        Point vel=new Point((int)((Math.random()*4)),(int)((Math.random()*4)));
        ball= new Ball(location,radius,vel,strategy);
        addObserver(ball);
        return ball;





    }

    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body) {

        String[] output = body.split("=");

        IUpdateStrategy strategy = findStrategy(output);


        SwitcherStrategy.Singleton.setStrategy(strategy);

    }
}