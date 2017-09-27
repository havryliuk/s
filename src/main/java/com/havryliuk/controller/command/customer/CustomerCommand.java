package com.havryliuk.controller.command.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.persistance.dao.CustomerDao;
import com.havryliuk.persistance.dao.DaoFactory;

public class CustomerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        CustomerDao dao = new DaoFactory().getCustomerDao();
        Customer customer = dao.find(id);
        request.setAttribute("customer", customer);
        return "customer.jsp";
    }
}
