package com.havryliuk.store.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.havryliuk.store.dao.rowmapper.OrderRowMapper;
import com.havryliuk.store.dao.rowmapper.ProductQuantityRowMapper;
import com.havryliuk.store.entity.Order;
import com.havryliuk.store.entity.Product;

public class OrderDao implements GenericStoreDao<Order> {
    private static final Logger LOG = Logger.getLogger(OrderDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductDao productDao;

    public OrderDao(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>();
    }

    public List<Order> findAllByCustomerId(int customerId) {
        String query = "SELECT * FROM public.order WHERE customer_id = ? ORDER BY id";
        return jdbcTemplate.query(query, new OrderRowMapper(), customerId);
    }

    @Override
    public int save(Order order) {
        int orderId = createOrderHeader(order);
        if (orderId != -1) {
            order.setId(orderId);
            insertOrderLines(order);
            deleteCartEntries(order);
            return orderId;
        } else {
            return orderId;
        }
    }

    @Override
    public boolean delete(Order object) {
        return false;
    }

    @Override
    public Order find(int id) {
        String query = "SELECT paid FROM public.order WHERE id = ?";
        Boolean paid = jdbcTemplate.queryForObject(query, new Object[]{id}, Boolean.class);
        if (paid != null) {
            Map<Product, Integer> orderLines = getOrderLines(id);
            return Order.builder().id(id).paid(paid).products(orderLines).build();
        }
        return null;
    }

    private Map<Product, Integer> getOrderLines(int id) {
        String query = "SELECT * FROM order_lines WHERE order_id = ?";
        List<ProductQuantity> productQuantities = jdbcTemplate.query(query, new ProductQuantityRowMapper(), id);

        Map<Product, Integer> orderLines = new HashMap<>();
        for (ProductQuantity quantity : productQuantities) {
            Product product = productDao.find(quantity.getProductId());
            if (product != null) {
                orderLines.put(product, quantity.getQuantity());
            }
        }
        return orderLines;
    }

    @Override
    public boolean update(Order object) {
        return false;
    }

    public boolean updateAsPaid(int id) {
        String query = "UPDATE public.order SET paid = ? WHERE id = ?";
        int result = jdbcTemplate.update(query, true, id);
        return result > 0;
    }

    private int createOrderHeader(Order order) {
        String query = "INSERT INTO public.order (customer_id, paid) VALUES (?, ?)";
        final PreparedStatementCreator preparedStatementCreator = connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement
                    .RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setBoolean(2, order.isPaid());
            return preparedStatement;
        };
        final GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);

        int orderId;
        try {
            orderId = Integer.parseInt(holder.getKeys().get("id").toString());
            LOG.info("Order created: " + order.toString());
        } catch (NullPointerException e) {
            orderId = -1;
        }
        return orderId;
    }

    private void deleteCartEntries(Order order) {
        int customerId = order.getCustomer().getId();
        String query = "DELETE FROM cart WHERE customer_id = ?";
        jdbcTemplate.update(query, customerId);
    }

    private void insertOrderLines(Order order) {
        int orderId = order.getId();
        for (Map.Entry<Product, Integer> row : order.getProducts().entrySet()) {
            int productId = row.getKey().getId();
            int quantity = row.getValue();
            String query = "INSERT INTO order_lines (order_id, product_id, quantity) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, orderId, productId, quantity);
        }
    }
}