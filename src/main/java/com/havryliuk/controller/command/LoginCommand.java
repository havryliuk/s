package com.havryliuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.UserType;
import com.havryliuk.service.SecurityService;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (null != username && null != password) {
            UserType type = new DaoFactory().getUserDao().getType(username, password);
            if (type != null) {
                request.setAttribute("usertype", type);
                request.getSession().setAttribute("userType", type.toString());
                request.getSession().setAttribute("userId", new SecurityService().getUserIdByName(username));
                if (type.equals(UserType.ADMIN)) {
                    return "adminMain.jsp";
                } else if (type.equals(UserType.CUSTOMER)) {
                    return "customerMain.jsp";
                }
            }
        }
        return "loginError.jsp";
    }
}
