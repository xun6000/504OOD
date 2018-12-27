package edu.rice.comp504.model.res;

import edu.rice.comp504.model.obj.Message;

/**
 * The NewMessageResponse extends AResponse. This class formats the new message
 */
public class NewMessageResponse extends AResponse {
    /**
     * new message
     */
    private Message message;

    /**
     * Constructor
     * @param message
     */
    public NewMessageResponse(Message message){
        super("NewMessageResponse");
        this.message = message;
    }
}
