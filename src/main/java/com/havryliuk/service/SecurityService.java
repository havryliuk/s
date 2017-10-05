package com.havryliuk.service;

import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.UserDao;
import com.havryliuk.dao.UserType;

public class SecurityService {
    private static SecurityService instance;

    private SecurityService() {}

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
        }
        return instance;
    }

    public int getUserIdByName(String name) {
        int id;
        UserDao dao = DaoFactory.getUserDao();
        id = dao.getIdByName(name);
        return id;
    }

    public UserType getUserType(String username, String password) {
        return DaoFactory.getUserDao().getUserType(username, password);
    }
}
