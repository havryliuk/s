package com.havryliuk.store.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Order;
import com.havryliuk.store.entity.Product;
import com.havryliuk.store.service.CartService;
import com.havryliuk.store.service.CustomerService;
import com.havryliuk.store.service.OrderService;
import com.havryliuk.store.view.ErrorViewWithMessage;

@Controller
@RequestMapping("order")
public class OrderController extends AbstractController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, CartService cartService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ModelAndView getOrder(@PathVariable("id") int id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return new ModelAndView("order/order", "order", order.get());
        } else {
            return new ErrorViewWithMessage("Order not found!");
        }
    }

    @PostMapping("/submit")
    public ModelAndView submitOrder(HttpServletRequest request) {
        int customerId = getCustomerIdFromSession(request);
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if (customer.isPresent()) {
            List<CartEntry> cartEntries = cartService.getCartForCustomer(customerId);
            Map<Product, Integer> orderLines = cartEntries.stream()
                    .collect(Collectors.toMap(CartEntry::getProduct, CartEntry::getQuantity));

            Optional<Order> order = orderService.createOrder(customer.get(), orderLines);
            if (order.isPresent()) {
                int orderId = order.get().getId();
                return new ModelAndView("order/submitted", "orderId", orderId);
            } else {
                return new ErrorViewWithMessage("Failed to submit order!");
            }
        }
        return new ErrorViewWithMessage("Customer ID: " + customerId + " not found!");
    }

    @PostMapping("/{id}/pay")
    public ModelAndView payOrder(@PathVariable("id") int id) {
        boolean result = orderService.payOrder(id);
        if (result) {
            return new ModelAndView("order/paid", "orderId", id);
        } else {
            return new ErrorViewWithMessage("Failed to pay order ID: " + id);
        }
    }
}
