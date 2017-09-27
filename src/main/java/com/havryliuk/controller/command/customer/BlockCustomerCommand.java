package com.havryliuk.controller.command.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.service.CustomerService;

public class BlockCustomerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean result = CustomerService.blockCustomer(id);

        if (result) {
            return "customerBlocked.jsp";
        } else {
            return "customerNotBlocked.jsp";
        }
    }
}
