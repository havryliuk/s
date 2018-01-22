package com.havryliuk.store.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.havryliuk.store.dao.UserType;

public class UserTypeRowMapper implements RowMapper<UserType> {
    @Nullable
    @Override
    public UserType mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserType.valueOf(resultSet.getString("type").toUpperCase());
    }
}
