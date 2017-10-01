package com.havryliuk.controller.servlet;

import java.util.HashMap;
import java.util.Map;

import com.havryliuk.controller.command.order.CartCommand;
import com.havryliuk.controller.command.order.ListOrdersCommand;
import com.havryliuk.controller.command.order.OrderCommand;
import com.havryliuk.controller.command.order.PayOrderCommand;
import com.havryliuk.controller.command.order.SubmitOrderCommand;
import com.havryliuk.controller.command.product.AddProductCommand;
import com.havryliuk.controller.command.customer.BlockCustomerCommand;
import com.havryliuk.controller.command.Command;
import com.havryliuk.controller.command.customer.CustomerCommand;
import com.havryliuk.controller.command.customer.ListCustomersCommand;
import com.havryliuk.controller.command.product.AddProductToCartCommand;
import com.havryliuk.controller.command.product.ListProductsCommand;
import com.havryliuk.controller.command.LoginCommand;
import com.havryliuk.controller.command.MainCommand;
import com.havryliuk.controller.command.product.ProductCommand;
import com.havryliuk.controller.command.SamePageCommand;
import com.havryliuk.controller.command.product.UpdateProductCommand;

final class RequestUrlToCommandMapping {
    final static Map<String, Command> GET_REQUESTS_URL_COMMAND_MAPPING = new HashMap<>();
    static {
        GET_REQUESTS_URL_COMMAND_MAPPING.put("", new MainCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("productList", new ListProductsCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("product", new ProductCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("adminMain", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("addProduct", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("productAdded", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("invalidProductData", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("product", new ProductCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("productSaved", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("customerList", new ListCustomersCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("customer", new CustomerCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("customerMain", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("index", new SamePageCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("cart", new CartCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("orders", new ListOrdersCommand());
        GET_REQUESTS_URL_COMMAND_MAPPING.put("order", new OrderCommand());
    }
    final static Map<String, Command> POST_REQUESTS_URL_COMMAND_MAPPING = new HashMap<>();
    static {
        POST_REQUESTS_URL_COMMAND_MAPPING.put("main", new LoginCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("addProduct", new AddProductCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("productUpdated", new UpdateProductCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("customerBlock", new BlockCustomerCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("addedToCart", new AddProductToCartCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("submitOrder", new SubmitOrderCommand());
        POST_REQUESTS_URL_COMMAND_MAPPING.put("payOrder", new PayOrderCommand());
    }
}
