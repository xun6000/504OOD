package edu.rice.comp504.model.cmd;
import edu.rice.comp504.model.cmd.IPaintObjCmd;
import  edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositePaintObj;
import edu.rice.comp504.model.strategy.SwitcherStrategy;

/**
 * Interface used to pass commands to lines
 */
public class changeposition implements IPaintObjCmd {

    /**
     * Execute the command on a particular line.
     * @param context The line.
     */
// change the position
    public void execute(APaintObject context){
        if (!context.getType().equals("composite") ) {

            context.getStrategy().updateState(context);


        }
        else{
            for (APaintObject i:((CompositePaintObj)context).getchild()){
                i.getStrategy().updateState(i);
            }


        }
    }}