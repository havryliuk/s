package com.havryliuk.store.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.dao.CustomerDao;

@Service
public class CustomerService {
    private static final Logger LOG = Logger.getLogger(CustomerService.class);
    @Autowired
    private CustomerDao customerDao;

    public void blockCustomer(int id) {
        setCustomerBlocked(id, true);
        LOG.info("Customer ID: " + id + " blocked.");
    }

    public void unblockCustomer(int id) {
        setCustomerBlocked(id, false);
        LOG.info("Customer ID: " + id + " unblocked.");
    }

    public Customer getCustomerById(int id) {
        Customer customer;
        try {
            customer = customerDao.find(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        LOG.info("Customer found: " + customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    private void setCustomerBlocked(int id, boolean blocked) {
        Customer customer = customerDao.find(id);
        customer.setBlocked(blocked);
        customerDao.update(customer);
    }
}
