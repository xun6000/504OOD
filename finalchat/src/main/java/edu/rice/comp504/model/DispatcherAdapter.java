package edu.rice.comp504.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.cmd.AddRoomCmd;
import edu.rice.comp504.model.cmd.LeaveRoomCmd;
import edu.rice.comp504.model.cmd.UpdateRoomListCmd;
import org.eclipse.jetty.websocket.api.Session;

import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.Message;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.*;

/**
 * Dispatch adapter class. This class helps handle web socket message
 */
public class DispatcherAdapter extends Observable {
    /**
     * Next user id that will be allocated
     */
    private int nextUserId;

    /**
     * Next message id that will be allocated
     */
    private int nextMessageId;

    /**
     * Maps user id to the user
     */
    private Map<Integer, User> users;

    /**
     * Maps room id to the chat room
     */
    private Map<Integer, ChatRoom> rooms;

    /**
     * Maps message id to the message
     */
    private Map<Integer, Message> messages;

    /**
     * Maps session to user id
     */
    private Map<Session, Integer> userIdFromSession;

    /**
     * Gson that helps parse json objects
     */
    private Gson gson = new Gson();

    /**
     * Constructor, initializing all private fields.
     */
    public DispatcherAdapter() {
        this.nextUserId = 0;
        this.nextMessageId = 0;
        this.users = new ConcurrentHashMap<>();
        this.rooms = new ConcurrentHashMap<>();
        this.messages = new ConcurrentHashMap<>();
        this.userIdFromSession = new ConcurrentHashMap<>();
        rooms.put(-1, new ChatRoom(-1, null, null, -1, -1, null, null, null));
    }

    /**
     * Allocate a user id for a new session.
     * @param session the new session
     */
    public void newSession(Session session) {
        userIdFromSession.put(session, nextUserId++);
    }

    /**
     * Get the user if from a session.
     * @param session the session
     * @return the user id binding with session
     */
    public int getUserIdFromSession(Session session) {
        return this.userIdFromSession.get(session);
    }

    /**
     * Helper function that filters all the rooms that the user can join
     * @param usr the user
     */
    private void filterChatRooms(User usr) {
        for (ChatRoom cr : rooms.values()) {
            if (cr.applyFilter(usr)) {
                usr.moveToAvailable(cr);
            }
        }
    }

    /**
     * Load a user into the environment.
     * @param session the session that requests to called the method
     * @param body of format "name age location school"
     * @return the new user that has been loaded
     */
    public User loadUser(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        int userid = getUserIdFromSession(session);
        User usr = new User(userid, session, "User" + userid, cr.age, cr.location, cr.school, new ChatRoom[]{});
        filterChatRooms(usr);
        users.put(userid, usr);
        addObserver(usr);
        return usr;
    }

    /**
     * Load a room into the environment.
     * @param session the session that requests to called the method
     * @param body of format "name ageLower ageUpper {[location],}*{[location]} {[school],}*{[school]}"
     * @return the new room that has been loaded
     */
    public ChatRoom loadRoom(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        int userid = getUserIdFromSession(session);
        User owner = users.get(userid);
        // the front end will check whether owner matches the chat room restrictions
        // owner is added to the room in the constructor
        ChatRoom room = new ChatRoom(userid, "Chatroom" + userid, owner, cr.ageResL, cr.ageResU, cr.locRes, cr.schoolRes, this);
        rooms.put(userid, room);
        room.addObserver(owner);
        owner.addRoom(room);
        setChanged();
        notifyObservers(new AddRoomCmd(room));
        return room;
    }

    /**
     * Remove a user with given userId from the environment.
     * @param userId the id of the user to be removed
     */
    public void unloadUser(int userId) {
        User usr = users.get(userId);
        ChatRoom ownRoom = null;
        // remove user from all chatrooms
        for (int i = usr.getJoinedRoomIds().size()-1; i>=0; i--) {
            Integer id = usr.getJoinedRoomIds().get(i);
            ChatRoom room = rooms.get(id);
            room.removeUser(usr, LeaveRoomCmd.REASON_DISCONNECT);
            if (room.getOwner().equals(usr)) {
                // the room is destroyed
                ownRoom = room;
                rooms.remove(ownRoom.getId());
            }
            usr.removeRoom(room);
        }
        setChanged();
        notifyObservers(new UpdateRoomListCmd(ownRoom));
        deleteObserver(usr);
        users.remove(userId);
    }

    /**
     * Make a user join a chat room.
     * @param session the session that requests to called the method
     * @param body of format "roomId"
     */
    public void joinRoom(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        User usr = users.get(getUserIdFromSession(session));
        // all the rooms that are visible to the user are qualified
        ChatRoom room = rooms.get(cr.chatroom);
        usr.moveToJoined(room);
        room.addUser(usr);
    }

