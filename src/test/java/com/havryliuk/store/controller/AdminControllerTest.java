package com.havryliuk.store.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminControllerTest {
    @Test
    public void testAdminController() {
        AdminController controller = new AdminController();
        String page = controller.returnMainAdminPage();
        assertEquals("admin/main", page);
    }
}
