package com.havryliuk.controller.servlet;

import org.junit.Test;

import com.havryliuk.controller.command.Command;
import com.havryliuk.controller.command.SamePageCommand;
import com.havryliuk.controller.command.customer.CustomerCommand;
import com.havryliuk.controller.command.product.AddProductCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestUrlToCommandMappingTest {
    @Test
    public void testReturnSamePageCommand() throws UnsupportedHttpMethodException {
        Command command = RequestUrlToCommandMapping.getCommandByUri("adminMain", HttpMethod.GET);
        assertThat(command).isInstanceOf(SamePageCommand.class);
    }

    @Test
    public void testReturnCustomerCommand() throws UnsupportedHttpMethodException {
        Command command = RequestUrlToCommandMapping.getCommandByUri("customer", HttpMethod.GET);
        assertThat(command).isInstanceOf(CustomerCommand.class);
    }

    @Test
    public void testReturnAddProductCommand() throws UnsupportedHttpMethodException {
        Command command = RequestUrlToCommandMapping.getCommandByUri("addProduct", HttpMethod.POST);
        assertThat(command).isInstanceOf(AddProductCommand.class);
    }

    @Test(expected = UnsupportedHttpMethodException.class)
    public void testUnsupportedHttpMethodException() throws UnsupportedHttpMethodException {
        RequestUrlToCommandMapping.getCommandByUri("badUri", HttpMethod.POST);
    }
}
