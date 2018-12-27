package edu.rice.comp504.model;

import java.util.Observable;

import com.google.gson.Gson;
import edu.rice.comp504.model.ball.*;

/**
 * The dispatch adapter holds all the balls in the BallWorld. It is responsible for communicating with the model
 * and the view.
 */
public class DispatchAdapter extends Observable {

    /**
     * Constructor
     */
    public DispatchAdapter() {

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
     * @param kind  The kind of ball to load into ball world
     * @return A ball
     */
    public ABall loadBall(String kind) {
        System.out.println("kind is"+kind);
        Gson gson = new Gson();
        ABall ball;
        switch(kind){
            case ("straight"):
                 ball = new Straightball();
                break;
            case ("rotation"):
                 ball = new Rotationball();
                break;
            case ("changing_size"):
                 ball = new Changingsizeball();

                break;
            case ("changing_color"):
                ball = new Changingcolorball();
                break;
            case ("random"):
                ball = new Randomball();
                break;
            case ("speed"):
                ball = new Speedingball();
                break;
            case ("low"):
                ball = new Lowball();
                break;
            default:
                ball = new NullBall();
                break;
        }

        addObserver(ball);
        return ball;





    }
}
