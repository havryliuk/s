package com.havryliuk.service;

import java.util.Optional;

import org.junit.Test;

import com.havryliuk.dao.CustomerDao;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Test
    public void testCustomerForBlockageNotFound() {
        CustomerDao mockCustomerDao = mock(CustomerDao.class);
        when(mockCustomerDao.find(anyInt())).thenReturn(Optional.empty());

        CustomerService service = CustomerService.getInstance();
        service.setCustomerDao(mockCustomerDao);
        boolean result = service.blockCustomer(anyInt());
        assertFalse(result);
    }

}