    /**
     * Make a user to leave a chat room.
     * @param session the session that requests to call the method
     * @param body of format "roomId"
     */
    public void leaveRoom(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        User usr = users.get(getUserIdFromSession(session));
        // if chatroom == -1, it means the user wants to leave all the other chat rooms and cleans his own room
        if (cr.chatroom == -1) {
            for (int i = usr.getJoinedRoomIds().size()-1; i>=0; i--) {
                Integer roomid = usr.getJoinedRoomIds().get(i);
                ChatRoom room = rooms.get(roomid);
                if (room.getOwner().equals(usr)) {
                    room.removeUser(usr, LeaveRoomCmd.REASON_CLOSE);
                } else {
                    usr.moveToAvailable(room);
                    room.removeUser(usr, LeaveRoomCmd.REASON_VOLUNTARY);
                }
            }
            ChatAppController.notify(session,new UserRoomResponse(usr.getId(),usr.getJoinedRoomIds(),usr.getAvailableRoomIds()));
        } else {
            ChatRoom room = rooms.get(cr.chatroom);
            if (room.getOwner().equals(usr)) {
                room.removeUser(usr, LeaveRoomCmd.REASON_CLOSE);
            } else {
                usr.moveToAvailable(room);
                room.removeUser(usr, LeaveRoomCmd.REASON_VOLUNTARY);
            }
        }

    }

    /**
     * A sender sends a string message to a receiver.
     * @param session the session of the message sender
     * @param body of format "roomId receiverId rawMessage"
     */
    public void sendMessage(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        int sender = getUserIdFromSession(session);
        if(cr.commandType.equals("message")) {
            if (cr.name == -1) {
                for (int receiver : getUserIds(sender)) {
                    if (receiver == sender) {
                        continue;
                    }
                    sendMessageHelper(sender, receiver, cr.content);
                }
                return;
            }
            sendMessageHelper(sender, cr.name, cr.content);
        } else if (cr.commandType.equals("chatHistory")) {
            sendHistoryHelper(sender, cr.name);
        }
    }
    //send the chat history
    private void sendHistoryHelper(int senderId, int receiverId) {
        User sender = users.get(senderId);
        UserChatHistoryResponse chatResp = new UserChatHistoryResponse(getChatHistory(-1, senderId, receiverId));
        notifyClient(sender, chatResp);
    }


    /**
     * Send the message. Messages are stored in chatroom -1, every pair of users is unique
     * @param senderId
     * @param receiverId
     * @param content
     */

    private void sendMessageHelper(int senderId, int receiverId, String content) {
        Message msg = new Message(nextMessageId, -1, senderId, receiverId, content);
        messages.put(nextMessageId++,msg);
        // messages are stored in chatroom -1, every pair of users is unique
        User sender = users.get(senderId);
        User receiver = users.get(receiverId);
        rooms.get(-1).storeMessage(sender, receiver, msg);
        NewMessageResponse msgResp = new NewMessageResponse(msg);
        notifyClient(receiver, msgResp);
    }

    /**
     * Acknowledge the message from the receiver.
     * @param session the session of the message receiver
     * @param body of format "msgId"
     */
    public void ackMessage(Session session, String body) {
        CommandResponse cr = gson.fromJson(body, CommandResponse.class);
        Message ackMsg = messages.get(cr.msgId);
        int sender  = ackMsg.getSenderId();
        ackMsg.setIsReceived(true);
        notifyClient(users.get(sender), new AckResponse(ackMsg));
    }

    /**
     * Notify the client for refreshing.
     * @param user user expected to receive the notification
     * @param response the information for notifying
     */
    public void notifyClient(User user, AResponse response) {
        ChatAppController.notify(user.getSession(),response);
    }

    /**
     * Get ids of all chat room members.
     * @param roomId the id of the chat room
     * @return all ids of chat room members
     */
    private List<Integer> getUserIds(int roomId) {
        List<Integer> userlist = new ArrayList<>();
        for (int id : users.keySet()) {
            if (users.get(id).getJoinedRoomIds().contains(roomId)) {
                userlist.add(id);
            }
        }
        return userlist;
    }

    /**
     * Get chat history between user A and user B (commutative).
     * @param roomId the id of the chat room
     * @param userAId the id of user A
     * @param userBId the id of user B
     * @return chat history between user A and user B at a chat room
     */
    private List<Message> getChatHistory(int roomId, int userAId, int userBId) {
        String key = userAId < userBId ? userAId + "&" + userBId : userBId + "&" + userAId;
        return rooms.get(roomId).getChatHistory().get(key);
    }
}
