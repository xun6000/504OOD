package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.UserRoomResponse;

import edu.rice.comp504.model.res.RoomUsersResponse;




import java.util.HashSet;
import java.util.Set;

/**
 * room owner leave its room, notify all the users in the room
 */
public class RemoveRoomCmd implements IUserCmd{
    /**
     * the room that its owner leave
     */
    private ChatRoom deletedRoom;

    /**
     * owner (all other users will be forced to leave)
     */
    private Set<Integer> remainedUsers;

    /**
     * the season of leaving, will be send to the user
     */
    private String reason;

    /**
     * Constructor: command to delete the room, add the reason the the ramianed users
     */
    public RemoveRoomCmd(ChatRoom deletedRoom, String reason){
        this.deletedRoom = deletedRoom;
        remainedUsers = new HashSet<>();
        if (!LeaveRoomCmd.REASON_DISCONNECT.equals(reason)){
            remainedUsers.add(deletedRoom.getOwner().getId());
        }
        this.reason = reason;
    }

    /**
     * Defines the command when a chat room is deleted by its owner. Its parameter should be all users.
     * The context user will delete the chat room from his/her joined and available chat room lists.
     * @param context
     */
    @Override
    public void execute(User context) {
        if (!deletedRoom.getOwner().equals(context)) {
            if (LeaveRoomCmd.REASON_DISCONNECT.equals(reason)){
                context.removeRoom(deletedRoom);
            }else {
                context.moveToAvailable(deletedRoom);
            }
            deletedRoom.deleteObserver(context);
            ChatAppController.notify(context.getSession(),new RoomUsersResponse(deletedRoom.getId(),remainedUsers));
        }
    }
}
