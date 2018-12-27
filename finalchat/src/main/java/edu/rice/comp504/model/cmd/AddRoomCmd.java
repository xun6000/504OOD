package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.UserRoomResponse;

/**
 * Command used to inform all users that a new ChatRoom is created
 */
public class AddRoomCmd implements IUserCmd {

    /**
     * newest created chat room
     */
    private ChatRoom newRoom;

    /**
     * construe method
     * @param chatRoom newest created chat room
     */
    public AddRoomCmd(ChatRoom chatRoom){
        newRoom = chatRoom;
    }

    /**
     * construe execute method on a context
     * @param  context
     */
    @Override
    public void execute(User context) {
        if (context.equals(newRoom.getOwner())){
            context.moveToJoined(newRoom);
            ChatAppController.notify(context.getSession(), new UserRoomResponse(context.getId(), context.getJoinedRoomIds(), context.getAvailableRoomIds()));
        }else if (newRoom.applyFilter(context)){
            context.addRoom(newRoom);
            ChatAppController.notify(context.getSession(), new UserRoomResponse(context.getId(), context.getJoinedRoomIds(), context.getAvailableRoomIds()));
        }
    }
}
