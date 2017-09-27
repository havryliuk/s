package com.havryliuk.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    UserDao(Connection connection) {
        this.connection = connection;
    }

    public UserType getType(String username, String password) {
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
            e.printStackTrace();
        }
        return type;
    }
}