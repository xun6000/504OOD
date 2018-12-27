package edu.rice.comp504.model.res;

import java.util.List;

/**
 * UserRoomResponse extends AResponse. Message covers the information of all chat rooms of one user.
 */
public class UserRoomResponse extends AResponse {
    /**
     * The id of the user.
     */
    private int userId;

    /**
     * The ids of joined chat rooms of the user.
     */
    private List<Integer> joinedRoomIds;

    /**
     * the ids of available chat rooms of the user.
     */
    private List<Integer> availableRoomIds;

    /**
     * Constructor
     * @param userId
     * @param jRoomIds
     * @param aRoomIDs
     */
    public UserRoomResponse(int userId, List<Integer> jRoomIds, List<Integer> aRoomIDs){
        super("UserRoomResponse");
        this.userId = userId;
        joinedRoomIds = jRoomIds;
        availableRoomIds = aRoomIDs;
    }
}
