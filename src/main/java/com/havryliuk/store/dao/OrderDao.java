package com.havryliuk.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.store.entity.Order;
import com.havryliuk.store.entity.Product;

public class OrderDao implements GenericStoreDao<Order> {
    private static final Logger LOG = Logger.getLogger(OrderDao.class);
    private Connection connection;

    OrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>();
    }

    public List<Order> findAllByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.order" +
                " WHERE customer_id = ? ORDER BY id")) {
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                boolean paid = rs.getBoolean("paid");
                orders.add(Order.builder().id(orderId).paid(paid).build());
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return orders;
    }

    @Override
    public int save(Order order) {
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO public.order (customer_id, paid)" +
                " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            statement.setInt(1, order.getCustomer().getId());
            statement.setBoolean(2, order.isPaid());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                order.setId(id);
            } else {
                connection.rollback();
                return id;
            }

            if (!insertOrderLines(order)) {
                connection.rollback();
                return -1;
            }

            if (!deleteCartEntries(order)) {
                connection.rollback();
                return -1;
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e1);
            }
            LOG.error(e);
        }
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return id;
    }

    private boolean deleteCartEntries(Order order) {
        try (PreparedStatement deleteCartStatement = connection.prepareStatement("DELETE FROM cart WHERE " +
                "customer_id=?")) {
            deleteCartStatement.setInt(1, order.getCustomer().getId());
            int result = deleteCartStatement.executeUpdate();
            if (result <= 0) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return true;
    }

    private boolean insertOrderLines(Order order) {
        for (Map.Entry<Product, Integer> row : order.getProducts().entrySet()) {
            int productId = row.getKey().getId();
            try (PreparedStatement linesStatement = connection.prepareStatement("INSERT INTO order_lines " +
                    "(order_id, product_id, quantity) VALUES (?, ?, ?)")) {
                linesStatement.setInt(1, order.getId());
                linesStatement.setInt(2, productId);
                linesStatement.setInt(3, row.getValue());
                int result = linesStatement.executeUpdate();
                if (result <= 0) {
                    return false;
                }
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
        return true;
    }

    @Override
    public boolean delete(Order object) {
        return false;
    }

    @Override
    public Optional<Order> find(int id) {
        boolean paid;
        try (PreparedStatement statement = connection.prepareStatement("SELECT paid FROM public.order WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                paid = rs.getBoolean("paid");
                Map<Product, Integer> orderLines = getOrderLines(id);
                return Optional.ofNullable(Order.builder().id(id).paid(paid).products(orderLines).build());
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return Optional.empty();
    }

    private Map<Product, Integer> getOrderLines(int id) {
        Map<Product, Integer> orderLines = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_lines" +
                " WHERE order_id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Optional<Product> product = DaoFactory.getProductDao().find(productId);
                if (product.isPresent()) {
                    int quantity = rs.getInt("quantity");
                    orderLines.put(product.get(), quantity);
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
        }

        return orderLines;
    }

    @Override
    public boolean update(Order object) {
        return false;
    }

    public boolean payOrder(int id) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE public.order SET paid=?" +
                " WHERE id=?")) {
            statement.setBoolean(1, true);
            statement.setInt(2, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return false;
    }
}
