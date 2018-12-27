package edu.rice.comp504.model.res;

import java.util.ArrayList;
import java.util.List;

/**
 * RoomNotificationResponse extends AResponse. This class generates the list of notifications.
 */
public class RoomNotificationResponse extends AResponse {
    /**
     * the room id
     */
    private int roomId;

    /**
     * notifications within the room
     */
    private List<String> notifications;

    /**
     * constructor
     * @param roomId new room's roomId
     */
    public RoomNotificationResponse(int roomId){
        super("RoomNotificationResponse");
        this.roomId = roomId;
        notifications = new ArrayList<>();
    }

    /**
     * assign the notification
     * @param notification
     */
    public void addNotification(String notification){
        notifications.add(notification);
    }

}
