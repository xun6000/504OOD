package edu.rice.comp504.model.obj;

import edu.rice.comp504.model.DispatcherAdapter;
import junit.framework.TestCase;

import java.util.ArrayList;


public class ChatRoomTest extends TestCase {

    public  ChatRoom createChatRoom(){
        String[] school = {};
        String[] location = {};
        return new ChatRoom(0, "name", null, 0, 18, location, school, new DispatcherAdapter());
    }

    public void testGetId() {
        ChatRoom c = createChatRoom();
        assertEquals(0, c.getId());
    }

    public void testGetName() {
        ChatRoom c = createChatRoom();
        assertEquals("name", c.getName());
    }

    public void testGetOwner() {
        ChatRoom c = createChatRoom();
        assertEquals(null, c.getOwner());
    }

    public void testGetNotifications() {
        ChatRoom c = createChatRoom();
        assertEquals(0, c.getNotifications().size());
    }

    public void testGetChatHistory() {
        ChatRoom c = createChatRoom();
        assertEquals(0, c.getChatHistory().size());
    }

    public void testApplyFilter() {
        ChatRoom c = createChatRoom();
        ChatRoom[] a = {};
        User user = new User(0, null, "name", 18, "location", "school", a);
        assertEquals(true, c.applyFilter(user));

    }

    public void testRemoveUser() {
        ChatRoom c = createChatRoom();
        ChatRoom[] a = {};
        User user = new User(0, null, "name", 18, "location", "school", a);
        assertEquals(true, c.removeUser(user, ""));
    }

}