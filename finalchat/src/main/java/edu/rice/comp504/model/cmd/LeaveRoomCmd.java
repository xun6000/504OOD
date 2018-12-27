package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.obj.ChatRoom;
import edu.rice.comp504.model.obj.User;
import edu.rice.comp504.model.res.RoomNotificationResponse;
import edu.rice.comp504.model.res.RoomUsersResponse;

import java.util.Map;
/**
 * Command to leave the room
 */
public class LeaveRoomCmd implements IUserCmd {
    /**
     * reason of leave: owner closes the room
     */
    public final static String REASON_CLOSE = "close";

    /**
     * reason of leave: forced
     */
    public final static String REASON_FORCE = "force";

    /**
     * reason of leave: voluntary
     */
    public final static String REASON_VOLUNTARY = "voluntary";

    /**
     * reason of leave: disconnected session
     */
    public final static String REASON_DISCONNECT = "disconnect";

    /**
     * room
     */
    private ChatRoom room;

    /**
     * leaved user
     */
    private User leavedUser;

    /**
     * notification to be send to users
     */
    private String notification;

    /**
     * reason of leave
     */
    private String reason ;

    /**
     * user map
     */
    private Map<Integer,String> usersMap;

    /**
     * Constructor: command to leave the room
     * @param leavedRoom
     * @param leavedUser
     * @param reason
     * @param usersMap
     */
    public LeaveRoomCmd(ChatRoom leavedRoom, User leavedUser, String reason, Map<Integer, String> usersMap){

        room = leavedRoom;
        this.leavedUser = leavedUser;
        this.reason = reason;
        this.usersMap = usersMap;
    }

    /**
     * generate the notification according to the leave reason
     * @param context
     */
    @Override
    public void execute(User context) {
        switch (reason){
            case REASON_DISCONNECT:
                notification = leavedUser.getName() + " leaves because of closed session";
                break;
            case REASON_FORCE:
                notification = leavedUser.getName() + " leaves because of word HATE";
                break;
            case REASON_VOLUNTARY:
                notification = leavedUser.getName() + " leaves voluntarily";
                break;
            case REASON_CLOSE:
                notification = leavedUser.getName() + " leaves because owner closed the room";
                break;
        }

        RoomNotificationResponse response = new RoomNotificationResponse(room.getId());
        response.addNotification(notification);
        ChatAppController.notify(context.getSession(), response);
        ChatAppController.notify(context.getSession(), new RoomUsersResponse(room.getId(), usersMap.keySet()));
    }
}
