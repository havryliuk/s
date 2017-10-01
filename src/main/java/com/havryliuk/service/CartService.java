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

public class CartService {

    public boolean addProductToCart(CartEntry newCartEntry) {
        CartDao dao = new DaoFactory().getCartDao();
        if (dao.recordForProductIdExists(newCartEntry.getProduct().getId())) {
            Map<String, Integer> cartEntryMap = dao.findByProductId(newCartEntry.getProduct().getId());
            Optional<Product> product = new ProductService().getProductById(cartEntryMap.get("productId"));
            Optional<Customer> customer = new CustomerService().getCustomerById(cartEntryMap.get("customerId"));
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

    public List<CartEntry> getCartForCustomer(int id) {
        CartDao dao = new DaoFactory().getCartDao();
        List<CartEntry> entries = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : dao.findAllByCustomerId(id).entrySet()) {
            int productId = entry.getKey();
            Optional<Product> product = new ProductService().getProductById(productId);
            Optional<Customer> customer = new CustomerService().getCustomerById(id);
            if (product.isPresent() && customer.isPresent()) {
                entries.add(new CartEntry(customer.get(), product.get(), entry.getValue()));
            }
        }
        return entries;
    }
}
