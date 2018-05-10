package com.havryliuk.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.dao.ProductQuantity;
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

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public boolean addProductToCart(CartEntry newCartEntry) {
        int customerId = newCartEntry.getCustomer().getId();
        int productId = newCartEntry.getProduct().getId();
        if (cartDao.recordForProductAndCustomerExists(customerId, productId)) {
            Map<String, Integer> cartEntryMap = cartDao.findByProductId(productId);
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
        for (ProductQuantity productQuantity : cartDao.findAllByCustomerId(customerId)) {
            int productId = productQuantity.getProductId();
            Product product = productService.getProductById(productId);
            Customer customer = customerService.getCustomerById(customerId);
            int quantity = productQuantity.getQuantity();
            entries.add(new CartEntry(customer, product, quantity));
        }
        return entries;
    }
}