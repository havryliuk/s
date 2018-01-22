package com.havryliuk.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.havryliuk.store.dao.rowmapper.UserTypeRowMapper;

public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserType getUserType(String username, String password) {
        return jdbcTemplate.queryForObject("SELECT type FROM public.user WHERE username=? AND password=?",
                new UserTypeRowMapper(),
                username, password);
    }

    public Integer getIdByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id FROM public.user WHERE username=?", new Object[] {name}, Integer.class);
    }
}