package com.havryliuk.store.view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorViewWithMessageTest {
    @Test
    public void testErrorViewWithMessage() {
        ErrorViewWithMessage errorViewWithMessage = new ErrorViewWithMessage("testMessage");
        assertEquals("testMessage", errorViewWithMessage.getModelMap().get("message"));
        assertEquals("error", errorViewWithMessage.getViewName());
    }
}