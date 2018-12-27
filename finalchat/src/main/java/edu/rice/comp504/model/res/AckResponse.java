package edu.rice.comp504.model.res;

import edu.rice.comp504.model.obj.Message;

/**
 * AckRespnse extends AResponse. Confirmation of get the information
 */
public class AckResponse extends AResponse {
    /**
     * confirmation message
     */
    private Message message;

    /**
     * Constructor
     * @param msg
     */
    public AckResponse(Message msg){
        super("AckResponse");
        message = msg;
    }
}
