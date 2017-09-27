package com.havryliuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.persistance.dao.DaoFactory;
import com.havryliuk.persistance.dao.UserType;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (null != username && null != password) {
            UserType type = new DaoFactory().getUserDao().getType(username, password);
            if (type != null) {
                request.setAttribute("usertype", type);
                if (type.equals(UserType.ADMIN)) {
                    return "adminMain.jsp";
                } else if (type.equals(UserType.CUSTOMER)) {
                    return "userMain.jsp";
                }
            }
        }
        return "loginError.jsp";
    }
}
