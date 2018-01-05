package com.havryliuk.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @GetMapping("/customer/main")
    public String getCustomerMainPage() {
        return "customer/main";
    }
}
