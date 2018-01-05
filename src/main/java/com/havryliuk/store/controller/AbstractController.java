package com.havryliuk.store.controller;

import javax.servlet.http.HttpServletRequest;

public class AbstractController {
    int getCustomerIdFromSession(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("userId");
    }
}
