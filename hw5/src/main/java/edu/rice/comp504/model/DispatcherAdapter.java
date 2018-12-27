package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.cmd.changeposition;
import edu.rice.comp504.model.cmd.changestragegy;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.*;
import edu.rice.comp504.model.strategy.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * The dispatch adapter is periodically called upon to update the ShapeWorld
 */
public class DispatcherAdapter extends Observable {
    private Point dims;

    /**
     * Constructor
     */

    public DispatcherAdapter() {

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
        return dims;
    }


    /**
     * Call the update method on all the paintobj observers to update their position in the paintobj world
     */
    public void updateBallWorld() {
        IPaintObjCmd cmad= new changeposition();
        setChanged();
        notifyObservers(cmad);
    }


    /**
     * Load a paintobj into the shape world
     * @param body  The REST request body has the strategy names.
     * @param type  The object type (e.g. ball, star, pentagon)
     * @return A paintobj
     */

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
        //System.out.println("the output is "+output[0]+output[1]);
        if (output[1].length()>1) {
            ArrayList<IUpdateStrategy> list = new ArrayList<>();

            for (int i=0 ;i<output[1].length();i++) {
                IUpdateStrategy returned = findSingleStrategy(output[1].substring(i,i+1));
                //System.out.println(returned.getName());
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
    private APaintObject[] generator(String d,IUpdateStrategy strategy){
        ArrayList<APaintObject> children = new ArrayList<>();

        if (d.equals("0")){
            Point location = new Point((int)((Math.random()*(400))),(int)((Math.random()*(300))));
            Point vel=new Point((int)((Math.random()*4)),(int)((Math.random()*4)));
            APaintObject c1 = new Ball(location,vel,"#000000", "Ball",new NullStrategy());
            children.add(c1);
            for (int i =0;i<6;i++){
                Point location2 = new Point((int)((Math.random()*(400))),(int)((Math.random()*(300))));
                Point vel2=new Point((int)((Math.random()*4)),(int)((Math.random()*4)));

                APaintObject c = new p(location,vel,"#000000", "p"+String.valueOf(i+1),new NullStrategy());
                children.add(c);
            }
            Point location3 = new Point((int)((Math.random()*(400))),(int)((Math.random()*(300))));
            Point vel3=new Point((int)((Math.random()*4)),(int)((Math.random()*4)));

            APaintObject c4 = new Fish(location,vel,"#000000", "Fish",new NullStrategy());
            children.add(c4);
        }
        else{
                String col = APaintObject.getRandomColor();
                for (int i =0;i<d.length();i++){

                //IUpdateStrategy returned = findSingleStrategy();
                Point location4 = new Point((int) ((Math.random() * (400))), (int) ((Math.random() * (300))));
                Point vel4 = new Point((int) ((Math.random() * 4)), (int) ((Math.random() * 4)));
                APaintObject c ;

                switch(d.substring(i,i+1)) {
                    case ("1"):
                        c= new p(location4, vel4, col, "p1", strategy);

                        break;
                    case ("2"):

                        c= new p(location4, vel4, col, "p2", strategy);

                        break;
                    case ("3"):
                        c= new p(location4, vel4, col, "p3", strategy);
                        break;
                    case ("5"):

                        c= new p(location4, vel4, col, "p5", strategy);
                        break;
                    case ("6"):

                        c= new p(location4, vel4, col, "p6", strategy);
                        break;
                    case ("4"):

                        c= new p(location4, vel4, col, "p4", strategy);
                        break;
                    case ("7"):

                        c= new Fish(location4, vel4, col, "Fish", strategy);
                        break;
                    case ("8"):

                        c= new Ball(location4, vel4, col, "Ball", strategy);
                        break;
                    default:

                        c= new Ball(location4, vel4, col, "Ball", strategy);
                        break;
                }
                //System.out.println("fished it ");
                children.add(c);

            }

    }
    APaintObject[] ji = children.toArray(new APaintObject[children.size()]);
    return ji;
    }



    public APaintObject loadPaintObj(String body) {
        String[] output = body.split("&");
        //type=0&strategy=0&kind=normal
         IUpdateStrategy strategy = findStrategy(output[1].split("="));

         Point location = new Point((int)((Math.random()*(400))),(int)((Math.random()*(300))));
        Point vel=new Point((int)((Math.random()*4)),(int)((Math.random()*4)));

        APaintObject obj;
        String col = APaintObject.getRandomColor();

        if (output[0].split("=")[1].equals("0") ||output[1].split("=")[1].equals("0")||strategy instanceof NullStrategy){
            // type is zero
            //nothing is selected or nostrategy is selected
            APaintObject[] children =generator("0",new NullStrategy());
            ArrayList<String> list = new ArrayList<>();
            for(int i=0; i<children.length;i++){
                list.add(children[i].getType());
            }
            String[] childlist = list.toArray(new String[list.size()]);
            obj= new CompositePaintObj(children,new NullStrategy(), childlist);
            addObserver(obj);
            return obj;

        }
        else{


        if (output[2].split("=")[1].equals("switcher")) {//switcher
            System.out.println("switcher tye");
            System.out.println(output[0].split("=")[1]);
            if (output[0].split("=")[1].length() == 1) {
                // if only one kind
                if ("123456".contains(output[0].split("=")[1]))
                    obj = new p(location, vel, col, "p" + output[0].split("=")[1], new SwitcherStrategy(strategy));
                else if (output[0].split("=")[1].equals("7")) {
                    obj = new Fish(location, vel, col, "Fish", new SwitcherStrategy(strategy));
                } else if (output[0].split("=")[1].equals("8"))
                    {
                    obj = new Ball(location, vel, col, "Ball", new SwitcherStrategy(strategy));
                }
                else{
                    // it is an unknown obj
                    APaintObject[] children =generator("0",new NullStrategy());
                    ArrayList<String> list = new ArrayList<>();
                    for(int i=0; i<children.length;i++){
                        list.add(children[i].getType());
                    }
                    String[] childlist = list.toArray(new String[list.size()]);
                    obj= new CompositePaintObj(children,new NullStrategy(), childlist);
                }


            } else {
                // length is larger than 1
                APaintObject[] children =generator(output[0].split("=")[1],new SwitcherStrategy(strategy));
                ArrayList<String> list = new ArrayList<>();
                for(int i=0; i<children.length;i++){
                    list.add(children[i].getType());
                }
                String[] childlist = list.toArray(new String[list.size()]);
                obj= new CompositePaintObj(children,new SwitcherStrategy(strategy), childlist);


            }

        }
        else {//normal
            System.out.println("normal tye");
            System.out.println(output[0].split("=")[1]);

            if (output[0].split("=")[1].length() == 1) {

                if ("123456".contains(output[0].split("=")[1]))
                    obj = new p(location, vel, col, "p" + output[0].split("=")[1], strategy);
                else if (output[0].split("=")[1].equals("7")) {
                    obj = new Fish(location, vel, col, "Fish", strategy);
                } else if (output[0].split("=")[1].equals("8")){
                    obj = new Ball(location, vel, col, "Ball", strategy);
                }else{
                    APaintObject[] children =generator("0",strategy);
                    ArrayList<String> list = new ArrayList<>();
                    for(int i=0; i<children.length;i++){
                        list.add(children[i].getType());
                    }
                    String[] childlist = list.toArray(new String[list.size()]);
                    obj= new CompositePaintObj(children, strategy, childlist);
                }

            } else {//the length is larger than 1
                APaintObject[] children =generator(output[0].split("=")[1],strategy);
                ArrayList<String> list = new ArrayList<>();
                for(int i=0; i<children.length;i++){
                    list.add(children[i].getType());
                }
                String[] childlist = list.toArray(new String[list.size()]);
                obj= new CompositePaintObj(children,strategy, childlist);

            }

        }
        addObserver(obj);

        return obj;}




    }


//    public APaintObject findobject(String[] output){
//        APaintObject object;
//        //System.out.println("the output is "+output[0]+output[1]);
//        if (output[1].length()>1) {
//            ArrayList<APaintObject> list = new ArrayList<>();
//
////            for (int i=0 ;i<output[1].length();i++) {
////                APaintObject returned = findsingleobject(output[1].substring(i,i+1));
////                list.add(returned);
////            }
////            APaintObject[] children = list.toArray(new APaintObject[list.size()]);
////            //tobe done
////            object=new CompositePaintObj(children);
//      }
//        else {
//
//            //object = findsingleobject(output[1]);
//
//        }
//        return object;
//    }
//
//
//    };

    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body) {
        String[] output = body.split("&");//type=12,1,2,0&strategy=0&kind=normal

        IUpdateStrategy strategy = findStrategy(output[1].split("="));

        IPaintObjCmd cmad= new changestragegy(output[0],strategy);
        setChanged();

        notifyObservers(cmad);


    }


}
