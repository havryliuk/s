package com.havryliuk.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.entity.Product;
import com.havryliuk.persistance.dao.DaoFactory;
import com.havryliuk.persistance.dao.ProductDao;

@WebServlet("/productList")
public class StoreServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new DaoFactory().getProductDao();
        List<Product> products = dao.findAll();

        request.setAttribute("products", products);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
