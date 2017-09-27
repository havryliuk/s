package com.havryliuk.persistance.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private Connection connection;

    public DaoFactory() {
        InputStream in = DaoFactory.class.getResourceAsStream("/db.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public ProductDao getProductDao() {
        return new ProductDao(connection);
    }

    public UserDao getUserDao() {
        return new UserDao(connection);
    }

    public CustomerDao getCustomerDao() {
        return new CustomerDao(connection);
    }
}
