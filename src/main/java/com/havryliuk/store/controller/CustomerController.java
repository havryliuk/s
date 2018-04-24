package com.havryliuk.store.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Order;
import com.havryliuk.store.service.CartService;
import com.havryliuk.store.service.CustomerService;
import com.havryliuk.store.service.OrderService;
import com.havryliuk.store.view.ErrorViewWithMessage;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {
    private static final String CUSTOMER = "customer";
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;
    @Autowired
    public CustomerController(CustomerService customerService, CartService cartService, OrderService orderService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ModelAndView getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ModelAndView(CUSTOMER + "/list", "customers", customers);
    }

    @GetMapping("/{id}")
    public ModelAndView getCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ModelAndView(CUSTOMER + "/customer", CUSTOMER, customer);
        } else {
            return customerIdNotFound(id);
        }
    }

    @PostMapping("/block")
    public ModelAndView block(@RequestParam("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            if (!customer.isBlocked()) {
                customerService.blockCustomer(id);
            }
            return new ModelAndView(CUSTOMER + "/blocked", CUSTOMER, customer);
        } else {
            return customerIdNotFound(id);
        }
    }

    @PostMapping("/unblock")
    public ModelAndView unblock(@RequestParam("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            if (customer.isBlocked()) {
                customerService.unblockCustomer(id);
            }
            return new ModelAndView(CUSTOMER + "/unblocked", CUSTOMER, customer);
        } else {
            return customerIdNotFound(id);
        }
    }

    @GetMapping("/cart")
    public ModelAndView getCart(HttpServletRequest request) {
        int customerId = getCustomerIdFromSession(request);
        List<CartEntry> entries = cartService.getCartForCustomer(customerId);
        return new ModelAndView(CUSTOMER + "/cart", "entries",
                entries.stream().filter(entry -> entry.getQuantity() != 0).collect(Collectors.toList()));
    }

    @GetMapping("/orders")
    public ModelAndView getCustomerOrders(HttpServletRequest request) {
        int customerId = getCustomerIdFromSession(request);
        List<Order> orders = orderService.getOrdersForCustomer(customerId);
        return new ModelAndView(CUSTOMER + "/orders", "orders", orders);
    }

    private ModelAndView customerIdNotFound(int id) {
        return new ErrorViewWithMessage( "Customer ID: " + id + " not found.");
    }
}