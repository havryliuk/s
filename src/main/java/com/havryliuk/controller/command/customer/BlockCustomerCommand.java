package com.havryliuk.controller.command.customer;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.controller.command.CommonCommand;
import com.havryliuk.entity.Customer;
import com.havryliuk.service.CustomerService;

import lombok.Setter;

@Setter
public class BlockCustomerCommand extends CommonCommand implements Command {
    private CustomerService customerService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean result;

        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Customer> customer = customerService.getCustomerById(id);
        boolean blocked;
        if (customer.isPresent()) {
            blocked = customer.get().isBlocked();
            if (!blocked) {
                result = customerService.blockCustomer(id);
            } else {
                result = customerService.unblockCustomer(id);
            }

            request.setAttribute("customer", customerService.getCustomerById(id));
            if (result) {
                return "customerBlocked.jsp";
            } else {
                return "customerNotBlocked.jsp";
            }
        } else {
            return errorPageWithMessage(request, "Customer to block/unblock not found.");
        }
    }
}
