package com.havryliuk.controller.command.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.persistance.dao.DaoFactory;
import com.havryliuk.persistance.dao.ProductDao;

public class ListProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao dao = new DaoFactory().getProductDao();
        List<Product> products = dao.findAll();
        request.setAttribute("products", products);

        return "productList.jsp";
    }
}
