package edu.rice.comp504.model.res;

import edu.rice.comp504.model.obj.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * UserChatHistoryResponse extends AResponse. Message covers the information of all history messages in the chat room.
 */
public class UserChatHistoryResponse extends AResponse {
    /**
     * The chat history as a message list
     */
    private List<Message> chatHistory;

    /**
     * constructor
     * @param messages
     */
    public UserChatHistoryResponse(List<Message> messages){
        super("UserChatHistoryResponse");
        if (messages == null){
            chatHistory = new ArrayList<>();
        } else {
            chatHistory = new ArrayList<>(messages);
        }
    }
}
