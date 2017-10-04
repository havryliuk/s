package com.havryliuk.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Customer;
import com.havryliuk.dao.CustomerDao;
import com.havryliuk.dao.DaoFactory;

public class CustomerService {
    private final static Logger LOG = Logger.getLogger(CustomerService.class);

    public boolean blockCustomer(int id) {
        return setCustomerBlocked(id, true);
    }

    public boolean unblockCustomer(int id) {
        return setCustomerBlocked(id, false);
    }

    public Optional<Customer> getCustomerById(int id) {
        CustomerDao dao = new DaoFactory().getCustomerDao();
        return dao.find(id);
    }

    private boolean setCustomerBlocked(int id, boolean blocked) {
        CustomerDao dao = new DaoFactory().getCustomerDao();
        Optional<Customer> customer = dao.find(id);
        if (customer.isPresent()) {
            customer.get().setBlocked(blocked);
            return dao.update(customer.get());
        }
        LOG.info("Customer ID: " + id + " not found.");
        return false;
    }
}
