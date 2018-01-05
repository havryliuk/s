package com.havryliuk.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/main")
    public String returnMainAdminPage() {
        return "admin/main";
    }
}
