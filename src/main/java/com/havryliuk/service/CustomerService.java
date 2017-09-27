package com.havryliuk.service;

import com.havryliuk.persistance.dao.CustomerDao;
import com.havryliuk.persistance.dao.DaoFactory;

public class CustomerService {
    public static boolean blockCustomer(int id) {
        CustomerDao dao = new DaoFactory().getCustomerDao();

        return false;
    }
}
