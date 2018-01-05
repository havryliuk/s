package com.havryliuk.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.havryliuk.store.dao.UserType;
import com.havryliuk.store.service.SecurityService;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String openLoginPage() {
        return "index";
    }

    @PostMapping
    public String authenticate(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (null != username && null != password) {
            UserType type = securityService.getUserType(username, password);
            if (type != null) {
                request.setAttribute("usertype", type);
                request.getSession().setAttribute("userType", type.toString());
                request.getSession().setAttribute("userId", securityService.getUserIdByName(username));
                if (type.equals(UserType.ADMIN)) {
                    return "admin/main";
                } else if (type.equals(UserType.CUSTOMER)) {
                    return "customer/main";
                }
            }
        }
        return "login/error";
    }
}
