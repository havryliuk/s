package com.havryliuk.store.spring;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.havryliuk.store.service.CartService;
import com.havryliuk.store.service.CustomerService;
import com.havryliuk.store.service.OrderService;
import com.havryliuk.store.service.ProductService;
import com.havryliuk.store.service.SecurityService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.havryliuk.store"})
public class StoreConfig {
    private final JdbcConfig jdbcConfig;
    private final RepositoryConfig repositoryConfig;

    @Autowired
    public StoreConfig(JdbcConfig jdbcConfig, RepositoryConfig repositoryConfig) {
        this.jdbcConfig = jdbcConfig;
        this.repositoryConfig = repositoryConfig;
    }

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
        return new CustomerService(jdbcConfig.customerDao());
    }

    @Bean
    public SecurityService securityService() {
        return new SecurityService(jdbcConfig.userDao());
    }

    @Bean
    public CartService cartService() {
        return new CartService(jdbcConfig.cartDao());
    }
}