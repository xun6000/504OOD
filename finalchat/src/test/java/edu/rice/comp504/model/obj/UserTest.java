package edu.rice.comp504.model.obj;

import edu.rice.comp504.model.DispatcherAdapter;
import junit.framework.TestCase;

public class UserTest extends TestCase {
    public User createUser(){
        ChatRoom[] a = {};
        return new User(0, null, "name", 0, "location", "school", a);
    }

    public void testGetId() {
        User u = createUser();
        assertEquals(0, u.getId());
    }

    public void testGetSession() {
        User u = createUser();
        assertEquals(null, u.getSession());
    }

    public void testGetName() {
        User u = createUser();
        assertEquals("name", u.getName());
    }

    public void testGetAge() {
        User u = createUser();
        assertEquals(0, u.getAge());
    }

    public void testGetLocation() {
        User u = createUser();
        assertEquals("location", u.getLocation());
    }

    public void testGetSchool() {
        User u = createUser();
        assertEquals("school", u.getSchool());
    }

    public void testGetJoinedRoomIds() {
        User u = createUser();
        assertEquals(0, u.getJoinedRoomIds().size());
    }

    public void testGetAvailableRoomIds() {
        User u = createUser();
        assertEquals(0, u.getAvailableRoomIds().size());
    }

    public void testAddRoom() {
        String[] school = {};
        String[] location = {};
        ChatRoom c =  new ChatRoom(0, "name", null, 0, 18, location, school, new DispatcherAdapter());
        User u = createUser();
        u.addRoom(c);
        assertEquals(0, u.getJoinedRoomIds().size());
    }

    public void testRemoveRoom() {
        String[] school = {};
        String[] location = {};
        ChatRoom c =  new ChatRoom(0, "name", null, 0, 18, location, school, new DispatcherAdapter());
        User u = createUser();
        u.removeRoom(c);
    }
}