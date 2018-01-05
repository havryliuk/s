package com.havryliuk.store.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.dao.CustomerDao;
import com.havryliuk.store.dao.DaoFactory;

@Service
public class CustomerService {
    private static final Logger LOG = Logger.getLogger(CustomerService.class);
    @Autowired
    private CustomerDao customerDao;

    public boolean blockCustomer(int id) {
        return setCustomerBlocked(id, true);
    }

    public boolean unblockCustomer(int id) {
        return setCustomerBlocked(id, false);
    }

    public Optional<Customer> getCustomerById(int id) {
        return DaoFactory.getCustomerDao().find(id);
    }

    public List<Customer> getAllCustomers() {
        return DaoFactory.getCustomerDao().findAll();
    }

    private boolean setCustomerBlocked(int id, boolean blocked) {
        Optional<Customer> customer = customerDao.find(id);
        if (customer.isPresent()) {
            customer.get().setBlocked(blocked);
            return customerDao.update(customer.get());
        }
        LOG.info("Customer ID: " + id + " not found.");
        return false;
    }
}
