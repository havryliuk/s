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
        //InputStream in = DaoFactory.class.getResourceAsStream("/db.properties");
        //Properties properties = new Properties();
        try {
            //properties.load(in);
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/store",
                    "postgres", "juswenko");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ProductDao getProductDao() {
        return new ProductDao(connection);
    }
}
