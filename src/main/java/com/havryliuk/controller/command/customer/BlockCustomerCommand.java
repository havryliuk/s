package com.havryliuk.controller.command.customer;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.service.CustomerService;

public class BlockCustomerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean result;

        int id = Integer.parseInt(request.getParameter("id"));
        CustomerService service = new CustomerService();
        Optional<Customer> customer = service.getCustomerById(id);
        boolean blocked;
        if (customer.isPresent()) {
            blocked = customer.get().isBlocked();
        } else {
            return "error.jsp";
        }

        if (!blocked) {
            result = service.blockCustomer(id);
        } else {
            result = service.unblockCustomer(id);
        }
        request.setAttribute("customer", service.getCustomerById(id));
        if (result) {
            return "customerBlocked.jsp";
        } else {
            return "customerNotBlocked.jsp";
        }
    }
}
