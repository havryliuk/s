package com.havryliuk.controller.command;

import javax.servlet.http.HttpServletRequest;

public abstract class CommonCommand {
    protected String errorPageWithMessage(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        return "error.jsp";
    }
}
