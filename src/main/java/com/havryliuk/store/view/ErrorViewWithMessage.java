package com.havryliuk.store.view;

import org.springframework.web.servlet.ModelAndView;

public class ErrorViewWithMessage extends ModelAndView {
    public ErrorViewWithMessage(String message) {
        super();
        setViewName("error");
        getModelMap().addAttribute("message", message);
    }
}