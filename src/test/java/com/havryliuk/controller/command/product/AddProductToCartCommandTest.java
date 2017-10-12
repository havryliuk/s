package com.havryliuk.controller.command.product;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.havryliuk.entity.CartEntry;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Product;
import com.havryliuk.service.CartService;
import com.havryliuk.service.CustomerService;
import com.havryliuk.service.ProductService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddProductToCartCommandTest {
    @Test
    public void testProductAddedToCart() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(String.valueOf(1));
        when(request.getParameter("quantity")).thenReturn(String.valueOf(1));

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userId")).thenReturn(1);
        when(request.getSession()).thenReturn(session);

        ProductService productService = mock(ProductService.class);
        Product product = mock(Product.class);
        when(productService.getProductById(anyInt())).thenReturn(Optional.ofNullable(product));

        CustomerService customerService = mock(CustomerService.class);
        Customer customer = mock(Customer.class);
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.ofNullable(customer));

        CartService cartService = mock(CartService.class);
        when(cartService.addProductToCart(any())).thenReturn(true);

        AddProductToCartCommand command = new AddProductToCartCommand();
        command.setProductService(productService);
        command.setCustomerService(customerService);
        command.setCartService(cartService);

        String page = command.execute(request, mock(HttpServletResponse.class));
        assertThat(page).isEqualTo("addedToCart.jsp");
        verify(request).setAttribute("cartEntry", new CartEntry(customer, product, 1));
    }

    @Test
    public void testProductNotPresentToAddToCart() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(String.valueOf(1));
        when(request.getParameter("quantity")).thenReturn(String.valueOf(1));

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userId")).thenReturn(1);
        when(request.getSession()).thenReturn(session);

        ProductService productService = mock(ProductService.class);
        when(productService.getProductById(anyInt())).thenReturn(Optional.empty());

        CustomerService customerService = mock(CustomerService.class);
        Customer customer = mock(Customer.class);
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.ofNullable(customer));

        AddProductToCartCommand command = new AddProductToCartCommand();
        command.setProductService(productService);
        command.setCustomerService(customerService);

        String page = command.execute(request, mock(HttpServletResponse.class));
        assertThat(page).isEqualTo("failedToAddToCart.jsp");
    }
}
