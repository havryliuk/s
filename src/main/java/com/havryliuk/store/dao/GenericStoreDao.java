package com.havryliuk.store.dao;

import java.util.List;
import java.util.Optional;

public interface GenericStoreDao<T> {
    List<T> findAll();
    int save(T object);
    boolean delete(T object);
    T find(int id);
    boolean update(T object);
}
