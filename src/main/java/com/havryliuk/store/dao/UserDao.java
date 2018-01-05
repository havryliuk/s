package com.havryliuk.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserDao {
    private static final Logger LOG = Logger.getLogger(UserDao.class);
    private Connection connection;

    UserDao(Connection connection) {
        this.connection = connection;
    }

    public UserType getUserType(String username, String password) {
        UserType type = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT type FROM public.user" +
                " WHERE username=? AND password=?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            if (statement.execute()) {
                ResultSet rs = statement.executeQuery();
                rs.next();
                type = UserType.valueOf(rs.getString("type").toUpperCase());
            } else {
                type = null;
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return type;
    }

    public int getIdByName(String name) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM public.user" +
                " WHERE username=?")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return id;
    }
}