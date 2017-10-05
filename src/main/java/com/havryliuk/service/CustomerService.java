package com.havryliuk.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Customer;
import com.havryliuk.dao.CustomerDao;
import com.havryliuk.dao.DaoFactory;

public class CustomerService {
    private static final Logger LOG = Logger.getLogger(CustomerService.class);
    private static CustomerService instance;

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

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
        CustomerDao dao = DaoFactory.getCustomerDao();
        Optional<Customer> customer = dao.find(id);
        if (customer.isPresent()) {
            customer.get().setBlocked(blocked);
            return dao.update(customer.get());
        }
        LOG.info("Customer ID: " + id + " not found.");
        return false;
    }
}
