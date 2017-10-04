package com.havryliuk.controller.command.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.ProductDao;
import com.havryliuk.service.ProductService;

public class ListProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = new ProductService().getAllProducts();
        request.setAttribute("products", products);
        return "productList.jsp";
    }
}
