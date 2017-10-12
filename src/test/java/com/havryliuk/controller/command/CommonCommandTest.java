package com.havryliuk.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.havryliuk.controller.command.customer.BlockCustomerCommand;
import com.havryliuk.service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommonCommandTest {
    @Test
    public void testErrorPageWithMessage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(String.valueOf(1));

        CustomerService customerService = mock(CustomerService.class);
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.empty());

        BlockCustomerCommand command = new BlockCustomerCommand();
        command.setCustomerService(customerService);
        String page = command.execute(request, mock(HttpServletResponse.class));
        assertThat(page).isEqualTo("error.jsp");
        verify(request).setAttribute("message", "Customer to block/unblock not found.");
    }
}
