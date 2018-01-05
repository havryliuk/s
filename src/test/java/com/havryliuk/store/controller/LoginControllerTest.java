package com.havryliuk.store.controller;

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
        testLoginForUserType(UserType.ADMIN, "admin/main", "admin");
    }

    @Test
    public void testCustomerLogin() {
        testLoginForUserType(UserType.CUSTOMER, "customer/main", "user");
    }

    private void testLoginForUserType(UserType userType, String expectedPage, String username) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(username);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        SecurityService securityService = mock(SecurityService.class);
        when(securityService.getUserType(anyString(), anyString())).thenReturn(userType);
        when(securityService.getUserIdByName(anyString())).thenReturn(1);

        LoginController controller = new LoginController(new SecurityService());
        String page = controller.authenticate(request);
        assertThat(page).isEqualTo(expectedPage);
        verify(request).setAttribute("usertype", userType);
    }
}
