package com.havryliuk.controller.command.order;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.controller.command.CommonCommand;
import com.havryliuk.entity.Order;
import com.havryliuk.service.OrderService;

import lombok.Setter;

@Setter
public class OrderCommand extends CommonCommand implements Command {
    private OrderService orderService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            request.setAttribute("order", order.get());
            return "order.jsp";
        } else {
            return errorPageWithMessage(request, "Order not found!");
        }
    }
}