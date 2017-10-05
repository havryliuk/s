package com.havryliuk.controller.command.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.service.CustomerService;

import lombok.Setter;

@Setter
public class ListCustomersCommand implements Command {
    private CustomerService customerService;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customers = customerService.getAllCustomers();
        request.setAttribute("customers", customers);

        return "customerList.jsp";
    }
}
