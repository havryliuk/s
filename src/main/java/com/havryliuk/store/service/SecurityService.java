package com.havryliuk.store.service;

import org.springframework.stereotype.Service;

import com.havryliuk.store.dao.DaoFactory;
import com.havryliuk.store.dao.UserDao;
import com.havryliuk.store.dao.UserType;

@Service
public class SecurityService {

    public int getUserIdByName(String name) {
        UserDao dao = DaoFactory.getUserDao();
        return dao.getIdByName(name);
    }

    public UserType getUserType(String username, String password) {
        return DaoFactory.getUserDao().getUserType(username, password);
    }
}