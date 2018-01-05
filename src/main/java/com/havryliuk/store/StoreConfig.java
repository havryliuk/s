package com.havryliuk.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.havryliuk.store.controller.LoginController;
import com.havryliuk.store.controller.PagesController;
import com.havryliuk.store.controller.CustomerController;
import com.havryliuk.store.controller.OrderController;
import com.havryliuk.store.controller.ProductController;
import com.havryliuk.store.dao.CartDao;
import com.havryliuk.store.dao.CustomerDao;
import com.havryliuk.store.dao.DaoFactory;
import com.havryliuk.store.dao.OrderDao;
import com.havryliuk.store.dao.ProductDao;
import com.havryliuk.store.service.CartService;
import com.havryliuk.store.service.CustomerService;
import com.havryliuk.store.service.OrderService;
import com.havryliuk.store.service.ProductService;
import com.havryliuk.store.service.SecurityService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.havryliuk.store"})

public class StoreConfig {
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(securityService());
    }

    @Bean
    public ProductController productController() {
        return new ProductController(productService());
    }

    @Bean
    public CustomerController customerController() {
        return new CustomerController(customerService(), cartService(), orderService());
    }

    @Bean
    public PagesController pagesController() {
        return new PagesController();
    }

    @Bean
    public OrderController orderController() {
        return new OrderController(orderService(), customerService(), cartService());
    }

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    public SecurityService securityService() {
        return new SecurityService();
    }

    @Bean
    public CartService cartService() {
        return new CartService();
    }

    @Bean
    public ProductDao productDao() {
        return DaoFactory.getProductDao();
    }

    @Bean
    public CustomerDao customerDao() {
        return DaoFactory.getCustomerDao();
    }

    @Bean
    public CartDao cartDao() {
        return DaoFactory.getCartDao();
    }

    @Bean
    public OrderDao orderDao() {
        return DaoFactory.getOrderDao();
    }
}