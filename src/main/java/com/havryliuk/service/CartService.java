package com.havryliuk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.havryliuk.entity.CartEntry;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Product;
import com.havryliuk.dao.CartDao;
import com.havryliuk.dao.DaoFactory;

import lombok.Setter;

@Setter
public class CartService {
    private ProductService productService;
    private CustomerService customerService;
    private static CartService instance;

    private CartService() {}

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public boolean addProductToCart(CartEntry newCartEntry) {
        CartDao dao = DaoFactory.getCartDao();
        int customerId = newCartEntry.getCustomer().getId();
        int productId = newCartEntry.getProduct().getId();
        if (dao.recordForProductAndCustomerExists(customerId, productId)) {
            Map<String, Integer> cartEntryMap = dao.findByProductId(newCartEntry.getProduct().getId());
            Optional<Product> product = productService.getProductById(cartEntryMap.get("productId"));
            Optional<Customer> customer = customerService.getCustomerById(cartEntryMap.get("customerId"));
            if (product.isPresent() && customer.isPresent()) {
                CartEntry previous = new CartEntry(customer.get(), product.get(), cartEntryMap.get("quantity"));
                int newQuantity = previous.getQuantity() + newCartEntry.getQuantity();
                previous.setQuantity(newQuantity);
                return dao.update(previous);
            }
        }  else {
            return dao.save(newCartEntry) > 0;
        }
        return false;
    }

    public List<CartEntry> getCartForCustomer(int customerId) {
        CartDao dao = DaoFactory.getCartDao();
        List<CartEntry> entries = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : dao.findAllByCustomerId(customerId).entrySet()) {
            int productId = entry.getKey();
            Optional<Product> product = productService.getProductById(productId);
            Optional<Customer> customer = customerService.getCustomerById(customerId);
            if (product.isPresent() && customer.isPresent()) {
                entries.add(new CartEntry(customer.get(), product.get(), entry.getValue()));
            }
        }
        return entries;
    }
}
