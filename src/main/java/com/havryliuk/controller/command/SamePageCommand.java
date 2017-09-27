package com.havryliuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SamePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return request.getRequestURI().replace("/store/", "").replaceAll("/\\d+", "") + ".jsp";
    }
}
