package com.havryliuk.controller.command.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Customer;
import com.havryliuk.persistance.dao.DaoFactory;
import com.havryliuk.persistance.dao.CustomerDao;

public class ListCustomersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDao dao = new DaoFactory().getCustomerDao();
        List<Customer> customers = dao.findAll();
        request.setAttribute("customers", customers);

        return "customerList.jsp";
    }
}
