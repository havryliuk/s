package com.havryliuk.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController extends AbstractController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ModelAndView getCustomerCart(HttpServletRequest request) {
        int customerId = getCustomerIdFromSession(request);
        List<CartEntry> entries = cartService.getCartForCustomer(customerId);
        return new ModelAndView("customer/cart", "entries", entries);
    }
}