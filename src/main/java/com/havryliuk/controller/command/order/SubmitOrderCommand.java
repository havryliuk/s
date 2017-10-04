package com.havryliuk.controller.command.order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.CartEntry;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;
import com.havryliuk.service.CartService;
import com.havryliuk.service.CustomerService;
import com.havryliuk.service.OrderService;

public class SubmitOrderCommand extends AbstractOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int customerId = getCustomerIdFromSession(request);
        Optional<Customer> customer = new CustomerService().getCustomerById(customerId);
        List<CartEntry> cartEntries = new CartService().getCartForCustomer(customerId);
        Map<Product, Integer> orderLines = cartEntries.stream()
                .collect(Collectors.toMap(CartEntry::getProduct, CartEntry::getQuantity));

        if (customer.isPresent()) {
            Optional<Order> order = new OrderService().createOrder(customer.get(), orderLines);
            if (order.isPresent()) {
                request.setAttribute("orderId", order.get().getId());
                return "orderSubmitted.jsp";
            }
        }
        return errorPageWithMessage(request, "Order creation failed!");
    }
}
