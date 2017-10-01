package com.havryliuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;

public class OrderDao implements GenericStoreDao<Order> {
    private Connection connection;

    OrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    public List<Order> findAllByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.order" +
                " WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                boolean paid = rs.getBoolean("paid");
                orders.add(Order.builder().id(orderId).paid(paid).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int save(Order order) {
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO public.order (customer_id, paid)" +
                " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getCustomer().getId());
            statement.setBoolean(2, order.isPaid());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                order.setId(id);
            } else {
                return id;
            }

            for (Map.Entry<Product, Integer> row : order.getProducts().entrySet()) {
                int productId = row.getKey().getId();
                PreparedStatement linesStatement = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, quantity)" +
                        " VALUES (?, ?, ?)");
                linesStatement.setInt(1, order.getId());
                linesStatement.setInt(2, productId);
                linesStatement.setInt(3, row.getValue());
                int result = linesStatement.executeUpdate();
                if (result <= 0) {
                    return -1;
                }
            }

            PreparedStatement deleteCartStatement = connection.prepareStatement("DELETE FROM cart WHERE customer_id=?");
            deleteCartStatement.setInt(1, order.getCustomer().getId());
            int result = deleteCartStatement.executeUpdate();
            if (result <= 0) {
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean delete(Order object) {
        return false;
    }

    @Override
    public Optional<Order> find(int id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Order object) {
        return false;
    }
}
