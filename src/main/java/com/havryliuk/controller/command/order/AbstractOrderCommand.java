package com.havryliuk.controller.command.order;

import javax.servlet.http.HttpServletRequest;

class AbstractOrderCommand {
    int getCustomerIdFromSession(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("userId");
    }
}
