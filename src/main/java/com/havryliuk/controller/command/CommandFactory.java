package com.havryliuk.controller.command;

import com.havryliuk.controller.command.customer.BlockCustomerCommand;
import com.havryliuk.controller.command.customer.CustomerCommand;
import com.havryliuk.controller.command.customer.ListCustomersCommand;
import com.havryliuk.controller.command.order.CartCommand;
import com.havryliuk.controller.command.order.ListOrdersCommand;
import com.havryliuk.controller.command.order.OrderCommand;
import com.havryliuk.controller.command.order.PayOrderCommand;
import com.havryliuk.controller.command.order.SubmitOrderCommand;
import com.havryliuk.controller.command.product.AddProductCommand;
import com.havryliuk.controller.command.product.AddProductToCartCommand;
import com.havryliuk.controller.command.product.ListProductsCommand;
import com.havryliuk.controller.command.product.ProductCommand;
import com.havryliuk.controller.command.product.UpdateProductCommand;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.service.CartService;
import com.havryliuk.service.CustomerService;
import com.havryliuk.service.OrderService;
import com.havryliuk.service.ProductService;
import com.havryliuk.service.SecurityService;

public class CommandFactory {
    private CommandFactory() {}

    public static ProductCommand productCommand() {
        ProductCommand command = new ProductCommand();
        command.setProductService(createProductService());
        return command;
    }

    public static UpdateProductCommand updateProductCommand() {
        UpdateProductCommand command = new UpdateProductCommand();
        command.setProductService(createProductService());
        return command;
    }

    public static ListProductsCommand listProductsCommand() {
        ListProductsCommand command = new ListProductsCommand();
        command.setProductService(createProductService());
        return command;
    }

    public static AddProductToCartCommand addProductToCartCommand() {
        AddProductToCartCommand command = new AddProductToCartCommand();
        ProductService productService = createProductService();
        CustomerService customerService = CustomerService.getInstance();

        command.setProductService(productService);
        command.setCustomerService(customerService);

        CartService cartService = createCartService();
        cartService.setProductService(productService);
        cartService.setCustomerService(customerService);
        command.setCartService(cartService);
        return command;
    }

    public static SubmitOrderCommand submitOrderCommand() {
        CustomerService customerService = CustomerService.getInstance();
        CartService cartService = createCartService();
        cartService.setCustomerService(customerService);
        cartService.setProductService(createProductService());

        SubmitOrderCommand command = new SubmitOrderCommand();
        command.setCartService(cartService);
        command.setCustomerService(customerService);
        command.setOrderService(createOrderService());
        return command;
    }

    public static CustomerCommand customerCommand() {
        CustomerCommand command = new CustomerCommand();
        command.setCustomerService(CustomerService.getInstance());
        return command;
    }

    public static ListOrdersCommand listOrdersCommand() {
        ListOrdersCommand command = new ListOrdersCommand();
        command.setOrderService(createOrderService());
        return command;
    }

    public static PayOrderCommand payOrderCommand() {
        PayOrderCommand command = new PayOrderCommand();
        command.setOrderService(createOrderService());
        return command;
    }

    public static OrderCommand orderCommand() {
        OrderCommand command = new OrderCommand();
        command.setOrderService(createOrderService());
        return command;
    }

    public static CartCommand cartCommand() {
        CartCommand command = new CartCommand();
        command.setCartService(createCartService());
        return command;
    }

    public static BlockCustomerCommand blockCustomerCommand() {
        BlockCustomerCommand command = new BlockCustomerCommand();
        CustomerService customerService = CustomerService.getInstance();
        customerService.setCustomerDao(DaoFactory.getCustomerDao());
        command.setCustomerService(customerService);
        return command;
    }

    public static ListCustomersCommand listCustomersCommand() {
        ListCustomersCommand command = new ListCustomersCommand();
        command.setCustomerService(CustomerService.getInstance());
        return command;
    }

    public static AddProductCommand addProductCommand() {
        AddProductCommand command = new AddProductCommand();
        command.setProductService(createProductService());
        return command;
    }

    public static LoginCommand loginCommand() {
        LoginCommand command = new LoginCommand();
        command.setSecurityService(SecurityService.getInstance());
        return command;
    }

    private static ProductService createProductService() {
        ProductService productService = ProductService.getInstance();
        productService.setProductDao(DaoFactory.getProductDao());
        return productService;
    }

    private static CartService createCartService() {
        CartService cartService = CartService.getInstance();
        cartService.setCartDao(DaoFactory.getCartDao());
        return cartService;
    }

    private static OrderService createOrderService() {
        OrderService orderService = OrderService.getInstance();
        orderService.setOrderDao(DaoFactory.getOrderDao());
        return orderService;
    }
}
