package com.havryliuk.dao;

import java.util.List;
import java.util.Optional;

public interface GenericStoreDao<T> {
    List<T> findAll();
    int save(T object);
    boolean delete(T object);
    Optional<T> find(int id);
    boolean update(T object);
}
