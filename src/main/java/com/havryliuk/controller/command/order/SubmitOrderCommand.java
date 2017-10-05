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

import lombok.Setter;

@Setter
public class SubmitOrderCommand extends AbstractOrderCommand implements Command {
    private CustomerService customerService;
    private CartService cartService;
    private OrderService orderService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int customerId = getCustomerIdFromSession(request);
        Optional<Customer> customer = customerService.getCustomerById(customerId);

        if (customer.isPresent()) {
            List<CartEntry> cartEntries = cartService.getCartForCustomer(customerId);
            Map<Product, Integer> orderLines = cartEntries.stream()
                    .collect(Collectors.toMap(CartEntry::getProduct, CartEntry::getQuantity));

            Optional<Order> order = orderService.createOrder(customer.get(), orderLines);
            if (order.isPresent()) {
                request.setAttribute("orderId", order.get().getId());
                return "orderSubmitted.jsp";
            }
        }
        return errorPageWithMessage(request, "Order creation failed!");
    }
}
