package com.havryliuk.store.service;

import org.junit.Test;

import com.havryliuk.store.dao.CustomerDao;
import com.havryliuk.store.entity.Customer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Test
    public void blockCustomerTest() {
        Customer customer = Customer.builder().blocked(false).build();
        CustomerDao customerDao = mock(CustomerDao.class);
        when(customerDao.find(anyInt())).thenReturn(customer);
        CustomerService service = new CustomerService(customerDao);
        service.blockCustomer(1);
        assertTrue(customer.isBlocked());
    }

    @Test
    public void unblockCustomerTest() {
        Customer customer = Customer.builder().blocked(true).build();
        CustomerDao customerDao = mock(CustomerDao.class);
        when(customerDao.find(anyInt())).thenReturn(customer);
        CustomerService service = new CustomerService(customerDao);
        service.unblockCustomer(1);
        assertFalse(customer.isBlocked());
    }
}
