package com.havryliuk.controller.command.product;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.CartEntry;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Product;
import com.havryliuk.service.CartService;
import com.havryliuk.service.CustomerService;
import com.havryliuk.service.ProductService;

import lombok.Setter;

@Setter
public class AddProductToCartCommand implements Command {
    private ProductService productService;
    private CustomerService customerService;
    private CartService cartService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int customerId = (int) request.getSession().getAttribute("userId");

        Optional<Product> product = productService.getProductById(productId);
        Optional<Customer> customer = customerService.getCustomerById(customerId);

        if (product.isPresent() && customer.isPresent()) {
            CartEntry cartEntry = new CartEntry(customer.get(), product.get(), quantity);
            if (cartService.addProductToCart(cartEntry)) {
                request.setAttribute("cartEntry", cartEntry);
                return "addedToCart.jsp";
            }
        }
        return "failedToAddToCart.jsp";
    }
}
