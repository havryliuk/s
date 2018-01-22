package com.havryliuk.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Product;
import com.havryliuk.store.dao.CartDao;

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
            Product product = productService.getProductById(cartEntryMap.get("productId"));
            Customer customer = customerService.getCustomerById(cartEntryMap.get("customerId"));
            if (product != null) {
                CartEntry previous = new CartEntry(customer, product, cartEntryMap.get("quantity"));
                int newQuantity = previous.getQuantity() + newCartEntry.getQuantity();
                previous.setQuantity(newQuantity);
                return cartDao.update(previous);
            }
        } else {
            return cartDao.save(newCartEntry) > 0;
        }
        return false;
    }

    public List<CartEntry> getCartForCustomer(int customerId) {
        List<CartEntry> entries = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartDao.findAllByCustomerId(customerId).entrySet()) {
            int productId = entry.getKey();
            Product product = productService.getProductById(productId);
            Customer customer = customerService.getCustomerById(customerId);
            entries.add(new CartEntry(customer, product, entry.getValue()));
        }
        return entries;
    }
}
