package com.havryliuk.service;

import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.UserDao;

public class SecurityService {
    public int getUserIdByName(String name) {
        int id;
        UserDao dao = new DaoFactory().getUserDao();
        id = dao.getIdByName(name);
        return id;
    }
}
