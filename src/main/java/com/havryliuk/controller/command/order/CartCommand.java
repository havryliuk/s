package com.havryliuk.controller.command.order;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.CartEntry;
import com.havryliuk.service.CartService;

public class CartCommand extends AbstractOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int customerId = getCustomerIdFromSession(request);
        List<CartEntry> entries = new CartService().getCartForCustomer(customerId);
        request.setAttribute("entries",
                entries.stream().filter(entry -> entry.getQuantity() != 0).collect(Collectors.toList()));
        return "cart.jsp";
    }
}
