package com.havryliuk.store.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.LocalConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductControllerTest {

    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server(8080);
        WebAppContext webAppContext = new WebAppContext(server, "src/main/webapp", "/");

        server.setHandler(webAppContext);
        server.setStopAtShutdown(true);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGetProductById() {
        given().baseUri("http://localhost:8080")
               .expect()
               .response().statusCode(HttpStatus.OK_200)
               .when().get("/product/list");
    }
}
