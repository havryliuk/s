package com.havryliuk.controller.command.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.service.OrderService;

import lombok.Setter;

@Setter
public class PayOrderCommand extends AbstractOrderCommand implements Command {
    private OrderService orderService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        boolean result = orderService.payOrder(id);
        if (result) {
            request.setAttribute("orderId", id);
            return "orderPaid.jsp";
        } else {
            return errorPageWithMessage(request, "Payment failed!");
        }
    }
}
