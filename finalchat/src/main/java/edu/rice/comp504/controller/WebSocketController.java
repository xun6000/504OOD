package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.res.CommandResponse;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Create a web socket for the server.
 */
@WebSocket
public class WebSocketController {
    /**
     * Gson for json object parsing
     */
    private Gson gson = new Gson();

    /**
     * Open user's session.
     * @param user The user whose session is opened.
     */
    @OnWebSocketConnect
    public void onConnect(Session user) {
        ChatAppController.getDispatcher().newSession(user);
    }

    /**
     * Send a message.
     * @param user  The session user sending the message.
     * @param message The message to be sent.
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        CommandResponse cr = gson.fromJson(message, CommandResponse.class);
        switch(cr.commandType) {
            case "signup":
                ChatAppController.getDispatcher().loadUser(user, message);
                ChatAppController.getDispatcher().loadRoom(user, message);
                break;
            case "joinRoom":
                ChatAppController.getDispatcher().joinRoom(user, message);
                break;
            case "chatHistory":
                ChatAppController.getDispatcher().sendMessage(user, message);
                break;
            case "message":
                StringBuilder sb = new StringBuilder().append(" ").append(cr.content).append(" ");
                if (sb.indexOf(" hate ")>=0){
                    ChatAppController.getDispatcher().unloadUser(ChatAppController.getDispatcher().getUserIdFromSession(user));
                    return;
                }
                ChatAppController.getDispatcher().sendMessage(user, message);
                break;
            case "ack":
                ChatAppController.getDispatcher().ackMessage(user, message);
                break;
            case "leaveRoom":
                ChatAppController.getDispatcher().leaveRoom(user, message);
                break;
            default:
                break;
        }
    }

    /**
     * Close the user's session.
     * @param user The use whose session is closed.
     */
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        // TimeOut
        if (!user.isOpen()){
            user.close();
        }
        int userid = ChatAppController.getDispatcher().getUserIdFromSession(user);
        ChatAppController.getDispatcher().unloadUser(userid);
    }

}
