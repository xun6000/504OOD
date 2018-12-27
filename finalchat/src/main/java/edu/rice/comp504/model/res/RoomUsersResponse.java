package edu.rice.comp504.model.res;

import java.util.Set;

/**
 * RoomUsersResponse extends AResponse. Message covers the information of all users in chat room.
 */
public class RoomUsersResponse extends AResponse {
    /**
     * room id
     */
    private int roomId;

    /**
     * users in this room
     */
    private Set<Integer> users;

    /**
     * constructor
     * @param users
     *   @param roomId
     */
    public RoomUsersResponse(int roomId, Set<Integer> users){
        super("RoomUsersResponse");
        this.users = users;
        this.roomId = roomId;
    }
}
