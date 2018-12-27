package edu.rice.comp504.model.obj;

import junit.framework.TestCase;

public class MessageTest extends TestCase {

    public Message createMsg() {
        return new Message(0, 0, 0, 0, "");
    }

    public void testGetId() {
        Message m = createMsg();
        assertEquals(0, m.getId());
    }

    public void testGetRoomId() {
        Message m = createMsg();
        assertEquals(0, m.getRoomId());
    }

    public void testGetSenderId() {
        Message m = createMsg();
        assertEquals(0, m.getSenderId());
    }

    public void testGetReceiverId() {
        Message m = createMsg();
        assertEquals(0, m.getReceiverId());
    }

    public void testGetMessage() {
        Message m = createMsg();
        assertEquals("", m.getMessage());
    }

    public void testGetIsReceived() {
        Message m = createMsg();
        assertEquals(false, m.getIsReceived());
    }

    public void testSetIsReceived() {
        Message m = createMsg();
        m.setIsReceived(true);
        assertEquals(true, m.getIsReceived());
    }
}