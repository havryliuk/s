package com.havryliuk.store.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Product;
import com.havryliuk.store.dao.UserType;
import com.havryliuk.store.entity.ProductCategory;
import com.havryliuk.store.service.CartService;
import com.havryliuk.store.service.CustomerService;
import com.havryliuk.store.service.ProductService;
import com.havryliuk.store.view.ErrorViewWithMessage;

@Controller
@RequestMapping("/product")
public class ProductController extends AbstractController {
    private static final String PRODUCT = "product";
    private final ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CartService cartService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ModelAndView getProductById(HttpServletRequest request, @PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            UserType userType = UserType.valueOf(request.getSession().getAttribute("userType").toString());
            if (userType.equals(UserType.ADMIN)) {
                return new ModelAndView("admin/product", PRODUCT, product);
            } else {
                return new ModelAndView("customer/product", PRODUCT, product);
            }
        }
        return new ErrorViewWithMessage("Product not found!");
    }

    @GetMapping("/list")
    public ModelAndView getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ModelAndView(PRODUCT + "/list", "products", products);
    }

    @GetMapping("/add")
    public String addProduct() {
        return PRODUCT + "/add";
    }

    @PostMapping("/add")
    public ModelAndView addProduct(@RequestParam("description") String description,
                                   @RequestParam("price") BigDecimal price,
                                   @RequestParam("category") ProductCategory category) {
        if (!description.isEmpty() && price.compareTo(new BigDecimal(0)) > 0) {
            Product product = Product.builder().category(category).price(price).description(description).build();
            int id = productService.addProduct(product);
            if (id > 0) {
                product.setId(id);
                return new ModelAndView(PRODUCT + "/created", PRODUCT, product);
            }
        } else {
            return new ModelAndView(PRODUCT + "/invalidData", ImmutableMap.of("description", description, "price",
                    price));

        }
        return new ModelAndView("error");
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam("id") int id,
                               @RequestParam("description") String description,
                               @RequestParam("price") String stringPrice,
                               @RequestParam("category") ProductCategory category) {
        BigDecimal price = new BigDecimal(stringPrice);
        Product product = Product.builder().id(id).description(description).price(price).category(category).build();

        boolean result = productService.updateProduct(product);
        if (result) {
            return new ModelAndView(PRODUCT + "/saved", PRODUCT, product);
        } else {
            return new ModelAndView(PRODUCT + "/updateFailed");
        }
    }

    @PostMapping("/addToCart")
    public ModelAndView addProductToCart(@RequestParam("id") int productId,
                                         @RequestParam("quantity") int quantity,
                                         HttpServletRequest request) {
        if (quantity == 0) {
            return new ErrorViewWithMessage("You cannot add to cart product of zero quantity!");
        }

        int customerId = getCustomerIdFromSession(request);

        Product product = productService.getProductById(productId);
        Customer customer = customerService.getCustomerById(customerId);
        if (product == null) {
            return new ErrorViewWithMessage("Product ID: " + productId + " not found!");
        }
        if (customer == null) {
            return new ErrorViewWithMessage("Customer ID: " + customerId + " not found!");
        }
        CartEntry cartEntry = new CartEntry(customer, product, quantity);
        if (cartService.addProductToCart(cartEntry)) {
            return new ModelAndView(PRODUCT + "/addedToCart", "cartEntry", cartEntry);
        } else {
            return new ErrorViewWithMessage("Failed to add product to cart!");
        }
    }
}