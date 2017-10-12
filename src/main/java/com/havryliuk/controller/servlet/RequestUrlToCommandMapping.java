package com.havryliuk.controller.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.havryliuk.controller.command.Command;
import com.havryliuk.controller.command.MainCommand;
import com.havryliuk.controller.command.SamePageCommand;

import static com.havryliuk.controller.command.CommandFactory.addProductCommand;
import static com.havryliuk.controller.command.CommandFactory.addProductToCartCommand;
import static com.havryliuk.controller.command.CommandFactory.blockCustomerCommand;
import static com.havryliuk.controller.command.CommandFactory.cartCommand;
import static com.havryliuk.controller.command.CommandFactory.customerCommand;
import static com.havryliuk.controller.command.CommandFactory.listCustomersCommand;
import static com.havryliuk.controller.command.CommandFactory.listOrdersCommand;
import static com.havryliuk.controller.command.CommandFactory.listProductsCommand;
import static com.havryliuk.controller.command.CommandFactory.loginCommand;
import static com.havryliuk.controller.command.CommandFactory.orderCommand;
import static com.havryliuk.controller.command.CommandFactory.payOrderCommand;
import static com.havryliuk.controller.command.CommandFactory.productCommand;
import static com.havryliuk.controller.command.CommandFactory.submitOrderCommand;
import static com.havryliuk.controller.command.CommandFactory.updateProductCommand;

final class RequestUrlToCommandMapping {
    static Command getCommandByUri(String uri, HttpMethod method) throws UnsupportedHttpMethodException {
        Command command = null;
        if (method == HttpMethod.GET) {
            if (SAME_PAGE.contains(uri)) {
                return new SamePageCommand();
            } else {
                command = GET.get(uri);
            }
        }
        if (method == HttpMethod.POST) {
            command = POST.get(uri);
        }

        if (command != null) {
            return command;
        } else {
            throw new UnsupportedHttpMethodException("Unsupported HTTP method : " + method + " for URI: " + uri);
        }
    }

    private RequestUrlToCommandMapping() {
        throw new IllegalAccessError("Utilities class. Cannot be instantiated.");
    }

    private static final List<String> SAME_PAGE = new ArrayList<>();

    static {
        SAME_PAGE.add("adminMain");
        SAME_PAGE.add("addProduct");
        SAME_PAGE.add("productAdded");
        SAME_PAGE.add("invalidProductData");
        SAME_PAGE.add("productSaved");
        SAME_PAGE.add("customerMain");
        SAME_PAGE.add("index");
    }

    private static final Map<String, Command> GET = new HashMap<>();

    static {
        GET.put("", new MainCommand());
        GET.put("productList", listProductsCommand());
        GET.put("product", productCommand());
        GET.put("customerList", listCustomersCommand());
        GET.put("customer", customerCommand());
        GET.put("cart", cartCommand());
        GET.put("orders", listOrdersCommand());
        GET.put("order", orderCommand());
    }

    private static final Map<String, Command> POST = new HashMap<>();

    static {
        POST.put("main", loginCommand());
        POST.put("addProduct", addProductCommand());
        POST.put("productUpdated", updateProductCommand());
        POST.put("customerBlock", blockCustomerCommand());
        POST.put("addedToCart", addProductToCartCommand());
        POST.put("submitOrder", submitOrderCommand());
        POST.put("payOrder", payOrderCommand());
    }
}
