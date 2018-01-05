package com.havryliuk.store.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class DaoFactory {
    private static final Logger LOG = Logger.getLogger(DaoFactory.class);

    private DaoFactory() {}

    public static ProductDao getProductDao() {
        return new ProductDao(newConnection());
    }

    public static UserDao getUserDao() {
        return new UserDao(newConnection());
    }

    public static CustomerDao getCustomerDao() {
        return new CustomerDao(newConnection());
    }

    public static CartDao getCartDao() {
        return new CartDao(newConnection());
    }

    public static OrderDao getOrderDao() {
        return new OrderDao(newConnection());
    }

    private static Connection newConnection() {
        try {
            return StoreDataSource.newConnection();
        } catch (ConnectionException e) {
            LOG.error(e);
        }
        return null;
    }
}
