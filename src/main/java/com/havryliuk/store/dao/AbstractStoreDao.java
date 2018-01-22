package com.havryliuk.store.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractStoreDao implements GenericStoreDao {
    @Autowired
    private DataSource dataSource;
}