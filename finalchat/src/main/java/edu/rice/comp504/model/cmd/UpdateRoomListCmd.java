package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.UserRoomResponse;

/**
 * UpdateRoomListCmd implements IUserCmd. This class updates the user list.
 */
public class UpdateRoomListCmd implements IUserCmd{
    /**
     * bannedRoom
     */
    private ChatRoom bannedRoom;

    /**
     * Constructor
     * @param bannedRoom
     */
    public UpdateRoomListCmd(ChatRoom bannedRoom){
        this.bannedRoom = bannedRoom;
    }

    /**
     * Execute the command
     * @param context
     */
    @Override
    public void execute(User context) {
        context.removeRoom(bannedRoom);
        ChatAppController.notify(context.getSession(), new UserRoomResponse(context.getId(),context.getJoinedRoomIds(), context.getAvailableRoomIds()));
    }
}
