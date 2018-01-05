package com.havryliuk.store.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PagesControllerTest {
    @Test
    public void testCustomerMain() {
        PagesController controller = new PagesController();
        String page = controller.getCustomerMainPage();
        assertEquals("customer/main", page);
    }
}
