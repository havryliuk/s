package com.havryliuk.controller.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.service.OrderService;

public class PayOrderCommand extends AbstractOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        boolean result = new OrderService().payOrder(id);
        if (result) {
            request.setAttribute("orderId", id);
            return "orderPaid.jsp";
        } else {
            return errorPageWithMessage(request, "Payment failed!");
        }
    }
}
