package com.havryliuk.controller.command.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.persistance.dao.DaoFactory;
import com.havryliuk.persistance.dao.ProductDao;

public class ProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        ProductDao dao = new DaoFactory().getProductDao();
        Product product = dao.find(id);
        request.setAttribute("product", product);
        return "product.jsp";
    }
}
