package com.havryliuk.controller.command.customer;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.service.CustomerService;

import lombok.Setter;

@Setter
public class CustomerCommand implements Command {
    private CustomerService customerService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            request.setAttribute("customer", customer.get());
            return "customer.jsp";
        } else {
            return "error.jsp";
        }
    }
}
