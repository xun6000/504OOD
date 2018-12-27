package edu.rice.comp504.model.res;

/**
 * The CommandResponse extends AResponse. This class formats the command string achieved from the websocket
 */
public class CommandResponse extends AResponse {
    /**
     * The command string type: signup, delete, join, ack, send
     */
    public String commandType;

    /**
     * The message receiver: userX, -1 means to all
     */
    public int name;

    /**
     * The message id
     */
    public int msgId;

    /**
     * The message content
     */
    public String content;

    /**
     * The chatroom: chatroomX, -1 means to all
     */
    public int chatroom;

    /**
     * The profile age
     */
    public int age;

    /**
     * The profile location
     */
    public String location;

    /**
     * The profile school
     */
    public String school;

    /**
     * The chatroom restriction: age upperbound
     */
    public int ageResU;

    /**
     * The chatroom restriction: age lowerbound
     */
    public int ageResL;

    /**
     * The chatroom restriction: location
     */
    public String[] locRes;

    /**
     * The chatroom restriction: school
     */
    public String[] schoolRes;
}
