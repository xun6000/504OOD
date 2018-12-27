package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.RoomNotificationResponse;
import edu.rice.comp504.model.res.RoomUsersResponse;

import java.util.Map;
/**
 * Command used join the room
 */
public class JoinRoomCmd implements IUserCmd {
    /**
     * target room
     */
    private ChatRoom joinedRoom;

    /**
     * user
     */
    private User joinedUser;

    /**
     * joinNotification
     */
    private String joinNotification;

    /**
     * usermap for notification
     */
    private Map<Integer,String> usersMap;

    /**
     * constructor
     * @param room
     * @param user
     * @param usersMap
     */
    public JoinRoomCmd(ChatRoom room, User user, Map<Integer,String> usersMap){
        joinedUser = user;
        joinedRoom = room;
        joinNotification = user.getName()+" joins this room";
        this.usersMap = usersMap;
    }

    /**
     * generate the notication
     * @param context
     */
    @Override
    public void execute(User context) {
        RoomNotificationResponse response = new RoomNotificationResponse(joinedRoom.getId());
        response.addNotification(joinNotification);
        ChatAppController.notify(context.getSession(),response);
        ChatAppController.notify(context.getSession(),new RoomUsersResponse(joinedRoom.getId(),usersMap.keySet()));
    }
}
