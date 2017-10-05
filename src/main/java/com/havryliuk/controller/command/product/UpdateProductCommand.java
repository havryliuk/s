package com.havryliuk.controller.command.product;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;
import com.havryliuk.entity.Product;
import com.havryliuk.entity.ProductCategory;
import com.havryliuk.service.ProductService;

import lombok.Setter;

@Setter
public class UpdateProductCommand implements Command {
    private ProductService productService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        ProductCategory category = ProductCategory.valueOf(request.getParameter("category").toUpperCase());
        Product product = Product.builder().id(id).description(description).price(price).category(category).build();

        boolean result = productService.updateProduct(product);
        if (result) {
            request.setAttribute("product", product);
            return "productSaved.jsp";
        } else {
            return "productUpdateFailed.jsp";
        }
    }
}
