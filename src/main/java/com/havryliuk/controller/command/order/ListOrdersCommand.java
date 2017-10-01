package com.havryliuk.controller.command.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Order;
import com.havryliuk.service.OrderService;

public class ListOrdersCommand extends AbstractOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = new ArrayList<>();
        int customerId = getCustomerIdFromSession(request);
        orders.addAll(new OrderService().getOrdersForCustomer(customerId));
        request.setAttribute("orders", orders);
        return "orders.jsp";
    }
}
