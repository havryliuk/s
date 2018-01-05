package com.havryliuk.store.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.havryliuk.store.controller.LoginController;
import com.havryliuk.store.dao.UserType;
import com.havryliuk.store.service.SecurityService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    @Test
    public void testAdminLogin() {
        testLoginForUserType(UserType.ADMIN, "adminMain.jsp");
    }

    @Test
    public void testCustomerLogin() {
        testLoginForUserType(UserType.CUSTOMER, "main.jsp");
    }

    private void testLoginForUserType(UserType userType, String expectedPage) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        SecurityService securityService = mock(SecurityService.class);
        when(securityService.getUserType(anyString(), anyString())).thenReturn(userType);
        when(securityService.getUserIdByName(anyString())).thenReturn(1);

        LoginController command = new LoginController(new SecurityService());
        String page = command.authenticate(request);
        assertThat(page).isEqualTo(expectedPage);
        verify(request).setAttribute("usertype", userType);
    }
}
