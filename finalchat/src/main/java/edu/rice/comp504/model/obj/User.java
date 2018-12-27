package edu.rice.comp504.model.obj;

import edu.rice.comp504.model.cmd.IUserCmd;
import org.eclipse.jetty.websocket.api.Session;

import java.util.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The User class defines a user object and private fields of a user
 */
public class User implements Observer {
    /**
     * userid
     */
    private int id;

    /**
     * the user session
     */
    private transient Session session;

    /**
     * user name
     */
    private String name;

    /**
     * the age of the user
     */
    private int age;

    /**
     * the location of the user
     */
    private String location;

    /**
     * the name of the school
     */
    private String school;

    /**
     * the list of the room that the user had joined
     */
    private List<Integer> joinedRoomIds;

    /**
     * the available roomid
     */
    private List<Integer> availableRoomIds;

    /**
     * Constructor.
     * @param id the user id
     * @param session the user session
     * @param name the user name when register
     * @param age the user age when register
     * @param location the user location when register
     * @param school the user school when register
     * @param rooms the ChatRoom array contains all rooms
     */
    public User(int id, Session session, String name, int age,
                String location, String school, ChatRoom[] rooms) {
        this.id = id;
        this.session = session;
        this.name = name;
        this.age = age;
        this.location = location;
        this.school = school;
        this.joinedRoomIds = new LinkedList<>();
        this.availableRoomIds = new LinkedList<>();
        for (ChatRoom room : rooms) {
            this.availableRoomIds.add(room.getId());
        }
    }

    /**
     * Get current user id
     * @return the user id
     * */
    public int getId() {
        return this.id;
    }

    /**
     * Get current user session
     * @return the user session
     * */
    public Session getSession() {
        return this.session;
    }

    /**
     * Get the user name
     * @return the user name
     * */
    public String getName() {
        return this.name;
    }

    /**
     * Get the user age
     * @return the user age
     * */
    public int getAge() {
        return this.age;
    }

    /**
     * Get the user location
     * @return the user register location in String
     * */
    public String getLocation() {
        return this.location;
    }

    /**
     * Get the user school
     * @return the user register school in String
     * */
    public String getSchool() {
        return this.school;
    }

    /**
     * Get a list of user joined chat rooms
     * @return joined rooms ids
     * */
    public List<Integer> getJoinedRoomIds() {
        return this.joinedRoomIds;
    }

    /**
     * Get a list of user available chat rooms
     * @return available chat rooms ids
     * */
    public List<Integer> getAvailableRoomIds() {
        return this.availableRoomIds;
    }

    /**
     * Get a chat room id then store into available chat room list
     * @param room the chat room object
     * */
    public void addRoom(ChatRoom room) {
        Integer roomid = room.getId();
        availableRoomIds.add(roomid);
    }

    /**
     * Get a chat room id then remove it from both user joined rooms list and available rooms list
     * @param room the chat room object
     * */
    public void removeRoom(ChatRoom room) {
        Integer roomid = room.getId();
        joinedRoomIds.remove(roomid);
        availableRoomIds.remove(roomid);
    }

    /**
     * Move a chat room from available room list to joined room list
     * @param room the chat room object
     * */
    public void moveToJoined(ChatRoom room) {
        Integer roomid = room.getId();
        availableRoomIds.remove(roomid);
        joinedRoomIds.add(roomid);
    }

    /**
     * Move a chat room from joined room list to available room list
     * @param room the chat room object
     * */
    public void moveToAvailable(ChatRoom room) {
        Integer roomid = room.getId();
        availableRoomIds.add(roomid);
        joinedRoomIds.remove(roomid);
    }

    /**
     * User update when observable has changed
     * */
    @Override
    public void update(Observable o, Object arg) {
        ((IUserCmd)arg).execute(this);
    }

}
