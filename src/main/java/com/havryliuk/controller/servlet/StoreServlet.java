package com.havryliuk.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.havryliuk.controller.command.Command;

@WebServlet("/store/*")
public class StoreServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(StoreServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response, HttpMethod.GET);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response, HttpMethod.POST);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod method) {
        String uri = request.getRequestURI()
                .replace("/store/", "")
                .replaceAll("/\\d+", "");
        try {
            Command command = RequestUrlToCommandMapping.getCommandByUri(uri, method);
            String pageName = command.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/view/" + pageName).forward(request, response);
        } catch (ServletException | IOException | UnsupportedHttpMethodException e) {
            LOG.error(e);
        }
    }
}
