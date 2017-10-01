package com.havryliuk.service;

import java.util.Optional;

import com.havryliuk.entity.Customer;
import com.havryliuk.dao.CustomerDao;
import com.havryliuk.dao.DaoFactory;

public class CustomerService {
    public boolean blockCustomer(int id) {
        return setCustomerBlocked(id, true);
    }

    public boolean unblockCustomer(int id) {
        return setCustomerBlocked(id, false);
    }

    private boolean setCustomerBlocked(int id, boolean blocked) {
        CustomerDao dao = new DaoFactory().getCustomerDao();
        Optional<Customer> customer = dao.find(id);
        if (customer.isPresent()) {
            customer.get().setBlocked(blocked);
            return dao.update(customer.get());
        }
        return false;
    }

    public Optional<Customer> getCustomerById(int id) {
        CustomerDao dao = new DaoFactory().getCustomerDao();
        return dao.find(id);
    }
}
