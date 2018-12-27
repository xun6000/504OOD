package edu.rice.comp504.model.cmd;
import  edu.rice.comp504.model.paintobj.APaintObject;
import  edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositePaintObj;
import edu.rice.comp504.model.strategy.CompositeStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
//import edu.rice.comp504.model.strategy.HorizontalStrategy;
//import edu.rice.comp504.model.strategy.VerticalStrategy;
import edu.rice.comp504.model.strategy.SwitcherStrategy;

import java.util.LinkedList;

/**
 * Interface used to pass commands to lines
 */
public class changestragegy implements IPaintObjCmd{

    private String target="";
    private IUpdateStrategy strategy;

    // check what kind of object should be updated
    public changestragegy(String type, IUpdateStrategy strategy){

        String[] output = type.split("=");
        System.out.println("what we get");
        System.out.println(type);

        for (int i=1;i<10;i++){
            if(output[1].contains(String.valueOf(i))){

                switch(String.valueOf(i)) {
                    case ("1"):
                        target = target+"p1";
                        break;
                    case ("2"):
                        target = target+"p2";
                        break;
                    case ("3"):
                        target = target+"p3";
                        break;
                    case ("4"):
                        target = target+"p4";
                        break;
                    case ("5"):
                        target = target+"p5";
                        break;
                    case ("6"):
                        target = target+"p6";
                        break;

                    case ("7"):
                        target = target+"Fish";
                        break;
                    case ("8"):
                        target = target+"Ball";
                        break;
                    default:
                        target = target+"Ball";
                        break;
                }
            }

        }


        this.strategy = strategy;}
    @Override
    // update the objects
    public void execute(APaintObject context){

        System.out.println(this.target);
        System.out.println(context.getType());

        // check whether the composite obj contain the object need to be changed
        if (context.getType().equals("composite") && context.getStrategy() instanceof SwitcherStrategy){

            String[] c = ((CompositePaintObj)context).getSubtype();
            boolean check = false;

            for (String i:c){
                if (this.target.contains(i)){
                    check=true;

                }
            }
            if (check){
                context.setStrategy(new SwitcherStrategy(strategy));
                for (APaintObject i:((CompositePaintObj)context).getchild()){
                    i.setStrategy(new SwitcherStrategy(strategy));
                }
                }

            }



        else{
            if(this.target.contains(context.getType()) && context.getStrategy() instanceof SwitcherStrategy)

            {context.setStrategy(new SwitcherStrategy(strategy));}

        }




    }





}
