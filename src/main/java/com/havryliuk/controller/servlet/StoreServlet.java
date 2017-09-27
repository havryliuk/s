package com.havryliuk.controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.havryliuk.controller.command.Command;

import static com.havryliuk.controller.servlet.RequestUrlToCommandMapping.GET_REQUESTS_URL_COMMAND_MAPPING;
import static com.havryliuk.controller.servlet.RequestUrlToCommandMapping.POST_REQUESTS_URL_COMMAND_MAPPING;

@WebServlet("/store/*")
public class StoreServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, GET_REQUESTS_URL_COMMAND_MAPPING);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, POST_REQUESTS_URL_COMMAND_MAPPING);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, Command>
            mapping) throws ServletException, IOException {
        Command command = mapping.get(request.getRequestURI()
                .replace("/store/", "")
                .replaceAll("/\\d+", ""));
        String pageName = command.execute(request, response);
        request.getRequestDispatcher("/WEB-INF/view/" + pageName).forward(request, response);
    }
}
