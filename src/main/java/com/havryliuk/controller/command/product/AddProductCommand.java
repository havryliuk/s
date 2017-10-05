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
public class AddProductCommand implements Command {
    private ProductService productService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        ProductCategory category = ProductCategory.valueOf(request.getParameter("category").toUpperCase());

        if (!description.isEmpty() && price.compareTo(new BigDecimal(0)) > 0) {
            Product product = Product.builder().category(category).price(price).description(description).build();
            int id = productService.addProduct(product);
            if (id > 0) {
                product.setId(id);

                request.setAttribute("product", product);
                return "productAdded.jsp";
            }
        } else {
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            return "invalidProductData.jsp";

        }
        return "error.jsp";
    }
}
