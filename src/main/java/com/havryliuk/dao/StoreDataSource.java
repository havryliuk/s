package com.havryliuk.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

class StoreDataSource {
    private static final Logger LOG = Logger.getLogger(StoreDataSource.class);

    private StoreDataSource() {}

    static Connection newConnection() throws ConnectionException {
        InputStream in = DaoFactory.class.getResourceAsStream("/db.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            Class.forName(properties.getProperty("db.driver"));
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            LOG.error(e);
        }
        throw new ConnectionException("Could not create connection.");
    }

}
