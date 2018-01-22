package com.havryliuk.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.dao.UserDao;
import com.havryliuk.store.dao.UserType;

@Service
public class SecurityService {
    @Autowired
    private UserDao userDao;

    public SecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getUserIdByName(String name) {
        return userDao.getIdByName(name);
    }

    public UserType getUserType(String username, String password) {
        return userDao.getUserType(username, password);
    }
}