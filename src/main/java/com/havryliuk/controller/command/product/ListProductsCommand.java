package com.havryliuk.controller.command.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.service.ProductService;

import lombok.Setter;

@Setter
public class ListProductsCommand implements Command {
    private ProductService productService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);
        return "productList.jsp";
    }
}
