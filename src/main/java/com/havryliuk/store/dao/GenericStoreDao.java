package com.havryliuk.store.dao;

import java.util.List;

public interface GenericStoreDao<T> {
    List<T> findAll();
    int save(T object);
    T find(int id);
    boolean update(T object);
}
