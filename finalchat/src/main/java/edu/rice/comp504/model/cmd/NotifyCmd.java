package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.AResponse;


/**
 * Command containing response that should be delivered to the client
 */
public class NotifyCmd implements IUserCmd {
    /**
     * response
     */
    private AResponse response;

    /**
     * Constructor
     * @param resp
     */
    public NotifyCmd(AResponse resp){
        response = resp;
    }

    /**
     * notify the user with the response
     * @param context
     */
    @Override
    public void execute(User context) {
        ChatAppController.notify(context.getSession(),response);
    }
}
