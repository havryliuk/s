package com.havryliuk.controller.command.order;

import javax.servlet.http.HttpServletRequest;

import com.havryliuk.controller.command.CommonCommand;

class AbstractOrderCommand extends CommonCommand {
    int getCustomerIdFromSession(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("userId");
    }
}
