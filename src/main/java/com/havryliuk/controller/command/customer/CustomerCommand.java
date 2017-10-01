package com.havryliuk.controller.command.customer;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.service.CustomerService;

public class CustomerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        Optional<Customer> customer = new CustomerService().getCustomerById(id);
        if (customer.isPresent()) {
            request.setAttribute("customer", customer);
            return "customer.jsp";
        } else {
            return "error.jsp";
        }
    }
}
