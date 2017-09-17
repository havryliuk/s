package com.havryliuk.persistance.dao;

import java.util.List;

public interface GenericStoreDao<T> {
    List<T> findAll();
    int save(T object);
    boolean delete(T object);
    T find(int id);
    boolean update(T object);
}
