package com.havryliuk.controller.command.product;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.dao.UserType;
import com.havryliuk.service.ProductService;

import lombok.Setter;

@Setter
public class ProductCommand implements Command {
    private ProductService productService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getRequestURI().replaceAll("\\D+", ""));
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            request.setAttribute("product", product.get());
            String userType = request.getSession().getAttribute("userType").toString();
            if (UserType.valueOf(userType).equals(UserType.ADMIN)) {
                return "adminProduct.jsp";
            } else if (UserType.valueOf(userType).equals(UserType.CUSTOMER)) {
                return "customerProduct.jsp";
            }
            return "productList.jsp";
        } else {
            request.setAttribute("message", "Product not found!");
            return "error.jsp";
        }
    }
}
