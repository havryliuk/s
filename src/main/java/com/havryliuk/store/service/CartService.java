package com.havryliuk.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Product;
import com.havryliuk.store.dao.CartDao;

import lombok.Setter;

@Service
public class CartService {
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartDao cartDao;

    public boolean addProductToCart(CartEntry newCartEntry) {
        int customerId = newCartEntry.getCustomer().getId();
        int productId = newCartEntry.getProduct().getId();
        if (cartDao.recordForProductAndCustomerExists(customerId, productId)) {
            Map<String, Integer> cartEntryMap = cartDao.findByProductId(newCartEntry.getProduct().getId());
            Optional<Product> product = productService.getProductById(cartEntryMap.get("productId"));
            Optional<Customer> customer = customerService.getCustomerById(cartEntryMap.get("customerId"));
            if (product.isPresent() && customer.isPresent()) {
                CartEntry previous = new CartEntry(customer.get(), product.get(), cartEntryMap.get("quantity"));
                int newQuantity = previous.getQuantity() + newCartEntry.getQuantity();
                previous.setQuantity(newQuantity);
                return cartDao.update(previous);
            }
        }  else {
            return cartDao.save(newCartEntry) > 0;
        }
        return false;
    }

    public List<CartEntry> getCartForCustomer(int customerId) {
        List<CartEntry> entries = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartDao.findAllByCustomerId(customerId).entrySet()) {
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
